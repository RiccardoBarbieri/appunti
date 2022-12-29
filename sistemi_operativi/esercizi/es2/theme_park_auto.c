#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include<unistd.h>

#define MAXG 5
#define MAXP 100

#define MAXA 10

#define NUM_THREADS 200

typedef struct {
    int posti_liberi;
    int auto_libere;
    int coda[NUM_THREADS];
    int sospesi;
    int ordine_accesso;
    sem_t sem_condizione;
    pthread_mutex_t mutex;
} parco_t;

parco_t parco;

void init(parco_t *p) {
    p->posti_liberi = MAXP;
    p->auto_libere = MAXA;
    for (int i = 0; i < NUM_THREADS; i++) {
        p->coda[i] = 0;
    }
    p->sospesi = 0;
    sem_init(&p->sem_condizione, 0, 0);
    pthread_mutex_init(&p->mutex, NULL);
}

int gruppo_in_attesa(parco_t *p, int place) {
    for (int i = place - 2; i >= 0; i--) {
        if (p->coda[i] != 0) {
            return 1;
        }
    }
    return 0;
}

void entrata(parco_t *p, int size_gruppo) {
    pthread_mutex_lock(&p->mutex);
    
    int place = ++p->ordine_accesso;

    while (p->posti_liberi < size_gruppo || p->auto_libere == 0 || gruppo_in_attesa(p, place)) {
        p->sospesi++;
        p->coda[place - 1]++;
        pthread_mutex_unlock(&p->mutex);
        sleep(1);
        sem_wait(&p->sem_condizione);
        pthread_mutex_lock(&p->mutex);
        p->sospesi--;
        p->coda[place - 1]--;
    }
    p->posti_liberi -= size_gruppo;
    p->auto_libere--;
    sem_post(&p->sem_condizione);
    printf("Gruppo %d di %d persone entrato (posti %d, auto %d)\n", place, size_gruppo, p->posti_liberi, p->auto_libere);

    pthread_mutex_unlock(&p->mutex);
}

void uscita(parco_t *p, int size_gruppo) {
    pthread_mutex_lock(&p->mutex);
    
    p->posti_liberi += size_gruppo;
    p->auto_libere++;
    for (int i = 0; i < p->sospesi; i++) {
        sem_post(&p->sem_condizione);
    }
    printf("Gruppo %d di %d persone uscito (posti %d, auto %d)\n", p->sospesi, size_gruppo, p->posti_liberi, p->auto_libere);

    pthread_mutex_unlock(&p->mutex);
}

void *visitatore(void *t) {
    int N;

    N = (int)t;
    entrata(&parco, N);
    sleep(1);
    uscita(&parco, N);
}

int main() {
    pthread_t thread[NUM_THREADS];
    int rc, size_gruppo;
    void *status;

    srand(time(NULL));
    
    init(&parco);

    for (int t = 0; t < NUM_THREADS; t++) {
        size_gruppo = (rand() % MAXG) + 1;
        rc = pthread_create(&thread[t], NULL, visitatore, (void *)size_gruppo);
        if (rc) {
            printf("Errore creazione thread %d: %d\n", t, rc);
            exit(1);
        }
    }

    for (int t = 0; t < NUM_THREADS; t++) {
        rc = pthread_join(thread[t], &status);
        if (rc) {
            printf("Errore join thread %d: %d", t, rc);
            exit(1);
        }
    }
    return 0;
}
