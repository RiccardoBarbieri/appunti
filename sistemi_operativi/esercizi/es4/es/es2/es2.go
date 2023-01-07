// es2
package es2

import (
	"fmt"
	"time"

	"golang.org/x/text/cases"
	"golang.org/x/text/language"
)

func when[T any](cond bool, ch chan T) chan T {
	if !cond {
		return nil
	}
	return ch
}

var strTIPO = [2]string{"modello A", "modello B"}

var strELEMENTO = [2]string{"cerchione", "pneumatico"}

var strID = [2][2]string{
	{"CA", "PA"},
	{"CB", "PB"},
}

const DIMBUF int = 50

const TIPOA = 0
const TIPOB = 1

const CERCHIONE = 0
const PNEUMATICO = 1

const MAXP int = 30
const MAXC int = 30

const TOT int = 10

var insPA = make(chan int, DIMBUF)
var insPB = make(chan int, DIMBUF)
var insCA = make(chan int, DIMBUF)
var insCB = make(chan int, DIMBUF)

var prePA = make(chan int, DIMBUF)
var prePB = make(chan int, DIMBUF)
var preCA = make(chan int, DIMBUF)
var preCB = make(chan int, DIMBUF)

var ackRobotA = make(chan int)
var ackRobotB = make(chan int)
var ackNastroPA = make(chan int)
var ackNastroPB = make(chan int)
var ackNastroCA = make(chan int)
var ackNastroCB = make(chan int)

var compMontaggioA = make(chan bool)
var compMontaggioB = make(chan bool)

var done = make(chan bool)
var termina = make(chan bool)

func deposito() {
	var totC int = 0
	var totP int = 0

	var numCA int = 0
	var numCB int = 0
	var numPA int = 0
	var numPB int = 0

	var numMontA int = 0
	var numMontB int = 0

	var stop bool = false

	for {
		select {
		case <-compMontaggioA:
			numMontA++
		case <-compMontaggioB:
			numMontB++

		//inserimenti
		case <-when(!stop && (numMontA < numMontB || (numMontB >= numMontA && len(insPB) == 0)) && (totP < MAXP && numPA < MAXP-1), insPA):
			totP++
			numPA++
			ackNastroPA <- 1
		case <-when(!stop && (numMontA < numMontB || (numMontB >= numMontA && len(insCB) == 0)) && (totC < MAXC && numCA < MAXC-1), insCA):
			totC++
			numCA++
			ackNastroCA <- 1

		case <-when(!stop && (numMontB <= numMontA || (numMontB > numMontA && len(insPA) == 0)) && (totP < MAXP && numPB < MAXP-1), insPB):
			totP++
			numPB++
			ackNastroPB <- 1
		case <-when(!stop && (numMontB <= numMontA || (numMontB > numMontA && len(insCA) == 0)) && (totC < MAXC && numCB < MAXC-1), insCB):
			totC++
			numCB++
			ackNastroCB <- 1

		//prelievi
		case <-when(!stop && (numMontA < numMontB || (numMontB >= numMontA && len(prePB) == 0)) && (numPA > 0), prePA):
			totP--
			numPA--
			ackRobotA <- 1
		case <-when(!stop && (numMontA < numMontB || (numMontB >= numMontA && len(preCB) == 0)) && (numCA > 0), preCA):
			totC--
			numCA--
			ackRobotA <- 1
		case <-when(!stop && (numMontB <= numMontA || (numMontA > numMontB && len(prePA) == 0)) && (numPB > 0), prePB):
			totP--
			numPB--
			ackRobotB <- 1
		case <-when(!stop && (numMontA <= numMontB || (numMontA > numMontB && len(preCA) == 0)) && (numCB > 0), preCB):
			totC--
			numCB--
			ackRobotB <- 1

		case <-when(stop, insCA):
			ackNastroCA <- -1
		case <-when(stop, insCB):
			ackNastroCB <- -1
		case <-when(stop, insPA):
			ackNastroPA <- -1
		case <-when(stop, insPB):
			ackNastroPB <- -1
		case <-when(stop, preCA):
			ackRobotA <- -1
		case <-when(stop, preCB):
			ackRobotB <- -1
		case <-when(stop, prePA):
			ackRobotA <- -1
		case <-when(stop, prePB):
			ackRobotB <- -1

		case <-termina: // quando tutti i processi hanno finito
			done <- true
			return
		}

		if (numMontA + numMontB) == TOT {
			stop = true
		}
	}
}

func robot(tipo int) {
	var ris int
	for {
		if tipo == TIPOA {
			for i := 0; i < 4; i++ {
				preCA <- 1
				ris = <-ackRobotA
				if ris == -1 {
					done <- true
					return
				}

				prePA <- 1
				ris = <-ackRobotA
				if ris == -1 {
					done <- true
					return
				}
			}
			compMontaggioA <- true
		} else if tipo == TIPOB {
			for i := 0; i < 4; i++ {
				preCB <- 1
				ris = <-ackRobotB
				if ris == -1 {
					done <- true
					return
				}

				prePB <- 1
				ris = <-ackRobotB
				if ris == -1 {
					done <- true
					return
				}
			}
			compMontaggioB <- true
		}
		time.Sleep(time.Duration(3) * time.Second)
		fmt.Printf("[robot %s] Montaggio completato\n", strTIPO[tipo])
	}
}

func nastro(tipo int, elemento int) {

	var ris int

	for {
		if (tipo == TIPOA) && (elemento == CERCHIONE) {
			insCA <- 1
			ris = <-ackNastroCA
			if ris == -1 {
				done <- true
				return
			}
		} else if (tipo == TIPOA) && (elemento == PNEUMATICO) {
			insPA <- 1
			ris = <-ackNastroPA
			if ris == -1 {
				done <- true
				return
			}
		} else if (tipo == TIPOB) && (elemento == PNEUMATICO) {
			insPB <- 1
			ris = <-ackNastroPB
			if ris == -1 {
				done <- true
				return
			}
		} else if (tipo == TIPOB) && (elemento == CERCHIONE) {
			insCB <- 1
			ris = <-ackNastroCB
			if ris == -1 {
				done <- true
				return
			}
		}
		time.Sleep(time.Duration(2) * time.Second)
		fmt.Printf("[nastro %s] %s %s consegnato\n", strID[tipo][elemento], cases.Title(language.Italian).String(strELEMENTO[elemento]), strTIPO[tipo])
	}
}

func Es2() {
	go deposito()

	for i := 0; i < 2; i++ {
		for j := 0; j < 2; j++ {
			go nastro(i, j)
			fmt.Printf("[main] Avviato nastro %s\n", strID[i][j])
		}
	}

	for i := 0; i < 2; i++ {
		go robot(i)
		fmt.Printf("[main] Avviato robot %s\n", strTIPO[i])
	}

	for i := 0; i < 6; i++ { //terminazione robot e nastri
		<-done
		fmt.Printf("%d\n", i)
	}
	termina <- true //terminazione deposito
	<-done
}
