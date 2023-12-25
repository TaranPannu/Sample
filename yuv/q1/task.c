/*
    Code of a simple program that prints its name and the first parameter sleet for 10 seconds print again and return 0
    Do not change this file
*/

#include <stdio.h>
#include <unistd.h> // sleep

int main(int argc, char **argv) {
  printf ("%s %s start ... \n", argv[0], argv[1]);
  sleep(10);
  printf ("%s %s end\n", argv[0], argv[1]);
  return(0);  // done.
}

