#include "circular_buffer.h"
#include <esp32/rom/ets_sys.h>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

/*
 * Change the value of BUFFER_SIZE if you want to change the size of the buffer.
 */
#define BUFFER_SIZE 4


struct circularBuffer buffer;

void test1(){

  printf("----------Test 1----------- \n");
  printf("added %d \n", addElement(&buffer, 20));
  printf("removed head %d \n", removeHead(&buffer));
	printBuffer(&buffer);

}
void test2(){
	printf("----------Test 2-----------\n");
  printf("added %d \n", addElement(&buffer, 20));
  printf("added %d \n", addElement(&buffer, 10));
  printf("removed head %d \n", removeHead(&buffer));
  printf("removed head %d \n", removeHead(&buffer));
	printBuffer(&buffer);
}

void test3(){
  printf("-----------Test 3----------\n");
  for (int i = 0; i < BUFFER_SIZE; i++) {
    printf("added %d \n", addElement(&buffer, i));
  }

  for (int i = 0; i < BUFFER_SIZE; i++) {
    printf("removed head %d \n", removeHead(&buffer));
  }
	printBuffer(&buffer);
}
void test4(){

  printf("----------Test 4----------\n");
  for (int i = 0; i <= BUFFER_SIZE; i++) {
    printf("added %d \n", addElement(&buffer, i));
  }

  for (int i = 0; i <= BUFFER_SIZE; i++) {
    printf("removed head %d \n", removeHead(&buffer));
  }
	printBuffer(&buffer);
}
void test5(){
  printf("------------Test 5----------\n");
  for (int i = 0; i <= BUFFER_SIZE + 1; i++) {
    printf("added %d \n", addElement(&buffer, 20));
    printf("removed head %d \n", removeHead(&buffer));
  }
	printBuffer(&buffer);
}
void test6(){
  printf("-------------Test 6----------\n");
  printf("found %d \n", contains(&buffer, 10));
	printBuffer(&buffer);
}
void test7(){
  printf("------------Test 7------------\n");
  printf("added %d \n", addElement(&buffer, 1));
  printf("found %d \n", contains(&buffer, 1));
	printBuffer(&buffer);
}
void test8(){
  printf("-------------Test 8-----------\n");
  printf("added %d \n", addElement(&buffer, 5));
  printf("found %d \n", contains(&buffer, 5));
	printBuffer(&buffer);
}
void test9(){
  printf("-------------Test 9------------\n");
	for(int i = 0; i < BUFFER_SIZE; i++){
  	printf("added %d \n", addElement(&buffer, i*10));
	}
  printf("found %d \n", contains(&buffer, BUFFER_SIZE*10));
	printBuffer(&buffer);
}

void test10(){
	printf("-------------Test 10------------\n");
	while(removeHead(&buffer) != INT_MIN){}
	printBuffer(&buffer);
	for(int i = 0; i < BUFFER_SIZE; i++){
  	printf("added %d \n", addElement(&buffer, i*10));
	}
	printBuffer(&buffer);
	removeValue(&buffer, 10);
	removeValue(&buffer, 30);
	printf("Removed 10 & 30 \n");
	printBuffer(&buffer);
}


void app_main() {

  int *buffer_data = (int *)malloc(BUFFER_SIZE * sizeof(int));
  initCircularBuffer(&buffer, buffer_data, BUFFER_SIZE);

  test1();
	test2();
	test3();
	test4();
	test5();
	test6();
	test7();
	test8();
	test9();
	test10();
  free(buffer_data);
}
