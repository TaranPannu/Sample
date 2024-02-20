#include <stdio.h>
#include <stdlib.h>
#include <pthread.h> //for thread
// #include<unistd.h>// for Unix & Ubuntu
// data member
int sum = 0; // store sum value
int read_val = 0;
int rw_bit = 1;
void *read_thd()
{
    do
    {
        while (!rw_bit) // Upto rw_bit becomes one
            Sleep(1000);
        int val; // read the value
        printf("Input a number : ");
        scanf("%d", &val);
        read_val = val; // store the val in shared memory
        rw_bit = 0;
        if (val < 0)
        {
            pthread_exit(NULL);
        }
    } while (1);
}
// sum of the thread function
void *sum_thd()
{
    do
    {
        while (rw_bit)
            Sleep(1000);
        int val = read_val;
        if (val < 0)
        { // if the value is -ve, exit
            // untill negative value
            pthread_exit(NULL);
        }
        sum += val; // sum=sum+val;
        rw_bit = 1;
    } while (1);
}
int main()
{
    pthread_t read_t_id;
    pthread_t sum_t_id;
    // creating the read thread
    pthread_create(&read_t_id, NULL, read_thd, NULL);
    // created sum of the thread
    pthread_create(&sum_t_id, NULL, sum_thd, NULL);
    // block the main thread until the sum thread exits
    pthread_join(sum_t_id, NULL);
    printf("Sum is %d\n", sum);
    return 0;
}
