/*
    Code adapted from Figure 3.21-3.22 Ordinary pipe in UNIX.
*/

#include <sys/types.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
//#define list_size 25
#define READ_END 0
#define WRITE_END 1
#define BUFFER_SIZE 25
int main(int argc, char *argv[]) {
    int fd[2];
    pid_t pid;

    /* create the pipe */
    if ( pipe (fd) == -1) {
        fprintf(stderr,"Pipe failed\n");
        return 1;
    }

    /* fork a child process */
    pid = fork();
    if (pid < 0) { /* error occurred */
        fprintf(stderr, "Fork Failed\n");
        return 1;
    }
   int* arg_list = malloc(argc * sizeof(int));
   int list_size = (argc - 1) * sizeof(int);

    // iterate over the elements of argv and add them to the list
    for (int i1 = 0; i1 < argc-1; i1++) 
    {
        arg_list[i1] = (atoi)(argv[i1+1]);  // use strdup to create a copy of the string
    }
    if (pid > 0) 
    {
        printf("I’m the parent sending ");
          for(int i=0;i<argc-1;i++){
            printf("%d ",arg_list[i]);
        }
                printf("\n");
            
        write(fd[WRITE_END], arg_list,list_size);

        /* close the WRITE_END of the pipe */
        close(fd[WRITE_END]);

        /* wait for the child */
        wait(NULL);
       // printf("Waited for the child\n");
                char read_msg[3*sizeof(double)];

                read(fd[READ_END], read_msg, 3*sizeof(double));
                printf("I’m the parent receiving and printing the result:\n");

                 double *received_list = (double*) read_msg;
       printf("The average value is %f\n",received_list[0]);
printf("The minimum value is %d\n",(int)received_list[1]);
printf("The maximum value is %d\n",(int)received_list[2]);
       
    }
    else { /* child process */
        char read_msg[(argc - 1) * sizeof(int)];

        /* read from the pipe */
        read(fd[READ_END], read_msg, (argc - 1) * sizeof(int));
                int *received_list = (int*) read_msg;
                printf("I’m the child receiving and calculating\n");
                int avg=0;
int min=received_list[1];
int max=0;
        for(int i=0;i<argc-1;i++){
             avg+=received_list[i];
             if(received_list[i]<min)
             {
                min=received_list[i];
             }
             if(received_list[i]>max)
             {
            max=received_list[i];
             }
        }
        double AVG=(double)avg/(argc-1);
        double list[]={AVG,min,max};
        close(fd[READ_END]);
        printf("I’m the child sending the results\n");
        write(fd[WRITE_END], list, 3*sizeof(double));
        close(fd[WRITE_END]);
    }
    return 0;
}
