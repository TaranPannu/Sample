
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define MAX_RESOURCES 5
int available_resources = MAX_RESOURCES;

pthread_mutex_t mutex;
void *resource_thread(void *resource_request);


int decrease_count(int count) {
    pthread_mutex_lock(&mutex);
    if (available_resources < count) {
        pthread_mutex_unlock(&mutex);
        return -1;
    } else {
        available_resources -= count;
        pthread_mutex_unlock(&mutex);
        return 0;
    }
}

int increase_count(int count) {
    pthread_mutex_lock(&mutex);
    available_resources += count;
    pthread_mutex_unlock(&mutex);
    return 0;
}

int main(int argc, char *argv[]) {
    if (argc == 1) {
        printf("We need at least one parameter\n");  // Feedback to the user
        return 0;  
    }

    int num_requests = argc - 1;
    int *requests_array = malloc(num_requests * sizeof(int));

    for (int i = 0; i < num_requests; i++) {
        int *tmp = (int *)malloc(sizeof(int));
        tmp = requests_array + i;
        *tmp = atoi(argv[i + 1]);
    }

    int num_threads = num_requests;
    pthread_t thread_id[num_threads];

    for (int i = 0; i < num_threads; i++)
        pthread_create(&thread_id[i], NULL, resource_thread, (void *)&requests_array[i]);

    for (int i = 0; i < num_threads; i++)
        pthread_join(thread_id[i], NULL);

    return 0;
}

void *resource_thread(void *resource_request) {
    int request = *((int *)resource_request);

    if (request < 0) 
        printf("%d, ", decrease_count(-request));
     else 
        printf("%d, ", increase_count(request));
    

    
pthread_exit(0);

}
