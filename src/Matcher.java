import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*This class has methods for taking an input sentence in the form of a String and
 * determining the most likely intended meaning, amidst potential spelling mistakes form the user
 */
public class Matcher {
	
	private ArrayList<String> greetings;
    private ArrayList<String> closures;
    private ArrayList<String> affirmations;
    private ArrayList<String> negations;
    private ArrayList<String> compliments;
    private ArrayList<String> acknowledgements;
    private ArrayList<String> adverbs;
    private ArrayList<String> verbs;
    private ArrayList<String> pronouns;
    private ArrayList<String> topics;
    private ArrayList<String> master;
    private ArrayList<String> common;
    
	private int msize;
	
    public Matcher(){
        greetings = Populate.greetings();
        closures = Populate.closures();
        affirmations = Populate.affirmations();
        negations = Populate.negations();
        compliments = Populate.compliments();
        acknowledgements = Populate.acknowledgements();
        adverbs = Populate.adverbs();
        verbs = Populate.verbs();
        pronouns = Populate.pronouns();
        topics = Populate.topiclist();
        common = Populate.common();
        
        //The master arraylist contains all recognized words
        
        master = new ArrayList<String>();
        master.addAll(greetings);
        master.addAll(closures);
        master.addAll(affirmations);
        master.addAll(negations);
        master.addAll(compliments);
        master.addAll(acknowledgements);
        master.addAll(adverbs);
        master.addAll(verbs);
        master.addAll(pronouns);
        master.addAll(topics);
        master.addAll(common);
        msize = master.size();        
    }
    
    /*This method uses the private methods below to take a sentence in the form of a String
     * and return a new sentence in which each unrecognized or misspelled word is replaced by
     * a word that the system is more likely to recognize.
     */
    public String fixSentence(String str){
    	String[] arr = breakSen(str);
    	String[] newArr = fixArr(arr);
    	String newSen = buildSen(newArr);
    	//The print statement below can be used to determine exactly how words are being interpreted
    	System.out.println("Sentence interpreted as: "+newSen);
    	
    	return newSen;
    }
    

	//this method take an sentence in a string and returns a an array containing each word
	private String[] breakSen(String str){
		String[] sar = str.split(" ");
		for(int i = 0;i<sar.length;i++){
			sar[i].replaceAll("\\s+","");
		}
		return sar;
	}
	
	/*This method takes two strings, and the length of each string and determines the
	 * Levenshtein distance from one string to the other as an integer, that is, the number of changes that
	 * would need to be made to either string in order to make them match
	 */
	private int levDis(String s1, int len1, String s2, int len2){
		int val;
		//case 1: strings are empty
		if(Math.abs(len1-len2)>5)
			return 5;
		if(len1==0)
			return len2;
		if(len2==0)
			return len1;
		//case 2: strings arent empty, check last character
		if(s1.charAt(len1-1)==s2.charAt(len2-1))
			val = 0;
		else
			val = 1;
		//min from delete from s1, delete form s2, delete form both
		int toReturn = Math.min(levDis(s1, len1-1, s2, len2)+1,
				Math.min(levDis(s1, len1, s2, len2-1)+1,
				levDis(s1, len1-1, s2, len2-1)+val));
		if(toReturn>5)
			return 5;
		return toReturn;
	}
	
	/* method takes a String array and changes each String contained, to the word from our
	 * master arraylist for which the Levenshtein distance is a minimum.
	 */
	private String[] fixArr(String[] arr){
		int j;
		int currentLD = 6;
		String prospect = "";
		for (int i=0; i<arr.length; i++){
			if (arr[i].length() > 7)
				return arr;
			if (arr[i].length() > 1) {
				for (j = 0; j < msize; j++) {
					String tempSt = master.get(j).toLowerCase().replaceAll("\\s+","");
					int tempLD = levDis(tempSt.toLowerCase(), tempSt.length(), arr[i].toLowerCase(),
							arr[i].length());
					//The print statement below can be used for diagnostic purposes
					//System.out.println("Levenshtein Distance from "+tempSt+" to "+arr[i]+": "+tempLD);
					if (tempLD < currentLD) {
						currentLD = tempLD;
						prospect = tempSt;
					}
					else if(tempLD == currentLD && tempSt.charAt(0) == arr[i].charAt(0)){
						currentLD = tempLD;
						prospect = tempSt;
					}
				}
				currentLD = 6;
				//The print statement below can be used for diagnostic purposes
				System.out.println(arr[i] + " was compared to "+j+" words and was interpreted as " + prospect);
				arr[i] = prospect;
			}
		}
		return arr;
	}
	
	/*This method takes a String array and returns a sentence as a String, by producing a
	 * single String containing all the array's entries in order, seperated by single spaces
	 */
	private String buildSen(String[] arr){
		String sen = "";
		for (int i = 0 ; i < arr.length; i++){
			sen = sen + arr[i] + " ";
		}
		return sen;
		
	}

}
