#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include<unistd.h>


#define MAXP 100
#define MAXB 5
#define MAXM 5

#define MONOP 0
#define BICI 1

#define NUM_THREADS 200

typedef struct {
    int posti_liberi;
    int bici_libere;
    int monopattini_liberi;
    sem_t sem_condizione;
    int sospesi;

    pthread_mutex_t mutex;
} parco_t;

parco_t parco;

void init(parco_t *p) {
    p->posti_liberi = MAXP;
    p->bici_libere = MAXB;
    p->monopattini_liberi = MAXM;
    p->sospesi = 0;
    sem_init(&p->sem_condizione, 0, 0);
    pthread_mutex_init(&p->mutex, NULL);
}

void entrata(parco_t *p, int mezzo) {
    pthread_mutex_lock(&p->mutex);
    
    if (mezzo == BICI) {
        while (p->posti_liberi == 0 || p->bici_libere == 0) {
            p->sospesi++;
            pthread_mutex_unlock(&p->mutex);
            sem_wait(&p->sem_condizione);
            pthread_mutex_lock(&p->mutex);
            p->sospesi--;
        }
        p->posti_liberi--;
        p->bici_libere--;
    }
    else if (mezzo == MONOP) {
        while (p->posti_liberi == 0 || p->monopattini_liberi == 0) {
            p->sospesi++;
            pthread_mutex_unlock(&p->mutex);
            sem_wait(&p->sem_condizione);
            pthread_mutex_lock(&p->mutex);
            p->sospesi--;
        }
        p->posti_liberi--;
        p->monopattini_liberi--;
    }
    else {
        printf("Errore: mezzo non valido\n");
        exit(1);
    }

    pthread_mutex_unlock(&p->mutex);
}

void uscita(parco_t *p, int mezzo) {
    pthread_mutex_lock(&p->mutex);

    p->posti_liberi++;
    if (mezzo == BICI) {
        p->bici_libere++;
    }
    else {
        p->monopattini_liberi++;
    }
    for (int i = 0; i < p->sospesi; i++)
        sem_post(&p->sem_condizione);

    pthread_mutex_unlock(&p->mutex);
}

const char *get_mezzo(int mezzo) {
    if (mezzo == BICI) {
        return "bici";
    }
    else if (mezzo == MONOP) {
        return "monopattino";
    }
    else {
        printf("Errore: mezzo non valido\n");
        exit(1);
    }
}

void *visitatore(void *t) {
    int mezzo = rand() % 2;
    int th = (int) t;

    entrata(&parco, mezzo);
    printf("Visitatore %d entrato con %s\n", th, get_mezzo(mezzo));
    sleep(rand() % 2);
    uscita(&parco, mezzo);
    printf("Visitatore %d uscito, liberato %s\n", th, get_mezzo(mezzo));
}

int main() {
    pthread_t thread[NUM_THREADS];
    int rc;
    void *status;

    srand(time(NULL));
    
    init(&parco);

    for (int t = 0; t < NUM_THREADS; t++) {
        rc = pthread_create(&thread[t], NULL, visitatore, (void *)t);
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
