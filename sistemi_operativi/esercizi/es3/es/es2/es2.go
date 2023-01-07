// es2.go
package es2

import (
	"fmt"
	"math/rand"
	"time"
)

var colors = [8]string{
	"\033[0m",
	"\033[31m",
	"\033[32m",
	"\033[33m",
	"\033[34m",
	"\033[35m",
	"\033[36m",
	"\033[37m",
}

func when[T any](cond bool, ch chan T) chan T {
	if !cond {
		return nil
	}
	return ch
}

func printColor(text string, color int) {
	text = "%s" + text + "%s"
	fmt.Printf(text, colors[(color%6)+1], color, colors[0])
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
var rilascio = make(chan req, DIMBUF)
var risorsa [MAXPROC](chan int)
var done = make(chan int)
var termina = make(chan int)

func client(r req) {
	switch r.tipo {
	case BT:
		printColor("[client %d] Richiesta bici tradizionale\n", r.id)
		richiestaT <- r
	case EB:
		printColor("[client %d] Richiesta bici elettrica\n", r.id)
		richiestaE <- r
	case FLEX:
		printColor("[client %d] Richiesta flex\n", r.id)
		richiestaFLEX <- r
	}
	r.tipo = <-risorsa[r.id]

	if r.tipo == BT {
		printColor("[client %d] Ricevuta bici tradizionale\n", r.id)
	} else {
		printColor("[client %d] Ricevuta bici elettrica\n", r.id)
	}

	time.Sleep(time.Second * 2)

	rilascio <- r
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
		case r = <-rilascio:
			switch r.tipo {
			case EB:
				dispE++
				printColor("	[server] Restituita bici elettrica da cliente %d\n", r.id)
			case BT:
				dispT++
				printColor("[server] Restituita bici tradizionale da cliente %d\n", r.id)
			}
		case r = <-when(dispT > 0, richiestaT):
			dispT--
			b = BT
			printColor("	[server] Noleggio bici tradizionale a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE > 0, richiestaE):
			dispE--
			b = EB
			printColor("	[server] Noleggio bici elettrica a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE > 0, richiestaFLEX):
			dispE--
			b = EB
			printColor("	[server] Noleggio bici elettrica FLEX a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE == 0 && dispT > 0, richiestaFLEX):
			dispT--
			b = BT
			printColor("	[server] Noleggio bici tradizionale FLEX a cliente %d\n", r.id)
			risorsa[r.id] <- b
		case r = <-when(dispE == 0 && dispT == 0, richiestaFLEX):
			printColor("[server] Attesa FLEX cliente %d\n", r.id)
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
	var clienti int = 7

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
