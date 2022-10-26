#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

#define N 125
#define M 4

struct args {
    int *a;
    int start;
    int length;
  
};

void *max(void *args) {
    struct args *_args = (struct args *)args;

    printf("Thread %lu: start: %d, length: %d\n", pthread_self(), _args->start, _args->length);

    int max = _args->a[_args->start];
    for (int i = _args->start+1; i < (_args->start + _args->length); i++) {
        if (_args->a[i] > max) {
            max = _args->a[i];
        }
    }
    pthread_exit((void *)max);
}

int main() {
    int V[N], K, split;
    struct args *args = (struct args *)malloc(M * sizeof(struct args));
    args->a = (int *)malloc(N * sizeof(int));
    pthread_t threads[M];

    K = N/M;
    printf("K = %d, split = %d\n", K, split);
    for (int i = 0; i < N; i++) {
        args->a[i] = rand() % 100;
    }
    args->start = 0;
    args->length = K;

    for (int i = 0; i < M; i++) {
        printf("Start: %d, length: %d\n", args->start, args->length);
        pthread_create(&threads[i], NULL, max, (void *)args);
        args->start += K;
        if (i == (M - 2)) {
            args->length = K + split;
        }
    }
    free(args);
}