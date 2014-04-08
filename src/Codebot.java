import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import com.gtranslate.Language;
import com.gtranslate.Translator;


public class Codebot {
        /*
         * This class is responsible for handling all user interaction. It is the central class. This class gathers information
         * from the other classes and uses that information to attempt to answer user questions.  This class also handles
         * regular parts of conversation such as greetings.  If the system is unable to answer a user question then this class
         * will perform an Internet search for the topic and show the user the resulting webpage.
         */

        /*
         * This is where our library gets stored into memory for fast access
         */
        private static ArrayList<String> greetings;
        private static ArrayList<String> closures;
        private static ArrayList<String> affirmations;
        private static ArrayList<String> negations;
        private static ArrayList<String> prompts;
        private static ArrayList<String> reprompts;
        private static ArrayList<String> topicprompts;
        private static ArrayList<String> inquiries;
        private static ArrayList<String> compliments;
        private static ArrayList<String> acknowledgements;
        private ArrayList<String> adverbs;
        private ArrayList<String> verbs;
        private ArrayList<String> pronouns;
        private static HashMap<String,String> topics;
        private static HashMap<String,String> details;
        private static Scanner scan;
        private static String lastSaid;
        private static String lastSaidType;
        private static Matcher match;
        private static Wolf w;
        public static int lang;
        private static Translator t;
        
        
        private static winui win;
        private static String[] tagged;
        private static String raw;
        private static boolean idk;
        private static String lastThing;
        private static int turn;
        /*
         * This is our constructor. It populates the library and begins the session
         */
        public Codebot(){
        	
    		
        		Tweeter.setConfig("CojIjpiUR8EsP05s975Jtsmzq", "du6vSyZcFYNJce375oDwRa0BqFtskfBVfb8SJ1Wupkzgg5IWYc", "1017153882-9SeQt5pFBbC3T0XbTCukXFGlAAzuu2PDzHweKqW", "5CcXSnAWXYs0N53mNu6XAjuUqro6L4jHcz11HFXUz2tz9", "CompSciFact");
                greetings = Populate.greetings();
                closures = Populate.closures();
                prompts = Populate.prompts();
                reprompts = Populate.reprompts();
                topicprompts = Populate.topicprompts();
                affirmations = Populate.affirmations();
                negations = Populate.negations();
                inquiries = Populate.inquiries();
                compliments = Populate.compliments();
                acknowledgements = Populate.acknowledgements();
                adverbs = Populate.adverbs();
                verbs = Populate.verbs();
                pronouns = Populate.pronouns();
                topics = Populate.topics();
                scan = new Scanner(System.in);
                lastSaid="";
                lastSaidType="";
                match = new Matcher();
                w = new Wolf();
                lang = 0;
                t = Translator.getInstance();
                
                win = new winui();
                idk = false;
                turn = 0;
                beginSession();
                
        }
        
        /*
         * This method gets the ball rolling. It will greet the user and then scan for their response
         */
        private void beginSession() {
                Random rand = new Random();
                String greeting = greetings.get(rand.nextInt(greetings.size()));
                lastSaid = greeting;
                lastSaidType = "greeting";
                System.out.println(greeting.substring(1));
                String value =greeting.substring(1);
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                win.upCon("<b>CodeBot: </b>"+ value);
                //String response = scan.nextLine() ;
                //respond(response);
                
        }

        /*
         * This method takes a string as an input and selects a valid response
         */
        public static void respond(String response) {
        		turn++;
        		System.out.println("TURN: "+turn);
        		win.upCon("<B>User: </b>" + response);
                if (response.isEmpty()){                        
                        prompt();
                }
                else{
                		//tagged = Tag.tagit(response);
                		raw = response;
                		if (lang == 1){
                			response = t.translate(response, Language.LITHUANIAN, Language.ENGLISH);
                		}
                		response = match.fixSentence(response);
                        response = Punctuation.space(response);        //correctly format their response for searching through libraries
                        if (Comparison.contains(greetings,response)){
                                /*
                                 * If they greet codebot, codebot will reply with a prompt
                                 */
                                prompt();
                        } 
                        else if (Comparison.contains(affirmations, response)&&idk==true){
                        	search2(lastThing);
                        	search3(lastThing);
                        	prompt();
                        }
                        else if (Comparison.contains(negations,  response)&&idk == true){
                        	
                        	idk = false;
                        	prompt();
                        	
                        }
                        else if (Comparison.contains(affirmations, response)&&(lastSaidType.equals("prompt")||lastSaidType.equals("reprompt"))){
                                /*
                                 * If codebot prompted them, i.e. "Do you want help?" and they respond with yes (or any other affirmation)
                                 * then codebot inquires as to what they need help with
                                 */
                                inquire();
                        }
                        else if (Comparison.contains(negations, response)&&lastSaidType.equals("prompt")){
                                /*
                                 * If codebot prompted them, i.e. "Do you want help?" and they respond with no (or any other negation)
                                 * then they are done and codebot ends the session. Might want to confirm they are done before ending
                                 * the session
                                 */
                                bored();
                        }
                        else if (Comparison.contains(negations, response)&&(lastSaidType.equals("tutor")||lastSaidType.equals("reprompt"))){
                                /*
                                 * If they negate our topicprompt (dont need additional help for a topic), prompt them
                                 */
                        		details = null;
                                if (lastSaidType.equals("tutor")){
                                        reprompt();}
                                else {
                                        endSession();}
                        }
                        else if (details != null&&Comparison.contains(details, response)&&lastSaidType.equals("tutor")){
                                /*
                                 * If they affirm our topicprompt (do need additional help for a topic), instruct them
                                 */
                                instruct(response);
                        }
                        else if(details != null&&lastSaidType.equals("tutor")){
                        	search1(response);
                        }
                        else if (details == null&&Comparison.contains(topics, response)){
                            /*
                             * Regardless of what was previously said, if they type a response that has a topic
                             * in our library, codebot responds with the basic information about that topic
                             */
                            tutor(response);
                        }
                        else if (Comparison.contains(compliments, response)){
                                /*
                                 * If they compliment codebot, codebot acknowledges the compliment
                                 */
                                acknowledge();
                        }
                        else if (Comparison.contains(closures,response)){
                                /*
                                 * If they say bye, or any other closure, codebot ends the session
                                 */
                                endSession();
                        }
                        else{
                                /*
                                 * In the worst case, codebot asks if they want it to look up the answer for them
                                 */
                                search1(response);
                        }
                }
        }
        
        /*
         * This method takes the user question and performs a google search in their default browser
         * This is the worst case scenario of codebot not knowing what to do :(
         */
        private static void bored(){
        	String[] chk = new String[3];
        	chk[0] = "Bored? take a look at this...";
        	chk[1] = "Check this out in the meantime...";
        	chk[2] = "Ok, why don't you have a look at this?...";
        	
    		Random ran = new Random();
    		int r = ran.nextInt(5);
    		String tweet = Tweeter.gimmeTweets();
    		
            System.out.println(chk[r]);
            win.upCon("<b>CodeBot: </b>"+ chk[r]+tweet);
            
            
        	
        }
        
        private static void search1(String response) {
        		details = null;
        		//String q = response.replace(' ', '+').substring(1,response.length()-1);
        		writeSearch(response,lastSaid);
        		String[] idks = new String[5];
        		
        		idks[0] = "Hmm.. not too sure about that. Want me to take a look online for you?";
        		idks[1] = "I don't have that information on hand. Should I see what information I can find online?";
        		idks[2] = "I don't know but I can find out. Want me to find that for you?";
        		idks[3] = "How am I supposed to know that? Give me a second while I find out.";
        		idks[4] = "Sorry, I am not that smart...yet\nWant me to search that for you?";
        		
        		Random ran = new Random();
        		int r = ran.nextInt(5);
        		
                System.out.println(idks[r]);
                win.upCon("<b>CodeBot: </b>"+ idks[r]);
                
                System.out.println("after window update");
                lastThing = raw;
                idk = true;
        }
        
        private static void search2(String str){
        				str = Punctuation.dpunc(str);
        				System.out.println(str);
                        String[] words = str.split(" ");
                        tagged = Tag.tagit(str);
                        StringBuffer query = new StringBuffer();
                        System.out.println(str);
                        System.out.println("tagged array size ="+tagged.length);
                        System.out.println("words array size ="+words.length);

                        for (int i = 0; i < tagged.length; i++){
                        	System.out.println("array position: "+ i + "...");
                    		System.out.println("tagged[i]: "+ tagged[i]);
                    		System.out.println("words[i]: " + words[i]);
                        	if (tagged[i].equals("JJ")||tagged[i].equals("NN")||tagged[i].equals("NNP")||tagged[i].equals("NNPS")||tagged[i].equals("NNS")||tagged[i].equals("VB")||tagged[i].equals("VBD")||tagged[i].equals("VBG")||tagged[i].equals("VBN")||tagged[i].equals("VBP")){
                        		query.append(words[i]+" ");
                        		System.out.println("Appending '"+words[i]+"' to search query");
                        	}
                        	else{
                        		System.out.println(words[i]+" will not be added to the query.");
                        	}
                        }
                        if(query.length()>0){
                        	query.deleteCharAt(query.length()-1); // removes last " " 
                        }
                        System.out.println("wolfram searchterms: "+ query.toString());
                        String ans = (String) w.trythis(query.toString());
                        System.out.println("printing ans");
                        System.out.println(ans);
                        win.upCon("<b>CodeBot: </b> According to Wolfram Alpha, "+ ans);
                		/*try {
                                Desktop desktop = java.awt.Desktop.getDesktop();
                                URL oURL = new URL("https://www.google.com/#q="+query);
                                System.out.println("https://www.google.com/#q="+query);
                                desktop.browse(oURL.toURI());
                                }
                        catch (Exception e) {
                                e.printStackTrace();
                                }*/
                		idk = false;
                        }
        
        private static void search3(String str){
			str = Punctuation.dpunc(str);
			System.out.println(str);
			String[] words = str.split(" ");
            tagged = Tag.tagit(str);
            StringBuffer query = new StringBuffer();
            System.out.println(str);
            System.out.println("tagged array size ="+tagged.length);
            System.out.println("words array size ="+words.length);
            for (int i = 0; i < tagged.length; i++){
            	System.out.println("array position: "+ i + "...");
        		System.out.println("tagged[i]: "+ tagged[i]);
        		System.out.println("words[i]: " + words[i]);
            	if (tagged[i].equals("JJ")||tagged[i].equals("NN")||tagged[i].equals("NNP")||tagged[i].equals("NNPS")||tagged[i].equals("NNS")||tagged[i].equals("VB")||tagged[i].equals("VBD")||tagged[i].equals("VBG")||tagged[i].equals("VBN")||tagged[i].equals("VBP")){
            		query.append(words[i]+" ");
            		System.out.println("Appending '"+words[i]+"' to search query");
            	}
            	else{
            		System.out.println(words[i]+" will not be added to the query.");
            	}
            }
            if(query.length()>0){
            	query.deleteCharAt(query.length()-1); // removes last " " 
            }
            System.out.println("wiki searchterms: "+ query.toString());
            String ans = (String) WikiFinder.wikiGet(query.toString());
            System.out.println("printing ans");
            System.out.println(ans);
            win.upCon("<b>CodeBot: </b> According to WikiPedia, "+ ans);
    		idk = false;
        	
        }
                

        
        //this method will store the google search in a file so we will be able to see what people are asking us to search
        //that way we will know what topics to add in
        private static void writeSearch(String q, String lastSaid2) {
        	String storethis = q+",~"+lastSaid+",~\n"; //their response is the key, and the value is what we said last
        	try {
     
    			File file = new File("SearchStorage.txt");
     
    			// if file doesnt exists, then create it
    			if (!file.exists()) {
    				file.createNewFile();
    			}
    			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
    			BufferedWriter bw = new BufferedWriter(fw);
    			//bw.write(storethis);
    			bw.append(storethis);
    			bw.close();
     
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}

		/*
         * This method provides further instruction based on the input topic
         */
        private static void instruct(String topic) {
                lastSaidType = "detail";
                boolean result = false;
                Iterator<String> keySet = details.keySet().iterator();        // returns an iterable list of topics from the hashmap
                String currentKey = null;
                Scanner topicscan;
                while(keySet.hasNext() && !result){                //This will return each individual key to search through since some are comprise of multiple keywords
                        currentKey = keySet.next();
                        topicscan = new Scanner(currentKey);
                        topicscan.useDelimiter(", *");
                        while(topicscan.hasNext()){                //Once codebot has the whole key with all keywords for the topic, it looks for matches from what the user inputed
                                String currentString = topicscan.next().toLowerCase();
                                currentString = Punctuation.space(currentString);
                                if(topic.toLowerCase().contains(currentString)){
                                        result = true;                //if coedbot finds a match, it now knows the topic they were searching for and can use the key to find the instructions
                                        break;
                                }
                        }
                }
                String value = details.get(currentKey);        //this will look up the instructions to return to them to the user
                lastSaid = currentKey;
                lastSaidType = "tutor";
                System.out.println(value);
                
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                
                win.upCon("<b>CodeBot: </b>"+ value);
                topicprompt();
        }

        /*
         * This method acknowledges compliments
         */
        private static void acknowledge() {
                Random rand = new Random();
                String acknowledgement = acknowledgements.get(rand.nextInt(acknowledgements.size()));
                lastSaid = acknowledgement;
                lastSaidType = "acknowledgement";
                System.out.println(acknowledgement.substring(1));
                String value = acknowledgement.substring(1);
                if(lang == 1){
                	value = t.translate(acknowledgement.substring(1), Language.ENGLISH, Language.LITHUANIAN);
                }
                
                win.upCon("<b>CodeBot: </b>"+ value);
                //String response = scan.nextLine();
               // respond(response);
                
        }

        /*
         * Provides the user help when given a topic
         */
        private static void tutor(String topic) {
                boolean result = false;
                Iterator<String> keySet = topics.keySet().iterator();        // returns an iterable list of topics from the hashmap
                String currentKey = null;
                Scanner topicscan;
                while(keySet.hasNext() && !result){                //This will return each individual key to search through since some are comprise of multiple keywords
                        currentKey = keySet.next();
                        topicscan = new Scanner(currentKey);
                        topicscan.useDelimiter(", *");
                        while(topicscan.hasNext()){                //Once codebot has the whole key with all keywords for the topic, it looks for matches from what the user inputed
                                String currentString = topicscan.next().toLowerCase();
                                currentString = Punctuation.space(currentString);
                                if(topic.toLowerCase().contains(currentString)){
                                        result = true;                //if coedbot finds a match, it now knows the topic they were searching for and can use the key to find the instructions
                                        break;
                                }
                        }
                }
                String value = topics.get(currentKey);        //this will look up the instructions to return to them to the user
                lastSaid = currentKey;
                Scanner first = new Scanner(currentKey);
                String firstword = first.next();
                details = Populate.details(firstword);
                lastSaidType = "tutor";
                System.out.println(value);
                
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                
                win.upCon("<b>CodeBot: </b>"+ value);
                topicprompt();
        }
        
        /*
         * This method picks a random reprompt and prints it
         */
        private static void topicprompt() {
                Random rand = new Random();
                String topicprompt = topicprompts.get(rand.nextInt(topicprompts.size()));
                System.out.println(topicprompt.substring(1));  
                String value = topicprompt.substring(1);
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                win.upCon("<b>CodeBot: </b>"+ value);
               // String response = scan.nextLine();
                //respond(response); 
        }
        

        /*
         * This method asks if the user has any other questions
         */
        private static void reprompt() {
                Random rand = new Random();
                String reprompt = reprompts.get(rand.nextInt(reprompts.size()));
                lastSaidType="reprompt";
                System.out.println(reprompt.substring(1));  
                String value = reprompt.substring(1);
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                win.upCon("<b>CodeBot: </b>"+ value);
                //String response = scan.nextLine();
                //respond(response);   
        }


        /*
         * This method stops the ball from rolling
         */
        private static void endSession() {
                Random rand = new Random();
                String closure = closures.get(rand.nextInt(closures.size()));
                System.out.println(closure.substring(1)); 
                String value = closure.substring(1);
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                win.upCon("<b>CodeBot: </b>"+ value);

        }

        /*
         * This method prompts the user to ask a question.
         */
        private static void prompt(){
                Random rand = new Random();
                String prompt = prompts.get(rand.nextInt(prompts.size()));
                lastSaid = prompt;
                lastSaidType = "prompt";
                System.out.println(prompt.substring(1));
                String value = prompt.substring(1);
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                win.upCon("<b>CodeBot: </b>"+ value);
               // String response = scan.nextLine();
               // respond(response);
        }
        
        /*
         * This method asks the user what he/she needs help with
         */
        private static void inquire() {
                Random rand = new Random();
                String inquiry = inquiries.get(rand.nextInt(inquiries.size()));
                lastSaid = inquiry;
                lastSaidType = "inquiry";
                System.out.println(inquiry.substring(1));
                String value =inquiry.substring(1);
                if(lang == 1){
                	value = t.translate(value, Language.ENGLISH, Language.LITHUANIAN);
                }
                win.upCon("<b>CodeBot: </b>"+ value);

                //String response = scan.nextLine();
                //respond(response);
        }

}

