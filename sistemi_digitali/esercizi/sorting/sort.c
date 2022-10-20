#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <sys/time.h>

#define DEBUG 1

#define VECTOR_LENGHT 1000000

int main()
{
    int Vector[VECTOR_LENGHT];

    int inner, outer;
    int min, index_min;
    int random_number;

    for (outer = 0; outer < VECTOR_LENGHT; outer++)
    {
        // random integer between 0 and RAND_MAX
        Vector[outer] = rand();
    }
    struct timeval tv;

    gettimeofday(&tv, NULL);

    unsigned long long begin =
        (unsigned long long)(tv.tv_sec) * 1000 +
        (unsigned long long)(tv.tv_usec) / 1000;


    for (outer = 0; outer < VECTOR_LENGHT; outer++)
    {
        min = INT_MAX;

        for (inner = outer; inner < VECTOR_LENGHT; inner++)
        {
            if (Vector[inner] < min)
            {
                min = Vector[inner];
                index_min = inner;
            }
        }

        Vector[index_min] = Vector[outer];
        Vector[outer] = min;
    }
    gettimeofday(&tv, NULL);

    unsigned long long end =
        (unsigned long long)(tv.tv_sec) * 1000 +
        (unsigned long long)(tv.tv_usec) / 1000;

    double time_spent = (double)(end - begin);
    printf("Millisecond elapsed: %f\n\n\n\n", time_spent);

    return 0;
}
