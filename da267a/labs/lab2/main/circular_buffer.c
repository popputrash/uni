#include "circular_buffer.h"
#include <limits.h>
#include <stdio.h>

void initCircularBuffer(struct circularBuffer *bufferPtr, int *data,
                        int maxLength) {
  bufferPtr->data = data;
  bufferPtr->head = 0;
  bufferPtr->tail = 0;
  bufferPtr->maxLength = maxLength;
}

void printBuffer(struct circularBuffer *bufferPtr) {

  for (int i = 0; i < bufferPtr->maxLength; i++) {
    if (bufferPtr->data[(bufferPtr->head + i) % bufferPtr->maxLength] ==
        INT_MIN) {
      printf("Index[%d] = EMPTY", (bufferPtr->head + i) % bufferPtr->maxLength);
    } else {

      printf("Index[%d] = %d", ((bufferPtr->head + i) % bufferPtr->maxLength),
             bufferPtr->data[(bufferPtr->head + i) % bufferPtr->maxLength]);
    }

    if ((bufferPtr->head + i) % bufferPtr->maxLength == bufferPtr->head) {
      printf("<---HEAD");
    }

    if ((bufferPtr->head + i) % bufferPtr->maxLength == bufferPtr->tail) {
      printf("<---TAIL");
    }

    printf("\n");
  }
}

int contains(struct circularBuffer *bufferPtr, int value) {
  for (int i = 0; i < bufferPtr->maxLength; i++) {
    if (bufferPtr->data[i] == value) {
      return value;
    }
  }
  return INT_MIN;
}

int addElement(struct circularBuffer *bufferPtr, int value) {
  if ((bufferPtr->tail + 1) % bufferPtr->maxLength == bufferPtr->head) {
    return INT_MIN;
  }
  bufferPtr->data[bufferPtr->tail] = value;
  bufferPtr->tail = (bufferPtr->tail + 1) % bufferPtr->maxLength;
  return value;
}

int removeValue(struct circularBuffer *bufferPtr, int value) {
  int temp[bufferPtr->maxLength];
  int c = 0;
  int r = INT_MIN;
  int a = removeHead(bufferPtr);
  while (a != INT_MIN) {
    if (a != value) {
      temp[c] = a;
      c++;
    } else {
      r = value;
    }
    a = removeHead(bufferPtr);
  }

  for (int i = 0; i < c; i++) {
    addElement(bufferPtr, temp[i]);
  }
  return r;
}

int removeHead(struct circularBuffer *bufferPtr) {
  int r;
  if (bufferPtr->head == bufferPtr->tail) {
    return INT_MIN;
  }

  r = bufferPtr->data[bufferPtr->head];
  bufferPtr->data[bufferPtr->head] = INT_MIN;
  bufferPtr->head = (bufferPtr->head + 1) % bufferPtr->maxLength;

  return r;
}
