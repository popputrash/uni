#include <stdio.h>
#include <stdlib.h>

#include "double_linked_list.h"
#include "esp_random.h"
#include "single_linked_list.h"

int verifySingleLinkedListSorted(struct singleLinkedList *list) {
  struct singleLinkedListElement *curr = list->first;
  while (curr && curr->next) {
    if (curr->data > curr->next->data) {
      return 0; // not sorted
    }
    curr = curr->next;
  }
  return 1;
}

// Verifies that a doubly linked list is sorted and all previous/next pointers
// are consistent.
int verifyDoubleLinkedListStructure(struct doubleLinkedList *list) {
  struct doubleLinkedListElement *curr = list->first;
  while (curr && curr->next) {
    if (curr->data > curr->next->data)
      return 0;
    if (curr->next->previous != curr)
      return 0;
    curr = curr->next;
  }
  if (list->last && list->last->next != NULL)
    return 0;
  return 1;
}

// Counts nodes in a singly linked list
int countNodesSingle(struct singleLinkedList *list) {
  int count = 0;
  struct singleLinkedListElement *curr = list->first;
  while (curr) {
    count++;
    curr = curr->next;
  }
  return count;
}

// Counts nodes in a doubly linked list
int countNodesDouble(struct doubleLinkedList *list) {
  int count = 0;
  struct doubleLinkedListElement *curr = list->first;
  while (curr) {
    count++;
    curr = curr->next;
  }
  return count;
}

void test1_sortedInsert_basicInsertion() {
  printf("----Sorted insert test----\n");

  printf("single linked list: \n");
  struct singleLinkedList list;
  initSingleLinkedList(&list);
  for (int i = 0; i < 5; i++) {
    addElementSingleLinkedList(&list, random() % 100);
  }
  printSingleLinkedList(&list);

  printf("\n\ndouble linked list: \n");

  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  for (int i = 0; i < 5; i++) {
    addElementDoubleLinkedList(&dlist, random() % 100);
  }

  printDoubleLinkedList(&dlist);
}

void test2_removeElements_basicRemoval() {

  printf("----Removeing elements test----\n");

  printf("single linked list: \n");
  struct singleLinkedList list;
  initSingleLinkedList(&list);
  for (int i = 0; i < 5; i++) {
    addElementSingleLinkedList(&list, random() % 100);
  }
  printSingleLinkedList(&list);
  printf("%d removed\n", removeFirstElementSingleLinkedList(&list));

  printf("%d removed\n", removeLastElementSingleLinkedList(&list));

  printf("\n\ndouble linked list: \n");
  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  for (int i = 0; i < 5; i++) {
    addElementDoubleLinkedList(&dlist, random() % 100);
  }

  printDoubleLinkedList(&dlist);

  printf("%d removed\n", removeFirstElementDoubleLinkedList(&dlist));

  printf("%d removed\n", removeLastElementDoubleLinkedList(&dlist));
}

void test3_removeFromEmptyList_edgeCase() {
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

void test4_insertingDuplicates_edgeCase() {
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

void test5_insertAtBeginningAndEnd_edgeCase() {

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

void test6_removeOnlyElement_edgeCase() {
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
void test7_verify_structure() {
  printf("\n\n----Test 7: Structural integrity check----\n");

  struct singleLinkedList slist;
  struct doubleLinkedList dlist;

  initSingleLinkedList(&slist);
  initDoubleLinkedList(&dlist);

  addElementSingleLinkedList(&slist, 2);
  addElementSingleLinkedList(&slist, 4);
  addElementSingleLinkedList(&slist, 1);

  addElementDoubleLinkedList(&dlist, 2);
  addElementDoubleLinkedList(&dlist, 4);
  addElementDoubleLinkedList(&dlist, 1);

  printSingleLinkedList(&slist);
  printDoubleLinkedList(&dlist);

  printf("Singly linked list sorted: %s\n",
         verifySingleLinkedListSorted(&slist) ? "YES" : "NO");

  printf("Doubly linked list structure valid: %s\n",
         verifyDoubleLinkedListStructure(&dlist) ? "YES" : "NO");
}

void test8_small_sequence_check() {
  printf("\n\n----Test 8: Inserting [3,1,4,2] and checking sort----\n");

  struct singleLinkedList slist;
  struct doubleLinkedList dlist;

  initSingleLinkedList(&slist);
  initDoubleLinkedList(&dlist);

  int vals[] = {3, 1, 4, 2};

  for (int i = 0; i < 4; i++) {
    addElementSingleLinkedList(&slist, vals[i]);
    addElementDoubleLinkedList(&dlist, vals[i]);
  }

  printSingleLinkedList(&slist);
  printDoubleLinkedList(&dlist);

  printf("Singly linked list sorted: %s\n",
         verifySingleLinkedListSorted(&slist) ? "YES" : "NO");

  printf("Doubly linked list structure valid: %s\n",
         verifyDoubleLinkedListStructure(&dlist) ? "YES" : "NO");
}
void test9_stress_test() {
  printf("\n\n----Test 9: Stress test with 100 elements----\n");

  struct singleLinkedList slist;
  struct doubleLinkedList dlist;

  initSingleLinkedList(&slist);
  initDoubleLinkedList(&dlist);

  for (int i = 0; i < 100; i++) {
    int val = random() % 1000;
    addElementSingleLinkedList(&slist, val);
    addElementDoubleLinkedList(&dlist, val);
  }

  printf("Single list node count: %d\n", countNodesSingle(&slist));
  printf("Double list node count: %d\n", countNodesDouble(&dlist));

  printf("Singly linked list sorted: %s\n",
         verifySingleLinkedListSorted(&slist) ? "YES" : "NO");

  printf("Doubly linked list structure valid: %s\n",
         verifyDoubleLinkedListStructure(&dlist) ? "YES" : "NO");

  // (optional) You can also time the operations or run memory leak check here
}
void app_main() {

  struct singleLinkedList slist;
  initSingleLinkedList(&slist);

  struct doubleLinkedList dlist;
  initDoubleLinkedList(&dlist);
  test1_sortedInsert_basicInsertion();
  test2_removeElements_basicRemoval();
  test3_removeFromEmptyList_edgeCase();
  test4_insertingDuplicates_edgeCase();
  test5_insertAtBeginningAndEnd_edgeCase();
  test6_removeOnlyElement_edgeCase();
	test7_verify_structure();
	test8_small_sequence_check();
	test9_stress_test();

}
