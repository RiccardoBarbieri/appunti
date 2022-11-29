#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <limits.h>

#define N 10 // persone
#define K 5   // film-domande

// dominio risposta domanda [1...10]

typedef struct tv_survey
{
    char film[K][50];
    int voti[K];
    int risposte;
    pthread_mutex_t m;
} tv_survey;

void init_sondaggio(tv_survey *s)
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
}

tv_survey sondaggio;

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

void *spettatore(void *t)
{
    int tid;
    long result = 0;
    tid = (int)t;
    vota(&sondaggio, tid);
    pthread_exit((void *)result);
}

int main() {
    pthread_t threads[N];
    int rc;
    long t;
    init_sondaggio(&sondaggio);

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