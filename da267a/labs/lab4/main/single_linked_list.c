#include "single_linked_list.h"
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

int addElementSingleLinkedList(struct singleLinkedList *list, int value) {
  struct singleLinkedListElement *e =
      malloc(sizeof(struct singleLinkedListElement));
  e->data = value;
  e->next = NULL;

  struct singleLinkedListElement *curr = list->first;

  if (curr == NULL || curr->data >= value) {
    e->next = curr;
    list->first = e;
  } else {
    while (curr->next) {
      if (curr->next->data > value) {
        break;
      }
      curr = curr->next;
    }

    e->next = curr->next;
    curr->next = e;
  }
  printf("added:%d\n", value);
  return value;
}

void initSingleLinkedList(struct singleLinkedList *list) { list->first = NULL; }

int removeFirstElementSingleLinkedList(struct singleLinkedList *list) {

  if (!list->first) {
    return INT_MIN;
  }
  struct singleLinkedListElement *remove = list->first;
  list->first = list->first->next;
	free(remove);
	printf("removeing first element\n");
	printSingleLinkedList(list);
  return (int)1;
}

int removeLastElementSingleLinkedList(struct singleLinkedList *list) {
  struct singleLinkedListElement *curr = list->first;
  struct singleLinkedListElement *prev = list->first;

  if (!list->first) {
    return INT_MIN;
  }
  while (curr->next) {
    prev = curr;
    curr = curr->next;
  }
  prev->next = NULL;
  free(curr);
	printf("removeing last element\n");
	printSingleLinkedList(list);
  return list->first->data;
}

void printSingleLinkedList(struct singleLinkedList *list) {
	if(!list->first){
		return;
	}
  struct singleLinkedListElement *curr = list->first;
  int c = 0;
	printf("list(first: %d)[", curr->data);
  for (;;) {
    printf("%d ",curr->data);
    if (curr->next == NULL) {
      break;
    }
    curr = curr->next;
    c++;
  }
	printf("]\n");
}
