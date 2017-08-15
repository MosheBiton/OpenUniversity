#include "utils.h"
#include <ctype.h>

/* Skip everything that is not alphanumeric value before that start of the word */
int skipDelim(FILE *file){
    int input;
    while(!(isalnum(input = fgetc(file)))){
        /* We check if we reached a necessary char */
        if(input == '\n')
            return NEWLINE;
        if(input == EOF)
            return EOF;
    }
    return input;
}

/* Gets a word from the specified file
 * We assume that a word will not exceed 2000 chars */
int getWord(FILE *file, char *word){
    int input,i;
    for(i = 0; (isalnum(input = fgetc(file))); i++){
        *word++ = (char)input;
    }
    /* We reached a non alphanumeric char so we can close the word */
    word[i] = '\0';
    /* We check if we reached a necessary char */
    if(input == '\n'){
        return NEWLINE;
    }
    if(input == EOF){
        return EOF;
    }
    return 1;
}