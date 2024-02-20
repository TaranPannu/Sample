#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>

#define MAX_PID 1000

struct node {
    int pid;
    struct node *next;
};

struct node *head = NULL;
pthread_mutex_t mutex;

int generate_random_number(int max_time) {
    return rand() % max_time + 1;
}

void acquire_pid(int *pid) {
    pthread_mutex_lock(&mutex);
    if (head == NULL) {
        *pid = -1;
    } else {
        *pid = head->pid;
        struct node *temp = head;
        head = head->next;
        free(temp);
    }
    pthread_mutex_unlock(&mutex);
}

void release_pid(int pid) {
    struct node *new_node = (struct node*) malloc(sizeof(struct node));
    new_node->pid = pid;
    new_node->next = head;
    head = new_node;
}

void *thread_function(void *arg) {
    int pid;
    int sleep_time_1, sleep_time_2;
    srand(time(NULL) + pthread_self());  

    sleep_time_1 = generate_random_number(*(int*)arg);
    sleep(sleep_time_1);

    acquire_pid(&pid);
    printf("Thread %lu acquired PID %d\n", pthread_self(), pid);

    sleep_time_2 = generate_random_number(*(int*)arg);
    sleep(sleep_time_2);

    release_pid(pid);
    printf("Thread %lu released PID %d\n", pthread_self(), pid);

    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {

    int num_threads = atoi(argv[1]);
    int max_sleep = atoi(argv[2]);

    pthread_t threads[num_threads];
    pthread_mutex_init(&mutex, NULL);

    for (int i = 0; i < MAX_PID; i++) {
        release_pid(i);
    }

    for (int i = 0; i < num_threads; i++) {
        pthread_create(&threads[i], NULL, thread_function, (void*)&max_sleep);
    }

    for (int i = 0; i < num_threads; i++) {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_destroy(&mutex);

    return 0;
}
