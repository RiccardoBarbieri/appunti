package es1

import (
	"fmt"
	"math/rand"
	"time"
)

const MAXPROC = 100
const NEB = 2
const NBT = 3

const BT = 0
const EB = 1
const FLEX = 2

type req struct {
	id   int
	tipo int
}

var richiesta = make(chan req)
var rilascio = make(chan int)
var risorsa [MAXPROC](chan int)
var done = make(chan int)
var termina = make(chan int)

func client(r req) {
	var b int
	switch r.tipo {
	case BT:
		fmt.Printf("	[client %d] Richiesta bici tradizionale\n", r.id)
	case EB:
		fmt.Printf("	[client %d] Richiesta bici elettrica\n", r.id)
	case FLEX:
		fmt.Printf("	[client %d] Richiesta flex\n", r.id)
	}
	richiesta <- r
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
	var nSospE, nSospT int = 0, 0
	var sospE, sospT [MAXPROC]bool

	for i := 0; i < MAXPROC; i++ {
		sospE[i] = false
		sospT[i] = false
	}
	var r req
	var b int

	for {
		select {
		case b = <-rilascio:
			switch b {
			case EB:
				if nSospE == 0 {
					dispE++
					fmt.Printf("	[server] Restituita bici elettrica da cliente %d\n", r.id)
				} else {
					for i := 0; i < MAXPROC; i++ {
						if sospE[i] {
							risorsa[i] <- b
							nSospE--
							sospE[i] = false
							break
						}
					}
				}
			case BT:
				if nSospT == 0 {
					dispT++
					fmt.Printf("	[server] Restituita bici tradizionale da cliente %d\n", r.id)
				} else {
					for i := 0; i < MAXPROC; i++ {
						if sospT[i] {
							risorsa[i] <- b
							nSospT--
							sospT[i] = false
							break
						}
					}
				}
			}
		case r = <-richiesta:
			switch r.tipo {
			case BT:
				if dispT > 0 {
					dispT--
					b = BT
					fmt.Printf("	[server] Noleggio bici tradizionale a cliente %d\n", r.id)
					risorsa[r.id] <- b
				} else {
					nSospT++
					sospT[r.id] = true
				}
			case EB:
				if dispE > 0 {
					dispE--
					b = EB
					fmt.Printf("	[server] Noleggio bici elettrica a cliente %d\n", r.id)
					risorsa[r.id] <- b
				} else {
					nSospE++
					sospE[r.id] = true
				}
			case FLEX:
				if dispE > 0 {
					dispE--
					b = EB
					fmt.Printf("	[server] Noleggio bici elettrica a cliente %d\n", r.id)
					risorsa[r.id] <- b
				} else if dispT > 0 {
					dispT--
					b = BT
					fmt.Printf("	[server] Noleggio bici tradizionale a cliente %d\n", r.id)
					risorsa[r.id] <- b
				} else {
					nSospE++
					sospE[r.id] = true
				}
			}
		case <-termina:
			fmt.Println("Fine!")
			done <- 1
			return

		}
	}
}

func Es1() {
	var r req
	var clienti int = 10
	rand.Seed(time.Now().Unix())

	for i := 0; i < MAXPROC; i++ {
		risorsa[i] = make(chan int)
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
	termina <- 1
	<-done
}
