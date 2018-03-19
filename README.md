# Plagiarism-Detection
Command Line project for Plagiarism Detection
//Ze Wang's
*Based on Intellij IDEA*

IMPORTANT ASSUMPTIONS
are detailed in each classes with examples
However, for conveniences, major assumptions are gathered here:
1. Words in the input file is space divided only!
    Otherwise you have to define other Regex to split lines
2. A new line in the file means a new start point to count
   and record tuples. Words in the previous line will not be
   taken into account.
3. Assuming lowercase and uppercase the same. Therefore,
   load all into lowercase.
4. Different line of words in synonyms file means
   different group of Synonyms words.
5. During the counting plagiarism tuple process,
   assuming assuming one match between two tuple from each file
   is enough for a plagiarism.
6. Assuming the default working directory is the src folder of this project.

RUN THE PROGRAM:
Please using the command line option to run PlagiarismDec.java
With 3 / 4 args, first three is synonyms file name, base file name, reference file name
the last args can be the size of tuple, in default, it's 3

Example Execution args: syns.txt file1.txt file2.txt 3

ABSTRACT CLASS - MATCH
where you can use / override / modify
the ismatch method in this abstract class for further use.

Main Compare tuple and count for plagiarism tuples are within Compare.java and Match.java
