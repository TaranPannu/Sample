#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>

#define MAX_NUMBERS 1000000

int numbers[MAX_NUMBERS];
int num_count = 0;
int sum = 0;

pthread_mutex_t mutex;
sem_t empty;
sem_t full;

void *input_thread(void *arg) {
    int n;
    while (1) {
        printf("Input a number: ");
        scanf("%d", &n);
        if (n < 0) {
            // Send sentinel value
            n = -1;
        }
        sem_wait(&empty);
        pthread_mutex_lock(&mutex);
        numbers[num_count++] = n;
        pthread_mutex_unlock(&mutex);
        sem_post(&full);
        if (n == -1) {
            break;
        }
    }
    return NULL;
}

void *sum_thread(void *arg) {
    int n;
    while (1) {
        sem_wait(&full);
        pthread_mutex_lock(&mutex);
        n = numbers[--num_count];
        pthread_mutex_unlock(&mutex);
        sem_post(&empty);
        if (n == -1) {
            break;
        }
        sum += n;
        printf("Current Total: %d\n", sum);
    }
    printf("The sum of the input numbers is %d\n", sum);
    return NULL;
}

int main() {
    pthread_t tid1, tid2;
    pthread_mutex_init(&mutex, NULL);
    sem_init(&empty, 0, MAX_NUMBERS);
    sem_init(&full, 0, 0);
    pthread_create(&tid1, NULL, input_thread, NULL);
    pthread_create(&tid2, NULL, sum_thread, NULL);
    pthread_join(tid1, NULL);
    pthread_join(tid2, NULL);
    pthread_mutex_destroy(&mutex);
    sem_destroy(&empty);
    sem_destroy(&full);
    return 0;
}
