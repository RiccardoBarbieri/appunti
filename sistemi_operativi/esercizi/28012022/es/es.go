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

const DIMBUF int = 50

// id mascherine
const CHIR int = 0
const FFP2 int = 1

// dimensione scatola
const BOXDIM int = 1000

// max scatole scaffale
const NC int = 50
const NF int = 30

// dimensione lotti formati
const LC int = 3
const LF int = 2
const LM int = 5

// id formati
const IDLC int = 0
const IDLF int = 1
const IDLM int = 2

// canali ritiro da addetti
// non blocca altre richieste
var reqRitiroLotto [3]chan int
var ackRitiroLotto [3]chan int
var stopRitiroLotto [3]chan int

var reqDeposito [2]chan int
var ackDeposito [2]chan int
var stopDeposito [2]chan int

var termina = make(chan bool)
var done = make(chan bool)

func magazzino() {

	var inRitiro = [2]bool{false, false}
	var inDeposito = [2]bool{false, false}

	var stop bool = false

	var dispLF int = 0
	var dispLC int = 0

	for {
		select {
		case <-when(!stop && LC <= dispLC && !inDeposito[CHIR] && (len(reqRitiroLotto[IDLF])+len(reqRitiroLotto[IDLM])) == 0, reqRitiroLotto[IDLC]):
			dispLC -= LC
			inRitiro[IDLC] = true
			ackRitiroLotto[IDLC] <- 1
		case <-when(!stop && LF <= dispLF && !inDeposito[FFP2] && len(reqRitiroLotto[IDLM]) == 0, reqRitiroLotto[IDLF]):
			dispLF -= LF
			inRitiro[IDLF] = true
			ackRitiroLotto[IDLF] <- 1
		case <-when(!stop && (LM <= dispLC && LM <= dispLF) && !inDeposito[FFP2] && !inDeposito[CHIR], reqRitiroLotto[IDLM]):
			dispLF -= LM
			dispLC -= LM
			inRitiro[IDLC] = true
			inRitiro[IDLF] = true
			ackRitiroLotto[IDLM] <- 1

		case <-stopRitiroLotto[IDLC]:
			inRitiro[IDLC] = false
		case <-stopRitiroLotto[IDLF]:
			inRitiro[IDLF] = false
		case <-stopRitiroLotto[IDLM]:
			inRitiro[IDLC] = false
			inRitiro[IDLF] = false

		case <-when(!stop && !inRitiro[IDLC] && (dispLC < dispLF), reqDeposito[CHIR]):
			inDeposito[CHIR] = true
			ackDeposito[CHIR] <- 1
		case <-when(!stop && !inRitiro[IDLF] && (dispLF <= dispLC), reqDeposito[FFP2]):
			inDeposito[FFP2] = true
			ackDeposito[FFP2] <- 1

		case <-stopDeposito[CHIR]:
			dispLC = NC
			inDeposito[CHIR] = false
		case <-stopDeposito[FFP2]:
			dispLF = NF
			inDeposito[FFP2] = false

		}
	}

}

func addetto(formato int) {
	var ris int

	for {
		reqRitiroLotto[formato] <- 1
		ris = <-ackRitiroLotto[formato]
		if ris == -1 {
			done <- true
			return
		}
		time.Sleep(time.Duration(rand.Intn(5)) * time.Second)

		stopRitiroLotto[formato] <- 1

		time.Sleep(time.Duration(6) * time.Second)
	}
}

func fornitore(tipo int) {
	var ris int

	for {
		reqDeposito[tipo] <- 1
		ris = <-ackDeposito[tipo]
		if ris == -1 {
			done <- true
			return
		}
		time.Sleep(time.Duration(rand.Intn(5)) * time.Second)

		stopDeposito[tipo] <- 1

		time.Sleep(time.Duration(6) * time.Second)
	}
}

func Es() {
	for i := 0; i < 3; i++ {
		reqRitiroLotto[i] = make(chan int, DIMBUF)
		ackRitiroLotto[i] = make(chan int)
		stopRitiroLotto[i] = make(chan int, DIMBUF)
	}
	for i := 0; i < 2; i++ {
		reqDeposito[i] = make(chan int, DIMBUF)
		ackDeposito[i] = make(chan int)
		stopDeposito[i] = make(chan int, DIMBUF)
	}

	for i := 0; i < 5; i++ {
		<-done
	}
	termina <- true
	<-done
}
