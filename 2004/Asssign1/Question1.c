#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(){
    int i=0;
for(i = 0; i < 100; i = i + 1)
{
          if(i!=99)
            printf("%d, ", i%5);
            else
printf("%d. ", i%5);
    }
                
    printf("\n\n");

    for(i = 0; i < 100; i = i + 1)
    {
                  if(i!=99)
            printf("%d, ", i%10);
            else
                    printf("%d. ", i%10);

            
    }
    printf("\n\n");
    for(i = 0; i < 100; i = i + 1)
    {
                  if(i!=99)
            printf("%d, ", i%27);
            else
                                printf("%d. ", i%27);
    }
printf("\n\n");
    for(i = 0; i < 100; i = i + 1)
    {
                  if(i!=99)
            printf("%d, ", i%100);
            else
                            printf("%d. ", i%100);


    }


}