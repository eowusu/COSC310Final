Codebot
=========

This is the repository COSC 310. The assignment is to create a simple chat agent.  
We chose to have our agent, codebot, function as a teaching assistant or tutor for first year computer 
science.


How to Compile and Run the code:

Download all files and run Test.java

Classes:


Codebot.java is the class that handles all interactions with the user. All other classes are used by this class. This class gathers information from the other classes and uses that information to attempt to answer user questions.  This class also handles regular parts of conversation such as greetings.  If the system is unable to answer a user question then this class will perform an internet search for the topic and show the user the resulting information if it is relevant.

Populate.java is the class that reads text files and stores them as either an arraylist of words (greetings.txt, prompts.txt, verbs.txt, etc), or a hashmap with multiple words as keys, and an explanation of the key as the value (topics.txt, details.txt, etc). This allows us to store a lot of information in text files without needing to search through it all every time that a user enters information to the system, since it will be in memory when the program starts up.

Punctuation.java formats each response to correspond with the way we store words in our libraries. That way we can properly search through them.  Specifically, this class puts extra spaces around words that are near punctuation as we do no store punctuation in out libraries.

Comparison.java searches through the libraries to determine if a term is contained in the given library. The libraries are defined in the text files and correspond to different parts of conversation, such as greetings, and different topics such as arrays.

Matcher.java is the class that checks for possible spelling mistakes and replaces words that aren't understood by the program with words that are more likely to be interpreted correctly. I.e. it would change 'varaibles' into 'variables' (a simple spelling error one might make).

Tag.java implements Stanford’s parts-of-speech tagging library, to take input in the form of text strings representing sentences, and delivers output in the form of arrays containing symbols representing the part of speech that each word of the original sentence was an instance of.

Winui.java is the class responsible for building the graphical user interface, that is used by the Codebot class.

CBnoUI.java is a version of Codebot.java, that does not use the winui.java GUI. This version is used for the networked sockets.

Server.java and Client.java build Sockets, and ServerSockets making the program network capable, and allowing the chat agent to speak with itself, with another automated agent, or with a user in a remote location.

ServerRun.java and ClientRun.java are the classes responsible for building instances of the Server.java and Client.java classes and simulating a conversation between two instances of the chat agent.

Wolf.java is a class that has implemented Wolfram Alpha's developer API and is responsible for properly constructing, executing, and returning information from search queries when information can not be found in one of the local banks of knowledge.

Tweeter.java is a class that given a Twitter App's authoriation information, returns strings containing random tweets from a given Twitter handle

WikiFinder.java is a class that returns parsed out string data from WikiPedia, when information isnt available locally


FINAL UPDATES:

Each of the packages has been made into an easy to use JAR file

The Comparison jar allows the search for the presence for more than one String in a single Array, HashMap or String

The Matcher jar allows fuzzy string matching, which handles things like spelling mistakes and pluralization, and compares the words entered to the set of words that can be recognized by the system

The MyWiki jar searches WikiPedia for pages related to a given query, and uses a series of regular expression to parse the returned text data, and identify general information that might be useful to the user. The text initally returned by the MediaWiki API is almost unreadable and doesnt seem to match any conventional String structure (HTML, JSON etc.)

The MyWolf jar searches Wolfram Alpha, and returns String data that only falls into specific categories such as "Definition" or "Description" with priorities assigned to each type of returned data

The Punctuate jar formats the input sentence so that there are spaces between all words and punctuation, or so that there is no punctuation at all

Other APIS that have been incorporated include:

Google translate, allowing for language speakers to use the program
MediaWiki, allowing for retrieval of information that was not already available from Wolfram Alpha.
Twitter4j, allowing the agent to spur the conversation when the user no longer needs help, suggesting interestng links and things to read from @CompSciFact

