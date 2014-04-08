import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Populate {
        /*
         * The purpose of this class is to put our library into RAM for fast access.
         * For arraylist populations, words are separated by commas
         * For hashmap populations, commas are used to separate multiple words in a key 
         * and ,~ separates the key from the value.  This allows us to store a lot of information
         * in text files without needing to search through it all every time that a user enters 
         * information to the system.
         */

        /*
         * This method populates the list of greetings
         */
        public static ArrayList<String> greetings() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner greeter;
                try {
                        greeter = new Scanner(new File("Greetings.txt"));
                        greeter.useDelimiter(", *");
                        while (greeter.hasNext()){
                                temp.add(" "+greeter.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        public static ArrayList<String> common() {
            ArrayList<String> temp = new ArrayList<String>();
            Scanner common;
            try {
                    common = new Scanner(new File("mostcommon.txt"));
                    common.useDelimiter(", *");
                    while (common.hasNext()){
                            temp.add(" "+common.next()+" ");
                    }
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            }
            return temp;
    }

        /*
         * This method populates the list of closures
         */

        public static ArrayList<String> closures() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner closer;
                try {
                        closer = new Scanner(new File("Closures.txt"));
                        closer.useDelimiter(", *");
                        while (closer.hasNext()){
                                temp.add(" " +closer.next()+ " ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the list of prompts
         */
        public static ArrayList<String> prompts() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner prompter;
                try {
                        prompter = new Scanner(new File("Prompts.txt"));
                        prompter.useDelimiter(", *");
                        while (prompter.hasNext()){
                                temp.add(" " +prompter.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the list of topicprompts
         */
        public static ArrayList<String> topicprompts() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner topicprompter;
                try {
                        topicprompter = new Scanner(new File("Topicprompts.txt"));
                        topicprompter.useDelimiter(", *");
                        while (topicprompter.hasNext()){
                                temp.add(" " +topicprompter.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the list of reprompts
         */
        public static ArrayList<String> reprompts() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner reprompter;
                try {
                        reprompter = new Scanner(new File("Reprompts.txt"));
                        reprompter.useDelimiter(", *");
                        while (reprompter.hasNext()){
                                temp.add(" " +reprompter.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the list of affirmations
         */
        public static ArrayList<String> affirmations() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner affirmationer;
                try {
                        affirmationer = new Scanner(new File("Affirmations.txt"));
                        affirmationer.useDelimiter(", *");
                        while (affirmationer.hasNext()) {
                                temp.add(" "+affirmationer.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the list of negations
         */
        public static ArrayList<String> negations() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner negationer;
                try {
                        negationer = new Scanner(new File("Negations.txt"));
                        negationer.useDelimiter(", *");
                        while (negationer.hasNext()){
                                temp.add(" "+negationer.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the list of inquiries
         */
        public static ArrayList<String> inquiries() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner inquirer;
                try {
                        inquirer = new Scanner(new File("Inquiries.txt"));
                        inquirer.useDelimiter(", *");
                        while (inquirer.hasNext()){
                                temp.add(" "+inquirer.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the compliments
         */
        public static ArrayList<String> compliments() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner complimenter;
                try {
                        complimenter = new Scanner(new File("Compliments.txt"));
                        complimenter.useDelimiter(", *");
                        while (complimenter.hasNext()){
                                temp.add(" "+complimenter.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the acknowledgements
         */
        public static ArrayList<String> acknowledgements() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner acknowledgementer;
                try {
                        acknowledgementer = new Scanner(new File("Acknowledgements.txt"));
                        acknowledgementer.useDelimiter(",~ *");
                        while (acknowledgementer.hasNext()){
                                temp.add(" "+acknowledgementer.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the adverbs
         */
        public static ArrayList<String> adverbs() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner adverber;
                try {
                        adverber = new Scanner(new File("Adverbs.txt"));
                        adverber.useDelimiter(", *");
                        while (adverber.hasNext()){
                                temp.add(" "+adverber.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the verbs
         */
        public static ArrayList<String> verbs() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner verber;
                try {
                        verber = new Scanner(new File("Verbs.txt"));
                        verber.useDelimiter(", *");
                        while (verber.hasNext()){
                                temp.add(" "+verber.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates the pronouns
         */
        public static ArrayList<String> pronouns() {
                ArrayList<String> temp = new ArrayList<String>();
                Scanner pronouner;
                try {
                        pronouner = new Scanner(new File("pronouns.txt"));
                        pronouner.useDelimiter(", *");
                        while (pronouner.hasNext()){
                                temp.add(" "+pronouner.next()+" ");
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        /*
         * This method populates our topics
         */
        public static HashMap<String,String> topics(){
                HashMap<String,String> temp = new HashMap<String,String>();
                Scanner topicer;
                try {
                        topicer = new Scanner(new File("Topics.txt"));
                        topicer.useDelimiter(",~ *\n*");
                        while (topicer.hasNext()){
                                String key = " "+topicer.next()+" ";
                                String value = "";
                                if(topicer.hasNext())
                                        value = topicer.next();
                                temp.put(key, value);
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return temp;
        }
        
        
        /*
         * This method populates our topiclist
         */
        public static ArrayList<String> topiclist(){
            ArrayList<String> temp = new ArrayList<String>();
            Scanner topicer;
            try {
                    topicer = new Scanner(new File("Topics.txt"));
                    topicer.useDelimiter(",~ *\n*");
                    while (topicer.hasNext()){
                            String[] key = topicer.next().split(",");
                            //String value = "";
                            if(topicer.hasNext())
                                    topicer.next();
                            for (int i = 0; i < key.length; i++)
                            	temp.add(key[i]);
                    }
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            }
            return temp;
    }
        
        
        /*
         * This method populates our details
         */
		public static HashMap<String,String> details(String firstword) {
			if(firstword.charAt(firstword.length()-1) == ',')
				firstword = firstword.substring(0, firstword.length()-1);
			HashMap<String,String> temp = new HashMap<String,String>();
            Scanner instructioner;
            try {
                    instructioner = new Scanner(new File("Details/"+firstword+".txt"));
                    instructioner.useDelimiter(",~ *\n*");
                    while (instructioner.hasNext()){
                            String key = " "+instructioner.next()+" ";
                            String value = "";
                            if(instructioner.hasNext())
                                    value = instructioner.next();
                            temp.put(key, value);
                    }
            } catch (FileNotFoundException e) {
                    temp = null;
            }
            return temp;
		}

}