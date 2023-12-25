
#include "2004.h"
#include <stdio.h>
#include <unistd.h>
 #include <sys/types.h>
  #include <sys/wait.h>
int main(int argc, char **argv) {
    int pid1, pid2, pid3;
    int cnt = 0;
    int status;

    pid1 = fork();
    if(pid1>0){
             //  wait(&status);
    }
    if (pid1 < 0)
        fprintf(stderr, "Fork Failed");
    else if (pid1 == 0) {  // child
        sleep(1);
        execlp("./task", "task", "1", NULL);
    } else {
                   //     wait(&status);

        pid2 = fork();
        if (pid2 < 0) fprintf(stderr, "Fork Failed");
        if (pid2 == 0) {  // child
            sleep(2);
            execlp("./task", "task", "2", NULL);
        } else {
                // wait(&status);
            pid3 = fork();
            if (pid3 < 0) fprintf(stderr, "Fork Failed");
            if (pid3 == 0) {  // child
                sleep(3);
                execlp("./task", "task", "3", NULL);
            } else {
 if(pid3>0){
            wait(&status);
    } if(pid2>0){
            wait(&status);
    }if(pid1>0){
            wait(&status);
    }               printf("I'll wait for no one!\n");
                execlp("./task", "parent", "0", NULL);
            }
        }
    }

    return (0);
}
