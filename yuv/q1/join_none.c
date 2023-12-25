/*
  Program that fork 3 children and implements join_none
*/

#include <stdio.h>
#include <unistd.h>
// #include <sys/wait.h>

int main(int argc, char **argv) {
    int pid1, pid2, pid3;
    int cnt = 0;
    int status;

    pid1 = fork();
    if (pid1 < 0)
        fprintf(stderr, "Fork Failed");
    else if (pid1 == 0) {  // child
        sleep(1);
        execlp("./task", "task", "1", NULL);
    } else {
        pid2 = fork();
        if (pid2 < 0) fprintf(stderr, "Fork Failed");
        if (pid2 == 0) {  // child
            sleep(2);
            execlp("./task", "task", "2", NULL);
        } else {
            pid3 = fork();
            if (pid3 < 0) fprintf(stderr, "Fork Failed");
            if (pid3 == 0) {  // child
                sleep(3);
                execlp("./task", "task", "3", NULL);
            } else {
                printf("I'll wait for no one!\n");
                execlp("./task", "parent", "0", NULL);
            }
        }
    }

    return (0);
}
