#include <stdio.h>
#include <stdlib.h>

// Global variables for the minimum and maximum PID values
int MIN_PID = 300;
int MAX_PID = 5000;


struct node {// Node structure for the linked list
    int data;
    struct node* next;
};
typedef struct node Node;


//the head and previous nodes of the list
Node* head = NULL;
Node* prev = NULL;



void print_pid_list() {
    // Set the current node to the head of the linked list
    Node* curr = head;

    // Traverse the linked list until the end is reached
    while (curr != NULL) {
        // Check if the current node's data is not equal to 0
        if (curr->data != 0) {
            // If the current node's data is not equal to 0, print a message indicating that the PID is allocated
            printf("PID:   %d\n", curr->data);
        }
        // Move to the next node in the linked list
        curr = curr->next;
    }

    // Print a newline character to separate the output from any subsequent output
    printf("\n");
}

void init_list() {
    // Set the head of the linked list to NULL
    head = NULL;

    // Allocate memory for a new node and set its data to MIN_PID - 1
    Node* prev = (Node*)malloc(sizeof(Node));
    prev->data = MIN_PID;
    prev->next = NULL;

    // Set the new node as the previous node
    head = prev;
}

int allocate_pid() {
    // Check if the head node is not null and its data is 0
    if (head != NULL && head->data == 0) {
        head->data = MIN_PID;
        return head->data;
    }

    // Traverse the list to find an available PID
    Node* curr = head;
    Node* prev = NULL;
    while (curr != NULL) {
        if (curr->data == 0) {
            curr->data = prev->data + 1;
            return curr->data;
        }
        prev = curr;
        curr = curr->next;
    }

    // If all PIDs are allocated, return -1
    if (prev->data == MAX_PID) {
        return -1;
    }

    // Create a new node for the next available PID
    Node* new_node = (Node*)malloc(sizeof(Node));
    new_node->data = prev->data + 1;
    new_node->next = NULL;

    // If the head is null, set the new node as the head
    if (head == NULL) {
        head = new_node;
    } else {
        // Otherwise, set the new node as the next node after the previous node
        prev->next = new_node;
    }

    // Return the allocated PID
    return new_node->data;
}

// Function to release a PID back to the list for reuse
void release_pid(int pid) {
    // Traverse the linked list to find the node with the specified PID
    Node* curr = head;
    Node* prev = NULL;
    while (curr != NULL) {
        if (curr->data == pid) {
            // If the node with the specified PID is found, mark it as unallocated (i.e., set its data to 0)
            curr->data = 0;
            break;
        }
        prev = curr;
        curr = curr->next;
    }

    // If the node was not found, print an error message
    if (curr == NULL) {
        printf("Error: PID %d not found.\n", pid);
        return;
    }

    // If the head node is unallocated, remove it from the list
    if (head != NULL && head->data == 0) {
        head = head->next;
        free(curr);
        return;
    }

    // If the unallocated node is not the head, remove it from the list
    if (prev != NULL) {
        prev->next = curr->next;
        free(curr);
    }
}


// Main function to test the PID allocator
int main() {
    init_list();
    int a1 = allocate_pid();
    int a2 = allocate_pid();
    int a3 = allocate_pid();
    int a4 = allocate_pid();
    int a5 = allocate_pid();
    int a6 = allocate_pid();
    printf("Current Linked List of PID's with integral value:\n");
    print_pid_list();


    release_pid(a1);
    release_pid(a3);
    release_pid(a6);
    printf("Released PID's integral value:  %d %d %d\n", a1, a3, a6);


    printf("Linked List After Realasing above PID's\n");
    print_pid_list();

    printf("Linked List after allocating another PID %d :\n", allocate_pid());
    print_pid_list();
    return 0;
}