#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NUM_COUNT 5

int main() {
    int num_list[NUM_COUNT] = {1, 2, 3, 4, 5};
    int fd[2];
    pid_t pid;
    char buffer[NUM_COUNT * sizeof(int)];

    if (pipe(fd) == -1) {
        fprintf(stderr, "Failed to create pipe\n");
        return 1;
    }

    pid = fork();

    if (pid < 0) {
        fprintf(stderr, "Failed to fork process\n");
        return 1;
    }

    if (pid == 0) {
        // Child process
        close(fd[0]); // Close the read end of the pipe
        write(fd[1], num_list, sizeof(num_list)); // Write the list of numbers to the write end of the pipe
        close(fd[1]); // Close the write end of the pipe
        exit(0);
    } else {
        // Parent process
        close(fd[1]); // Close the write end of the pipe
        read(fd[0], buffer, sizeof(buffer)); // Read the list of numbers from the read end of the pipe
        close(fd[0]); // Close the read end of the pipe

        // Convert the buffer to an array of integers
        int *received_list = (int*) buffer;
        int i;
        printf("Received list of numbers: ");
        for (i = 0; i < NUM_COUNT; i++) {
            printf("%d ", received_list[i]);
        }
        printf("\n");
    }

    return 0;
}

