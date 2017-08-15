#ifndef MAMAN22_UTILS_H
#define MAMAN22_UTILS_H
#include <stdio.h>

/* Prompt messages */
#define NEWLINE 10001

/* This "class" is used for helpers methods -- made to keep the main file cleaner and more readable and modular */

/* Skip everything that is not alphanumeric value before that start of the word */
int skipDelim(FILE *file);

/* Gets a word from the specified file
 * We assume that a word will not exceed 2000 chars - I read on the forum that this is possible in this maman */
int getWord(FILE *file, char *word);

#endif