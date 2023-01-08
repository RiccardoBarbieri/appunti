// es
package es

import (
	"fmt"
	"math/rand"
	"time"
)

func when[T any](cond bool, ch chan T) chan T {
	if !cond {
		return nil
	}
	return ch
}

const DIMBUF int = 20

// capacità massima refrigetori
const MAXP int = 5000
const MAXM int = 8000

// id vaccini
const VP int = 0
const VM int = 1

// stringhe
var strPRODUTTORE = [2]string{"Pfizer-Biontech", "Moderna"}
var strZONA = [3]string{"gialla", "arancione", "rossa"}
var strREGIONE = [20]string{"Valle D'aosta", "Piemonte", "Lombardia", "Veneto", "Friuli V.G.", "Trentino Alto Adige",
	"Liguria", "Toscana", "Emilia Romagna", "Marche", "Umbria", "Lazio", "Abruzzo", "Molise", "Campania", "Basilicata",
	"Puglia", "Calabria", "Sicilia", "Sardegna"}

// var str

// dimensione lotti
const NLP int = 100
const NLM int = 150

// quantità richiesta
const QREQ int = 50

// colori regioni
const GIALLO = 0
const ARANCIONE = 1
const ROSSO = 2

const NREG int = 20

// target lotti terminazione programma
const TOTP int = 50
const TOTM int = 50

// canali consegna
var reqDepP = make(chan int, DIMBUF)
var reqDepM = make(chan int, DIMBUF)

var ackDepP = make(chan int)
var ackDepM = make(chan int)

// canali ritiro
var reqRitiro [3]chan int

var ackRitiro [NREG]chan int

var done = make(chan bool)
var termina = make(chan bool)

func deposito() {
	var dispP int = 0
	var dispM int = 0

	var totDisp int = 0

	var libP int = MAXP
	var libM int = MAXM

	var nLottiP int = 0
	var nLottiM int = 0

	var stopP bool = false
	var stopM bool = false

	var id int

	for {
		select {
		case <-when(!stopP && libP >= NLP, reqDepP):
			dispP += NLP
			totDisp += NLP
			libP -= NLP

			ackDepP <- 1

			nLottiP++
		case <-when(!stopM && libM >= NLM, reqDepM):
			dispM += NLM
			totDisp += NLM
			libM -= NLM

			ackDepM <- 1

			nLottiM++

		case id = <-when(!(stopM && stopP) && totDisp >= QREQ && (len(reqRitiro[ARANCIONE])+len(reqRitiro[ROSSO])) == 0, reqRitiro[GIALLO]):
			if dispP >= QREQ {
				dispP -= QREQ
			} else if dispM >= QREQ {
				dispM -= QREQ
			} else {
				dispM -= (QREQ - dispP)
				dispP = 0
			}
			totDisp -= QREQ

			ackRitiro[id] <- 1
		case id = <-when(!(stopM && stopP) && totDisp >= QREQ && len(reqRitiro[ROSSO]) == 0, reqRitiro[ARANCIONE]):
			if dispP >= QREQ {
				dispP -= QREQ
			} else if dispM >= QREQ {
				dispM -= QREQ
			} else {
				dispM -= (QREQ - dispP)
				dispP = 0
			}
			totDisp -= QREQ

			ackRitiro[id] <- 1
		case id = <-when(!(stopM && stopP) && totDisp >= QREQ, reqRitiro[ROSSO]):
			if dispP >= QREQ {
				dispP -= QREQ
			} else if dispM >= QREQ {
				dispM -= QREQ
			} else {
				dispM -= (QREQ - dispP)
				dispP = 0
			}
			totDisp -= QREQ

			ackRitiro[id] <- 1

		case <-when(stopP, reqDepP):
			ackDepP <- -1
		case <-when(stopM, reqDepM):
			ackDepM <- -1
		case id = <-when((stopM && stopP), reqRitiro[GIALLO]):
			ackRitiro[id] <- -1
		case id = <-when((stopM && stopP), reqRitiro[ARANCIONE]):
			ackRitiro[id] <- -1
		case id = <-when((stopM && stopP), reqRitiro[ROSSO]):
			ackRitiro[id] <- -1

		case <-termina: // quando tutti i processi hanno finito
			done <- true
			return
		}

		if nLottiM == TOTM {
			stopM = true
		}
		if nLottiP == TOTP {
			stopP = true
		}
	}
}

func produttore(tipo int) {
	var ris int

	for {
		if tipo == VP {
			reqDepP <- 1
			ris = <-ackDepP
			if ris == -1 {
				done <- true
				return
			}
		} else if tipo == VM {
			reqDepM <- 1
			ris = <-ackDepM
			if ris == -1 {
				done <- true
				return
			}
		}
		time.Sleep(time.Duration(rand.Intn(3)) * time.Second)
		fmt.Printf("[produttore %s] Consegnato lotto di vaccini\n", strPRODUTTORE[tipo])
	}
}

func regione(id int, colore int) {
	var ris int

	for {
		reqRitiro[colore] <- id
		ris = <-ackRitiro[id]
		if ris == -1 {
			done <- true
			return
		}
		time.Sleep(time.Duration(rand.Intn(3)) * time.Second)
		fmt.Printf("[regione %s %s] Ricevuti %d vaccini\n", strREGIONE[id], strZONA[colore], QREQ)
	}
}

func Es() {

	rand.Seed(time.Now().Unix())

	for i := 0; i < NREG; i++ {
		ackRitiro[i] = make(chan int)
	}

	for i := 0; i < 3; i++ {
		reqRitiro[i] = make(chan int, DIMBUF)
	}

	go deposito()

	go produttore(VP)
	go produttore(VM)

	for i := 0; i < NREG; i++ {
		go regione(i, rand.Intn(3))
	}

	for i := 0; i < 22; i++ { //terminazione regioni e produttori
		<-done
	}
	termina <- true //terminazione deposito
	<-done

}
