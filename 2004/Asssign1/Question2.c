#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main(int m,char *argv[]){
  //   argv[1] = '\0';
    if(m>=2)
    {
    int n=atoi(argv[1]);
if(n<=0){
    printf("Please enter valid number");
    return 1;
}
    printf("Multiplication table for Z%s:\n",argv[1]);
    printf("  ");
    for(int i=0;i<n;i++){
        printf("%d ",i);
    }
    printf("\n");
    for(int b=0;b<n;b++){
        printf("%d ",b);
for(int a=0;a<n;a++){
printf("%d ",(a*b)%n);
    }
        printf("\n");

    }
}else
printf("Nothing is passed as a input");
}