// es1
package es1

import (
	"fmt"
	"math/rand"
	"time"
)

const MAXPROC int = 100
const DIMBUF int = 100
const MAX int = 50

const A = 0
const P = 1
const S = 0
const N = 1

//lettera finale indica sempre direzione
//di entrata, anche nei canali di uscita
var enAutoN = make(chan int, DIMBUF)
var enAutoS = make(chan int, DIMBUF)
var enPedoneN = make(chan int, DIMBUF)
var enPedoneS = make(chan int, DIMBUF)

var exN = make(chan int)
var exS = make(chan int)

var ACK [MAXPROC]chan int

var done = make(chan bool)
var termina = make(chan bool)

func when[T any](cond bool, ch chan T) chan T {
	if !cond {
		return nil
	}
	return ch
}

func client(id int, dir int) {

	var tipo int = rand.Intn(2)

	if dir == N {
		if tipo == A {
			enAutoN <- id
			<-ACK[id]
			fmt.Printf("[client %d] Entrata auto NORD\n", id)
			time.Sleep(time.Duration(rand.Intn(3)) * time.Second)
			exN <- tipo
			fmt.Printf("[client %d] Uscito pedone SUD\n", id)
		} else {
			enPedoneN <- id
			<-ACK[id]
			fmt.Printf("[client %d] Entrato pedone NORD\n", id)
			time.Sleep(time.Duration(rand.Intn(3)) * time.Second)
			exN <- tipo
			fmt.Printf("[client %d] Uscito pedone NORD\n", id)
		}
	} else {
		if tipo == A {
			enAutoS <- id
			<-ACK[id]
			fmt.Printf("[client %d] Entrata auto SUD\n", id)
			time.Sleep(time.Duration(rand.Intn(3)) * time.Second)
			exS <- tipo
			fmt.Printf("[client %d] Uscita auto SUD\n", id)
		} else {
			enPedoneS <- id
			<-ACK[id]
			fmt.Printf("[client %d] Entrata pedone SUD\n", id)
			time.Sleep(time.Duration(rand.Intn(3)) * time.Second)
			exS <- tipo
			fmt.Printf("[client %d] Uscita pedone SUD\n", id)
		}
	}
	done <- true
}

func server() {
	//contatori per ogni tipo utente
	var numPN int = 0
	var numPS int = 0
	var numAN int = 0
	var numAS int = 0

	//tot sul ponte
	var tot int = 0

	//var ricezione messaggi
	var id int
	var out int

	for {
		select {
		//entrate
		case id = <-when((tot < MAX) && (numAN == 0), enPedoneS):
			numPS++
			tot++
			ACK[id] <- 1
			fmt.Printf("[server] Concessa entrata pedone %d SUD\n", id)
		case id = <-when((tot < MAX) && (numAS == 0) && (len(enPedoneS) == 0), enPedoneN):
			numPN++
			tot++
			ACK[id] <- 1
			fmt.Printf("[server] Concessa entrata pedone %d NORD\n", id)
		case id = <-when((tot+10 <= (MAX)) && ((numAN+numPN) == 0) && ((len(enPedoneS)+len(enPedoneN)) == 0), enAutoS):
			numAS++
			tot += 10
			ACK[id] <- 1
			fmt.Printf("[server] Concessa entrata auto %d SUD\n", id)
		case id = <-when((tot+10 <= (MAX)) && ((numAS+numPS) == 0) && ((len(enPedoneS)+len(enPedoneN)+len(enAutoS)) == 0), enAutoN):
			numAN++
			tot += 10
			ACK[id] <- 1
			fmt.Printf("[server] Concessa entrata auto %d NORD\n", id)
		//uscite
		case out = <-exS:
			switch out {
			case A:
				tot -= 10
				numAS--
			case P:
				tot--
				numPS--
			}
		case out = <-exN:
			switch out {
			case A:
				tot -= 10
				numAN--
			case P:
				tot--
				numPN--
			}
		case <-termina: // quando tutti i processi hanno finito
			done <- true
			return
		}
	}
}

func Es1() {
	const UN = 30 //numero utenti da nord
	const US = 20 //numero utenti da sud
	var i int = 0

	rand.Seed(time.Now().Unix())

	for i = 0; i < UN+US; i++ {
		ACK[i] = make(chan int)
	}

	go server()
	fmt.Println("Starting clients NORD")
	for i = 0; i < UN; i++ {
		go client(i, N)
	}
	fmt.Println("Starting clients SUD")
	for j := i; j < (UN + US); j++ {
		go client(j, S)
	}

	fmt.Println("Waiting for clients")
	for i = 0; i < (UN + US); i++ {
		<-done
	}
	termina <- true
	<-done

}
