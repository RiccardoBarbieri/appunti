#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <omp.h>
#include <sys/time.h>

#define DEBUG 1

#define VECTOR_LENGHT 10

int main()
{
    int Vector[VECTOR_LENGHT];

    omp_set_num_threads(4);

    int inner, outer;
    int min, index_min;
    int random_number;

    Vector[0] = 7;
    Vector[1] = 1;
    Vector[2] = 10;
    Vector[3] = 3;
    Vector[4] = 2;
    Vector[5] = 9;
    Vector[6] = 4;
    Vector[7] = 8;
    Vector[8] = 5;
    Vector[9] = 6;

    

//     for (outer = 0; outer < VECTOR_LENGHT; outer++)
//     {
//         // random integer between 0 and RAND_MAX
//         // Vector[outer] = rand() % 10;
//         // Vector[outer] = rand() % 10;

// #ifdef DEBUG
//         printf("Vector[%d] = %d\n", outer, Vector[outer]);
// #endif
//     }
    struct timeval tv;

    gettimeofday(&tv, NULL);

    unsigned long long begin =
        (unsigned long long)(tv.tv_sec) * 1000 +
        (unsigned long long)(tv.tv_usec) / 1000;
        
    int min_array[4];
    for (int i = 0; i < 4; i++)
    {
        min_array[i] = INT_MAX;
    }
    int index_min_array[4];

    for (outer = 0; outer < VECTOR_LENGHT; outer++)
    {
        min = INT_MAX;

        #pragma omp parallel for num_threads(4)
        for (inner = outer; inner < VECTOR_LENGHT; inner++)
        {
            if (Vector[inner] < min_array[omp_get_thread_num()])
            {
                min_array[omp_get_thread_num()] = Vector[inner];
                index_min_array[omp_get_thread_num()] = inner;
            }
        }
        for (int i = 0; i < 4; i++) {
            printf("min_array[%d] = %d____", i, min_array[i]);
            printf("index_min_array[%d] = %d\n", i, index_min_array[i]);
        }

        for (int i = 0; i < 4; i++)
        {
            if (min_array[i] < min)
            {
                min = min_array[i];
                index_min = index_min_array[i];
            }
        }
        printf("min = %d, index_min = %d\n", min, index_min);

        Vector[index_min] = Vector[outer];
        Vector[outer] = min;

#ifdef DEBUG
        for (int i = 0; i < VECTOR_LENGHT; i++)
            printf("Debug - Vector[%d] = %d\n", i, Vector[i]);
#endif
   }

    gettimeofday(&tv, NULL);

    unsigned long long end =
        (unsigned long long)(tv.tv_sec) * 1000 +
        (unsigned long long)(tv.tv_usec) / 1000;


// #ifdef DEBUG
//     for (outer = 0; outer < VECTOR_LENGHT; outer++)
//         printf("Sorted - Vector[%d] = %d\n", outer, Vector[outer]);
// #endif



    for (int i = 0; i < VECTOR_LENGHT - 1; i++) {
        if (Vector[i] > Vector[i + 1]) {
            printf("Error: Vector[%d] = %d > Vector[%d] = %d\n", i, Vector[i], i + 1, Vector[i + 1]);
        }
    }

    double time_spent = (double)(end - begin);
    printf("Time spent: %f\n\n\n\n", time_spent);

    return 0;
}
