#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS 3

int *m;
int Count;
double AVG;
int MIN;
int MAX;

void *AVG_runner(void *P) {
    double s = 0;
int i = 0;
while (i < Count) {
    s += m[i];
    i++;
}
AVG = s / Count;

    pthread_exit(0);
}

void *MIN_runner(void *P) {
    int i = 1;
MIN = m[0];
do {
    if (m[i] < MIN) {
        MIN = m[i];
    }
    i++;
} while (i < Count);

    pthread_exit(0);
}

void *MAX_runner(void *P) {
    int i = 1;
MAX = m[0];
while (i < Count) {
    if (m[i] > MAX) {
        MAX = m[i];
        i++;
        continue;
    }
    int j = i + 1;
    while (j < Count && m[j] <= MAX) {
        j++;
    }
    if (j == Count) {
        break;
    }
    i = j;
    MAX = m[i];
}

    pthread_exit(0);
}
int main(int count, char *argv[]) {
 pthread_t avg_thread, min_thread, max_thread;

    if (count < 2) {
        printf("Invalid input");
        return 1;
    }
    
    Count = count - 1;
    m = malloc(sizeof(int) * Count);
  int i = 0;
while (i < Count) {
m[i] = atoi(argv[i+1]);
i++;
}
    
    
    // Creating worker threads
    pthread_create(&avg_thread, NULL, AVG_runner, NULL);
    pthread_create(&min_thread, NULL, MIN_runner, NULL);
    pthread_create(&max_thread, NULL, MAX_runner, NULL);

    pthread_join(avg_thread, NULL);
    pthread_join(min_thread, NULL);
    pthread_join(max_thread, NULL);
    
printf( "The AVG value is %.2f\nThe MIN value is %d\nThe MAX value is %d\n", AVG, MIN, MAX);
    
    free(m);
    
    return 0;
}