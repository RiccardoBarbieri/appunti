// es2.go
package es2

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

const MAXPROC = 100
const NEB = 2
const NBT = 3

const BT = 0
const EB = 1
const FLEX = 2

const DIMBUF = 100

type req struct {
	id   int
	tipo int
}

var richiestaT = make(chan req, DIMBUF)
var richiestaE = make(chan req, DIMBUF)
var richiestaFLEX = make(chan req, DIMBUF)
var rilascio = make(chan int, DIMBUF)
var risorsa [MAXPROC](chan int)
var done = make(chan int /*, DIMBUF*/)
var termina = make(chan int /*, DIMBUF*/)

func client(r req) {
	var b int
	switch r.tipo {
	case BT:
		fmt.Printf("	[client %d] Richiesta bici tradizionale\n", r.id)
		richiestaT <- r
	case EB:
		fmt.Printf("	[client %d] Richiesta bici elettrica\n", r.id)
		richiestaE <- r
	case FLEX:
		fmt.Printf("	[client %d] Richiesta flex\n", r.id)
		richiestaFLEX <- r
	}
	b = <-risorsa[r.id]

	if b == BT {
		fmt.Printf("[client %d] Ricevuta bici tradizionale\n", r.id)
	} else {
		fmt.Printf("[client %d] Ricevuta bici elettrica\n", r.id)
	}

	time.Sleep(time.Second * 2)

	rilascio <- b
	done <- r.id
}

func server() {
	var dispE int = NEB
	var dispT int = NBT
	// var nSospE, nSospT int = 0, 0
	// var sospE, sospT [MAXPROC]bool

	// for i := 0; i < MAXPROC; i++ {
	// 	sospE[i] = false
	// 	sospT[i] = false
	// }
	var r req
	var b int

	for {
		select {
		case b = <-rilascio:
			switch b {
			case EB:
				dispE++
				fmt.Printf("	[server] Restituita bici elettrica da cliente %d\n", r.id)
			case BT:
				dispT++
				fmt.Printf("	[server] Restituita bici tradizionale da cliente %d\n", r.id)
			}
		case r = <-when(dispT > 0, richiestaT):
			dispT--
			b = BT
			fmt.Printf("	[server] Noleggio bici tradizionale a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE > 0, richiestaE):
			dispE--
			b = EB
			fmt.Printf("	[server] Noleggio bici elettrica a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE > 0, richiestaFLEX):
			dispE--
			b = EB
			fmt.Printf("	[server] Noleggio bici elettrica a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE == 0 && dispT > 0, richiestaFLEX):
			dispT--
			b = BT
			fmt.Printf("	[server] Noleggio bici tradizionale a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE == 0 && dispT == 0, richiestaFLEX):
			fmt.Printf("[server] Attesa FLEX cliente %d\n", r.id)
			richiestaE <- r //metto req r (associata a client r.id) in attesa per una EB
		case <-termina:
			fmt.Println("Fine!")
			done <- 1
			return

		}
	}
}

func Es2() {
	var r req
	var clienti int = 10

	rand.Seed(time.Now().Unix())

	for i := 0; i < MAXPROC; i++ {
		risorsa[i] = make(chan int, DIMBUF)
	}

	for i := 0; i < clienti; i++ {
		r.id = i
		r.tipo = rand.Intn(3)
		go client(r)
	}
	go server()

	for i := 0; i < clienti; i++ {
		<-done
	}
	termina <- 1 //terminazione server
	<-done
}
