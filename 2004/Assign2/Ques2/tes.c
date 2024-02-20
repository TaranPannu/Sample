#include <stdio.h>
#include <stdlib.h>
struct node {// Node structure for the linked list
    int data;
    struct node* next;
};
typedef struct node Node;
// Global variables for the minimum and maximum PID values
int MIN_PID = 300;
int MAX_PID = 5000;
//the head and previous nodes of the list
Node* head = NULL;
Node* prev = NULL;

void print_pid_list() {// to print the entire list of PID's that are allocated
    Node* curr = head;
    while (curr != NULL) {
        if (curr->data != 0) {
            printf("PID %d: Allocated\n", curr->data);
        }
        curr = curr->next;
    }
    printf("\n");
}

void init_list() {// // Function to allocate a PID from the list
    head = NULL;
    prev = (Node*)malloc(sizeof(Node));
    prev->data = MIN_PID - 1;
    prev->next = NULL;
}

int allocate_pid() {
    if (head != NULL) {// check if head is not null
        if (head->data == 0) {
            head->data = MIN_PID;
            return head->data;
        }
    }
    // Traverse the list to find an available PID
    Node* curr = head;
    while (curr != NULL) {
        if (curr->data == 0) {
            curr->data = prev->data + 1;
            return curr->data;
        }
        prev = curr;
        curr = curr->next;
    }
   
    if (prev->data == MAX_PID) {
        return -1;
    }
    Node* new_node = (Node*)malloc(sizeof(Node));
    new_node->data = prev->data + 1;
    new_node->next = NULL;
    if (head == NULL) {
        head = new_node;
    }
    prev->next = new_node;
    return new_node->data;
}
// Function to release a PID back to the list for reuse
void release_pid(int pid) {
    Node* cur = head;
    while (cur != NULL) {
        if (cur->data == pid) {
            cur->data = 0;
        }
        cur = cur->next;
    }
}
// Main function to test the PID allocator
int main() {
    init_list();
    int p1 = allocate_pid();
    int p2 = allocate_pid();
    int p3 = allocate_pid();
    printf("Allocated PID's\n");
    print_pid_list();
    release_pid(p1);
    release_pid(p3);
    printf("Released PID's %d %d\n", p1, p3);
    printf("List After Realased PID's\n");
    print_pid_list();
    printf("List after allocating PID %d :\n", allocate_pid());
    print_pid_list();
    return 0;
}
