#include "double_linked_list.h"
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

int addElementDoubleLinkedList(struct doubleLinkedList *list, int value) {
  struct doubleLinkedListElement *e =
      malloc(sizeof(struct doubleLinkedListElement));
  e->data = value;
  e->next = NULL;
  e->previous = NULL;


  if (!list->first) {
    list->first = list->last = e;
  } else if (list->first->data >= value) {
    e->next = list->first;
    list->first->previous = e;
    list->first = e;
  } else if (list->last->data <= value) {
    e->previous = list->last;
    list->last->next = e;
    list->last = e;
  } else {
		struct doubleLinkedListElement *curr = list->first;
    while (curr->next) {

      if (curr->next->data > value) {
        break;
      }
      curr = curr->next;
    }

    e->next = curr->next;
    e->previous = curr;
    curr->next->previous = e;
    curr->next = e;
  }
  printf("added %d\n", value);
  return value;
}

void initDoubleLinkedList(struct doubleLinkedList *list) {
  list->first = NULL;
  list->last = NULL;
}

int removeFirstElementDoubleLinkedList(struct doubleLinkedList *list) {

  if (!list->first) {
    return INT_MIN;
  }

	if(list->last == list->first){
		int r = list->last->data;
		free(list->last);
		return r;
	}	

  struct doubleLinkedListElement *temp = list->first->next;
  free(list->first);

  list->first = temp;
  list->first->next->previous = list->first;
  list->first->previous = NULL;
	printf("removeing first element\n");
  printDoubleLinkedList(list);

  return (int)list->first->data;
}

int removeLastElementDoubleLinkedList(struct doubleLinkedList *list) {

	printf("removing last element\n");
  if (!list->first) {
    return INT_MIN;
  }


	if(list->last == list->first){
		int r = list->last->data;
		free(list->last);
		return r;
	}	

  struct doubleLinkedListElement *remove = list->last;
  list->last = remove->previous;
  list->last->next = NULL;
  free(remove);
  printDoubleLinkedList(list);
  return list->first->data;
}

void printDoubleLinkedList(struct doubleLinkedList *list) {
  struct doubleLinkedListElement *curr = list->first;
  printf("list(first:%d, last%d )[", list->first->data, list->last->data);
  for (;;) {
    printf("%d ", curr->data);
    if (curr->next == NULL) {
      break;
    }
    curr = curr->next;
  }
  printf("]\n");
}
