#include <stdio.h>
#include <stdlib.h>
#include <math.h>


    struct LinkedList{
    int data;
    struct LinkedList *next;
 };
 typedef struct LinkedList *node; 
 node createNode(){
    node temp; // declare a node
    temp = (node)malloc(sizeof(struct LinkedList)); // allocate memory using malloc()
    temp->next = NULL;// make next point to NULL
    return temp;
}
float var(node head,int c,float mean){
    node p;
p = head;
int i=0;
float v=0;
while(i<c){
v+=((((float)p->data)-mean)*(((float)p->data)-mean))/(float)(c);
    p = p->next;
   i++;
}
return v;
}
int main () {

        char mystring[10000];
 int i=0;
FILE *fp = fopen("sensor.csv","r");
float sum=0;
int c=0;
node head;
  node temp,p;
while(fscanf(fp, "%s", mystring) != EOF)
{
      sum+=atoi(mystring);
c++;
    temp = createNode();
    temp->data = atoi(mystring); 
    temp->next=head;
    head=temp;
}

float varr=var(head,c,(float)(sum/c));
printf("Count: %d\n",c);
printf("Sum: %d\n",(int)sum);
printf("Mean: %f\n",(float)(sum/c));
printf("Variance: %f\n",varr);
printf("Standard Deviation: %f\n",sqrt(varr));

 
}
