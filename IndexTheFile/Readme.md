About this program:

Written in C90.

The program takes a file as an argument, parses it and output an "index" file. The index file contains all the words in the file arranged in a dictionary order with the line numbers that the word appears in the original file near each word.

* Note that the program does not count "how many times a word appears" since it ignores the second appearance of the same word in the same line.

To run the program you may use the following line in the shell: gcc -Wall index.c linkedlist.c utils.c -o index ------- or use the makefile to compile by running the "make" command
and then
./index.o [arguments]

Hope you will find this useful.