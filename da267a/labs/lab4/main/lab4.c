#include <stdio.h>
#include <stdlib.h>

#include "double_linked_list.h"
#include "esp_random.h"
#include "single_linked_list.h"

void test1() {
  printf("----Sorted insert test----\n");

  printf("single linked list: \n");
  struct singleLinkedList list;
  initSingleLinkedList(&list);
  for (int i = 0; i < 5; i++) {
    addElementSingleLinkedList(&list, esp_random() % 100);
  }
  printSingleLinkedList(&list);

  printf("\n\ndouble linked list: \n");

  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  for (int i = 0; i < 5; i++) {
    addElementDoubleLinkedList(&dlist, esp_random() % 100);
  }

  printDoubleLinkedList(&dlist);
}

void test2() {

  printf("----Removeing elements test----\n");

  printf("single linked list: \n");
  struct singleLinkedList list;
  initSingleLinkedList(&list);
  for (int i = 0; i < 5; i++) {
    addElementSingleLinkedList(&list, esp_random() % 100);
  }
  printSingleLinkedList(&list);
  printf("%d removed\n", removeFirstElementSingleLinkedList(&list));

  printf("%d removed\n", removeLastElementSingleLinkedList(&list));

  printf("\n\ndouble linked list: \n");
  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  for (int i = 0; i < 5; i++) {
    addElementDoubleLinkedList(&dlist, esp_random() % 100);
  }

  printDoubleLinkedList(&dlist);

  printf("%d removed\n", removeFirstElementDoubleLinkedList(&dlist));

  printf("%d removed\n", removeLastElementDoubleLinkedList(&dlist));
}

void test3() {
  printf("----Remove from empty list----\n");
  printf("remove from empty sll\n");
  struct singleLinkedList slist;
  initSingleLinkedList(&slist);
  printf("remove first: %d\n", removeFirstElementSingleLinkedList(&slist));
  printf("remove last: %d\n", removeLastElementSingleLinkedList(&slist));

  printf("remove from empty dll\n");
  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  printf("remove first: %d\n", removeFirstElementDoubleLinkedList(&dlist));
  printf("remove last: %d\n", removeLastElementDoubleLinkedList(&dlist));
}

void test4() {
  printf("\n\n----Adding duplicate values----\n");

  printf("single linked list: \n");
  struct singleLinkedList slist;
  initSingleLinkedList(&slist);
  addElementSingleLinkedList(&slist, 1);
  addElementSingleLinkedList(&slist, 1);
  printSingleLinkedList(&slist);

  printf("double linked list: \n");
  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  addElementDoubleLinkedList(&dlist, 1);
  addElementDoubleLinkedList(&dlist, 1);
  printDoubleLinkedList(&dlist);
}

void test5() {

  printf("\n\n----Adding values to beginning----\n");

  printf("single linked list: \n");
  struct singleLinkedList slist;
  initSingleLinkedList(&slist);
  addElementSingleLinkedList(&slist, 1);
  addElementSingleLinkedList(&slist, 1);
  addElementSingleLinkedList(&slist, 0);
  printSingleLinkedList(&slist);

  printf("double linked list: \n");
  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  addElementDoubleLinkedList(&dlist, 1);
  addElementDoubleLinkedList(&dlist, 1);
  addElementDoubleLinkedList(&dlist, 0);
  printDoubleLinkedList(&dlist);

  printf("\n\n----Adding values to end----\n");

  printf("single linked list: \n");
  addElementSingleLinkedList(&slist, 3);
  printSingleLinkedList(&slist);

  printf("double linked list: \n");
  addElementDoubleLinkedList(&dlist, 3);
  printDoubleLinkedList(&dlist);
}

void test6() {
  printf("\n\n ----Removeing only element----");
  printf("single linked list: \n");
  struct singleLinkedList slist;
  initSingleLinkedList(&slist);
  addElementSingleLinkedList(&slist, 1);
  printSingleLinkedList(&slist);
  printf("removed: %d", removeFirstElementSingleLinkedList(&slist));

  printf("double linked list: \n");
  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  addElementDoubleLinkedList(&dlist, 1);
  printDoubleLinkedList(&dlist);
  printf("removed: %d ", removeLastElementDoubleLinkedList(&dlist));
}

void app_main() {

  struct singleLinkedList slist;
  initSingleLinkedList(&slist);

  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  test1();

  test2();

  test3();

  test4();

  test5();

  test6();
}
