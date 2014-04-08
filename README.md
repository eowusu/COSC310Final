Codebot
=========

This is the repository for assignment 2 in COSC 310. The assignment is to create a simple chat agent.  
We chose to have our agent, codebot, function as a teaching assistant or tutor for first year computer 
science.


How to Compile and Run the code:

Download all files and run Test.java

Classes:

Codebot.java is the class that handles all interactions with the user. All other classes are used by this class. This class gathers information from the other classes and uses that information to attempt to answer user questions.  This class also handles regular parts of conversation such as greetings.  If the system is unable to answer a user question then this class will perform an internet search for the topic and show the user the resulting webpage.

Populate.java is the class that reads text files and stores them as either an arraylist of words (greetings.txt, prompts.txt, verbs.txt, etc), or a hashmap with multiple words as keys, and an explanation of the key as the value (topics.txt, details.txt, etc). This allows us to store a lot of information in text files without needing to search through it all every time that a user enters information to the system, since it will be in memory when the program starts up.

Punctuation.java formats each response to correspond with the way we store words in our libraries. That way we can properly search through them.  Specifically, this class puts extra spaces around words that are near punctuation as we do no store punctuation in out libraries.

Comparison.java searches through the libraries to determine if a term is contained in the given library. The libraries are defined in the text files and correspond to different parts of conversation, such as greetings, and different topics such as arrays.

Matcher.java is the class that checks for possible spelling mistakes and replaces words that aren't understood by the program with words that are more likely to be interpretted correctly. I.e. it would change varaibles into variables (a simple spelling error one might make).

