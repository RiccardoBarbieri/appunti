#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <limits.h>

#define N 120
#define K 10

int V[N];

void *max(void *t)
{
    int first = (int)t;

    int max = V[first];
    for (int i = 0; i < first + K; i++)
    {
        if (V[i] > max)
        {
            max = V[i];
        }
    }
    pthread_exit((void *)max);
}

int main()
{
    int M, max_value = INT_MIN;
    M = N / K;
    pthread_t threads[M];

    srand(time(0));
    for (int i = 0; i < N; i++)
    {
        V[i] = 1 + rand() % 100;
    }

    for (int i = 0; i < M; i++)
    {
        int rc = pthread_create(&threads[i], NULL, max, (void *)(i * K));
        if (rc)
        {
            printf("Error creating thread %d with code %d", i, rc);
            exit(-1);
        }
    }

    for (int i = 0; i < M; i++)
    {
        int status;
        pthread_join(threads[i], (void *)&status);
        // printf("Thread %lu finished with result %d", threads[i], status);

        if (status > max_value)
        {
            max_value = status;
        }
    }

    printf("Max value: %d", max_value);
    int check_correct = 1, check_found = 0;
    for (int i = 0; i < N; i++)
    {
        if (V[i] == max_value && !check_found)
        {
            printf(" at index %d\n", i);
            check_found = 1;
        }
        if (V[i] > max_value)
        {
            check_correct = 0;
        }
    }
    if (check_correct)
    {
        printf("The max value is correct\n");
    }
}