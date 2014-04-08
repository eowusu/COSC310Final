package comparison;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Comparison {
        /**
         * The purpose of this class is to search through the libraries to determine if a term is contained in
         * the given library.  The libraries are defined in the text files and correspond to different parts of conversation,
         * such as greetings, and different topics such as arrays.
         */

        /**
         * This method searches for a string in an arraylist, and returns if it is there or not, using a linear scan
         * This is used for verbs, pronouns, negations, affirmations, greetings, etc..
         * @param list the ArrayList<String> that needs to be searched
         * @param str the string that is being searched for
         * @return true if a match is found
         */
        public static boolean contains(ArrayList<String> list, String str){
                boolean result = false;
                for(int i = 0; i<list.size();i++){
                        if (str.toLowerCase().contains(list.get(i).toLowerCase())){
                                result = true;
                                break;
                        }
                }
                return result;
        }
        
        /**
         * This method checks if a String appears as a key in a hashmap. The hashmap keys are assumed to have multiple words
         * separated by commas
         * This is used by topics and details
         * @param map the HashMap that needs to be searched
         * @param str the string that is being searched for
         * @return true if a match is found
         */
        public static boolean contains(HashMap<String,String> map, String str){
                boolean result = false;
                Iterator<String> keySet = map.keySet().iterator(); // returns an iterable list of keys from the hashmap
                String currentKey = keySet.next();
                Scanner scan = new Scanner(currentKey);
                scan.useDelimiter(", *");
                while(currentKey != null&& !result){		//This will loop through the hashmap until a result is found
                        while(scan.hasNext()){				//Once codebot has a key, it looks for matches 
                                String currentString = scan.next().toLowerCase();
                                currentString = Punctuation.space(currentString);
                                if(str.toLowerCase().contains(currentString)){
                                        result = true;	//if codebot finds a match, it now knows the topic they were searching for is in the library
                                        break;		
                                }
                        }
                        if(keySet.hasNext()){		//move to the next entry in the key (keys have multiple words in them, separated by commas)
                                currentKey = keySet.next();
                                scan = new Scanner(currentKey);
                                scan.useDelimiter(", *");
                        }
                        else
                                currentKey = null;
                        
                }
                return result;		//if a match was found, it will return true. otherwise it will return false
        }

        /**
         * This method searches for the presence of any entry in a string array, in the title given to it.
         * @param split The set of words you want to check for the presence of
         * @param title The title in which you are searching for recognized words
         * @return True if one of the words in the "split" array is found inside of title
         */
		public static boolean contains(String[] split, String title) {
            boolean result = false;
            for(int i = 0; i<split.length;i++){
                    if (title.toLowerCase().contains(split[i].toLowerCase())){
                            result = true;
                            break;
                    }
            }
            return result;
		}

}
