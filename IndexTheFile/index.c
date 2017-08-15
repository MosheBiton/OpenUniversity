#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "utils.h"
#include <string.h>
#define MAXWORDLENGTH 2000

/* ----------------------------------------- Function Prototypes ----------------------------------------------------*/
/* Checking if the arguments provided is at the right amount and the file can be opened.
 * If success returns a pointer to the file. Otherwise, prints the error to stdout and exit the program*/
FILE *checkArgs(int argc, char *argv[]);
/* Parsing the file for words and insert them into the linked list */
void getWordsInsideLinkedList(FILE *inFile);
/* ----------------------------------------- Main Method ----------------------------------------------------*/

int main(int argc, char *argv[]) {
    FILE *inFile, *outFile;
    char *outputfilename;
    inFile = checkArgs(argc, argv);
    /* initializes the linked list */
    initialize();
    getWordsInsideLinkedList(inFile);
    /* Opens the output file */ /* How do I get the inFile file name and pass it to the outfile? */
    outputfilename = strcat(argv[1], ".index");
    if((outFile = fopen(outputfilename, "w")) == NULL){
		fclose(inFile);
        fprintf(stderr, "Cannot open the output file");
        exit(0);
    }
    /* Printing the list */
    printListWithLines(outFile);

    /* Closing the files */
    fclose(inFile);
    fclose(outFile);
    /* returning 0 as main function has to return a value and 0 is considered as a success token */
    return 0;
}

/* ----------------------------------------- Functions ----------------------------------------------------*/

/* Checking if the arguments provided is at the right amount and the file can be opened.
 * If success returns a pointer to the file. Otherwise, prints the error to stdout and exit the program*/
FILE *checkArgs(int argc, char *argv[]) {
    FILE *inFile;
    /* Checks if we miss an argument */
    if (argc == 1) {
        fprintf(stderr, "No Arguments Provided\n");
        exit(0);
    }
    /* Checking if too many arguments were inserted */
    if (argc > 2) {
        fprintf(stderr, "Too Many Arguments Were Provided\n");
        exit(0);
    }
    /* If the first tests passed, we check if we can open the file */
    if ((inFile = fopen(argv[1], "r")) == NULL) {
        fprintf(stderr, "%s: cannot open file %s for read\n", argv[0], argv[1]);
        exit(0);
    }
    return inFile;
    /* Everything is okay, we have an open file */
}

/* Parsing the file for words and insert them into the linked list */
void getWordsInsideLinkedList(FILE *inFile){
    int input, lineNumber, getError;
    /* We assume the word is 2000 chars at max. the +1 is for the /0 */
    char *wordptr;
    wordptr = (char*) malloc((MAXWORDLENGTH+1)* sizeof(char));
    lineNumber = 1;
    while((input = skipDelim(inFile)) != EOF){
        char *word;
        word = wordptr;
        /* If the next char is '\n' then we increment the lineNumber and continue to the next iteration */
        if(input == NEWLINE){
            lineNumber++;
            continue;
        }
        *wordptr = (char)input;
        getError = getWord(inFile,++wordptr);
        if(getError == EOF){
            addElement(word,lineNumber);
            return;
        }
        if(getError == NEWLINE)
            lineNumber++;
        addElement(word,lineNumber);
    } /* End of While loop */
}
