#include "double_linked_list.h"
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

int addElementDoubleLinkedList(struct doubleLinkedList *list, int value) {
  struct doubleLinkedListElement *e =
      malloc(sizeof(struct doubleLinkedListElement));

  if (!e)
    return INT_MIN;

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
  printf("added:%d\n", value);
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

  if (list->last == list->first) {
    int r = list->first->data;
    free(list->first);
    list->first = list->last = NULL;
    return r;
  }

  struct doubleLinkedListElement *remove = list->first;
  int r = remove->data;
  list->first = remove->next;

  if (list->first) {
    list->first->previous = NULL;
  } else {
    list->last = NULL;
  }

  free(remove);

  printf("removing first element\n");
  printDoubleLinkedList(list);

  return r;
}

int removeLastElementDoubleLinkedList(struct doubleLinkedList *list) {

  if (!list || !list->first) {
    return INT_MIN;
  }

  struct doubleLinkedListElement *remove = list->last;
  int r = remove->data;

  list->last = remove->previous;

  if (list->last) {
    list->last->next = NULL;
  } else {
    list->first = NULL;
  }

  free(remove);

  printDoubleLinkedList(list);
  return r;
}

void printDoubleLinkedList(struct doubleLinkedList *list) {
  if (!list || !list->first) {
    printf("list is empty\n");
    return;
  }

  struct doubleLinkedListElement *curr = list->first;
  printf("list(first: %d, last: %d) [", list->first->data, list->last->data);

  while (curr) {
    printf("%d ", curr->data);
    curr = curr->next;
  }
  printf("]\n");
}
