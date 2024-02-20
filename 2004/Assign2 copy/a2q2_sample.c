/*
This file shows a sample structure that may be used for Assignment 2 Question 2
Fell free to edit any aspect of this file as long as you are following the requested api
*/

// Include any libraries that may be required
#include <stdio.h>

#define MIN_PID 300
#define MAX_PID 5000

// Functions prototypes
int allocate_map(void);
int allocate_pid(void);
void release_pid(int pid);
int print_pid_list();

int main(){
    // Initialize the list
    // Create a loop that allocate a number of pids
    // Release some of the allocated pids
    // You may write and use the print_pid_list() to show your list working
}

int init_pid_list() {
    // Creates and initializes a data structure for representing pids
    // Hint: review what is meant by an empty linked list
    // The solution here is trivial, don’t overthink
}

int allocate_pid(void) {
    // Allocates and returns a pid
    // returns −1 if unable to allocate a pid (all pids are in use)
}

void release_pid(int pid) {
    // Releases a pid
}

int print_pid_list() {
    // Print the list of PIDs
}