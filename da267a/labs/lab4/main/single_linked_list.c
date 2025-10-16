#include "single_linked_list.h"
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

int addElementSingleLinkedList(struct singleLinkedList *list, int value) {
  struct singleLinkedListElement *e =
      malloc(sizeof(struct singleLinkedListElement));
  if (!e)
    return INT_MIN;

  e->data = value;
  e->next = NULL;

  if (!list->first || list->first->data >= value) {
    e->next = list->first;
    list->first = e;
    return value;
  }

  struct singleLinkedListElement *curr = list->first;
  while (curr->next && curr->next->data < value) {
    curr = curr->next;
  }

  e->next = curr->next;
  curr->next = e;

  printf("added:%d\n", value);
  return value;
}

void initSingleLinkedList(struct singleLinkedList *list) { list->first = NULL; }

int removeFirstElementSingleLinkedList(struct singleLinkedList *list) {

  if (!list || !list->first)
    return INT_MIN;
  struct singleLinkedListElement *remove = list->first;
  int removed_value = remove->data;
  list->first = remove->next;
  free(remove);
  printf("removing first element\n");
  printSingleLinkedList(list);
  return removed_value;
}

int removeLastElementSingleLinkedList(struct singleLinkedList *list) {
  if (!list || !list->first)
    return INT_MIN;

  struct singleLinkedListElement *curr = list->first;
  struct singleLinkedListElement *prev = list->first;

  if (!curr->next) {
    int val = curr->data;
    free(curr);
    list->first = NULL;
    return val;
  }

  while (curr->next) {
    prev = curr;
    curr = curr->next;
  }
  int val = curr->data;
  prev->next = NULL;
  free(curr);
  printf("removing last element\n");
  printSingleLinkedList(list);
  return val;
}

void printSingleLinkedList(struct singleLinkedList *list) {
  if (!list->first) {
    return;
  }
  struct singleLinkedListElement *curr = list->first;
  printf("list[first: %d] [", curr->data);
  while (curr) {
    printf("%d ", curr->data);
    curr = curr->next;
  }
  printf("]\n");
}
