#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <limits.h>
#include <semaphore.h>

#define N 4 // persone
#define K 2  // film-domande

// dominio risposta domanda [1...10]

typedef struct tv_survey
{
    char film[K][50];
    int voti[K];
    int risposte;
    int indice_vincitore;
    pthread_mutex_t m;
} tv_survey;

typedef struct barrier
{
    int completati;
    sem_t mutex;
    sem_t barriera;
} barrier;

void init(tv_survey *s, barrier *barriera)
{
    // inizializa il sondaggio
    s->risposte = 0;
    for (int i = 0; i < K; i++)
    {
        // nome film
        sprintf(s->film[i], "Film %d", i);
        // voto film
        s->voti[i] = 0;
    }
    pthread_mutex_init(&s->m, NULL);

    // initialize barriera
    barriera->completati = 0;
    sem_init(&barriera->mutex, 0, 1);
    sem_init(&barriera->barriera, 0, 0);
}

tv_survey sondaggio;
barrier barriera;

void vota(tv_survey *s, int spettatore)
{
    // vota il sondaggio
    pthread_mutex_lock(&s->m);
    printf("Spettatore %d sta votando...\n", spettatore);
    for (int i = 0; i < K; i++)
    {
        // voto film
        s->voti[i] += 1 + rand() % 10;
    }
    s->risposte++;
    printf("Spettatore %d ha finito di votare...\n", spettatore);
    for (int i = 0; i < K; i++)
    {
        printf("Media parziale film \"%s\": %f\n", s->film[i], (float)s->voti[i] / s->risposte);
    }    
    pthread_mutex_unlock(&s->m);   
}

void visione(int th, tv_survey *s) {
    pthread_mutex_lock(&s->m);
    printf("Spettatore %d sta scaricando %s...\n", th, s->film[s->indice_vincitore]);
    pthread_mutex_unlock(&s->m); 
}

void wait_barriera(barrier *b, tv_survey *s) {
    sem_wait(&b->mutex);
    b->completati++;
    if (b->completati == N) {
        // calcola il vincitore
        int max = INT_MIN;
        for (int i = 0; i < K; i++)
        {
            if (s->voti[i] > max) {
                max = s->voti[i];
                s->indice_vincitore = i;
            }
        }
        printf("Il vincitore è %s con voto %f\n", s->film[s->indice_vincitore], (float) max / N);
        sem_post(&b->barriera);
    }
    sem_post(&b->mutex); 
    sem_wait(&b->barriera);
    sem_post(&b->barriera);
}

void *spettatore(void *t)
{
    int tid;
    long result = 0;
    tid = (int)t;
    vota(&sondaggio, tid);
    wait_barriera(&barriera, &sondaggio);
    visione(tid, &sondaggio);
    pthread_exit((void *)result);
}

int main() {
    pthread_t threads[N];
    int rc;
    int t;
    init(&sondaggio, &barriera);

    for (t = 0; t < N; t++) {
        rc = pthread_create(&threads[t], NULL, spettatore, (void *)t);
        printf("Creazione thread %ld\n", t);
        if (rc) {
            printf("ERRORE: %d\n", rc);
            exit(-1);
        }
    }

    for (t = 0; t < N; t++) {
        rc = pthread_join(threads[t], NULL);
        if (rc) {
            printf("ERRORE join thread %ld: %d\n", threads[t], rc);
            exit(-1);
        }
    }

    return 0;

}
