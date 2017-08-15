#include "linkedlist.h"
#include <stdlib.h>
#include <string.h>
/* ----------------------------------------- "declarations" ----------------------------------------------------*/
/* Defining the structure of the node */
struct node{
    char *word;
    struct node* next;
    struct lines* nextLine;
};

struct lines{
    int lineNumber;
    struct lines* next;
};

/* creating two pointers of type node
 * *head - will hold the head node (first node in the linked list)
 * *current - used as pointer to the list nodes
 */
struct node *head;
struct node *current;

/* ----------------------------------------- private prototypes --------------------------------------------------*/

/* Creates a full linked with line number.
 * Returns a pointer to the link if succeed
 * Return NULL if malloc has failed*/
struct node *createLink(char* newWord, int wordLine);

/* Returns a new Lines node */
struct lines *createLineLink(int wordLine);

/* ----------------------------------------- Functions ----------------------------------------------------*/
/* initializes the linked list */
void initialize() {
    head = NULL;
    current = NULL;
}

/* Adds a new element to the list and keeping it lexicographically arranged
 * newWord is a string of the new word
 * wordLine is the line that the word has appeared in */
void addElement(char* newWord, int wordLine){
    struct node *listptr;
    /* If the head was empty, fill it and quit the method */
    if(head == NULL){
        head = createLink(newWord,wordLine);
        return;
    }
    /* 1. Check if the input word is lexicographically before the head - Changing the head */
    if(strcmp(newWord,(head->word)) < 0){
        current = head;
        head = createLink(newWord, wordLine);
        head -> next = current;
        return;
    }

    /* Now we are sure that there are at least two nodes */
    current = head;
    listptr = head -> next;
    /* Search lexicographically for the place to put the input word */
    while(listptr != NULL){
        /* 2. If the word already exists */

        if(!strcmp((listptr->word),newWord)){
            /* Check if it's on the same line */
            struct lines *lineptr;
            struct lines *linebeforeptr;
            lineptr = listptr->nextLine;
            while(lineptr != NULL){
                /* If the line number exist, quit the method */
                if((lineptr->lineNumber) == wordLine)
                    return;
                linebeforeptr = lineptr;
                lineptr = lineptr->next;
            }
            /* If we reached this stage, the line does not exist and we need to add it */
            linebeforeptr -> next = createLineLink(wordLine);
            continue;
        } /* End of If statement - end of checking if the word exist */

        /* 3. Check if it's smaller then the current pointer  */
        if(strcmp(newWord,listptr->word) < 0){
            struct node *tempWord;
            tempWord = createLink(newWord,wordLine);
            current -> next = tempWord;
            tempWord -> next = listptr;
            return;
        }

        listptr = listptr -> next;
        current = current -> next;
    } /* End of While */
    /* 4. Check if it's bigger then every other word we already stored */
    current->next = createLink(newWord,wordLine);
    return;
}

/* Creates a full linked with line number.
 * Returns a pointer to the link if succeed
 * Return NULL if malloc has failed*/
struct node *createLink(char* newWord, int wordLine){
    /* Allocating memory for the new node */
    char *newwrd;
    struct lines *newLine;
    struct node *link;
    if((newwrd = (char *) malloc(2000*sizeof(char))) == NULL){
        return NULL;
    }
    strcpy(newwrd,newWord);
    if((link = (struct node*) malloc(sizeof(struct node))) == NULL){
        return NULL;
    }
    link->next = NULL;
    link -> word = newwrd;
    /* Allocating memory for the new line stuct */
    if((newLine = (struct lines*) malloc(sizeof(struct lines)))== NULL){
        return NULL;
    }
    newLine->next = NULL;
    newLine -> lineNumber = wordLine;
    /* Combining them */
    link -> nextLine = newLine;
    /* returning the new link */
    return link;
}

/* Returns a new Lines node */
struct lines *createLineLink(int wordLine){
    struct lines *link;
    if((link = (struct lines*) malloc(sizeof(struct lines))) == NULL){
        return NULL;
    }
    link->next=NULL;
    link -> lineNumber = wordLine;
    return link;
}

/* Prints the list with the line numbers to the specified file */
void printListWithLines(FILE *outFile){
    struct lines *lineptr;
    current = head;
    /* printing the information */
    while(current != NULL){
        fprintf(outFile, "%*s appears in lines ",12, current->word);
        /* Printing the lines */
        lineptr = current->nextLine;
        /* Printing the first line number here to make it more arranged in the output */
        fprintf(outFile,"%d",lineptr->lineNumber);
        lineptr = lineptr -> next;
        while(lineptr != NULL){
            fprintf(outFile,",%d",lineptr->lineNumber);
            lineptr = lineptr -> next;
        }
        /* we finished the current line */
        fprintf(outFile,"\n");
        current = current -> next;
    } /* End of main while loop */
}
