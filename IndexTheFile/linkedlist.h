#ifndef MAMAN_12_LINKEDLIST_H
#define MAMAN_12_LINKEDLIST_H
#include <stdio.h>

/* initializes the linked list */
void initialize();

/* Adds a new element to the list and keeping it lexicographically arranged
 * newWord is a string of the new word
 * wordLine is the line that the word has appeared in */
void addElement(char* newWord, int wordLine);

/* Prints the list with the line numbers to the specified file */
void printListWithLines(FILE *outFile);

#endif
