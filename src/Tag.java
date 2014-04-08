import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class Tag {

	private static MaxentTagger tagger = new MaxentTagger( "taggers/english-caseless-left3words-distsim.tagger");
	
	public static String[] tagit(String str){
		String[] tagged = tagger.tagString(str).split(" ");
		String[] ret = new String[tagged.length];
		for (int i = 0; i < tagged.length; i++){
			System.out.println(tagger.tagString(str));
			System.out.println(tagged[i]);
			System.out.println(tagged[i].split("_")[1]);
			ret[i] = tagged[i].split("_")[1];
		}
		System.out.println(ret);
		return ret;
			
	}
}
