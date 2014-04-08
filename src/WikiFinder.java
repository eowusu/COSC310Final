import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;



public class WikiFinder {
	/*
	 * This class takes as input, a search query in a String, and returns a text String of the first section of the WikiPedia page
	 * most associated with the given query
	 */

	public static MediaWikiBot mwb;
	public static Article art;
	/*
	 * This method takes the String str, searches WikiPedia and returns text data from the resulting page
	 * @param str The item that will be searched for on WikiPedia
	 * @return The first section of text from the page (only the most general description)
	 */
	public static String wikiGet(String str){
		mwb =new MediaWikiBot("http://en.wikipedia.org/w/");
		art = mwb.getArticle(str);
		String sum = art.getSimpleArticle().getText();
		
		String pattern = "[{}]{2}[^{}]*[{}]{2}";
		String pattern2 = "[*].*";
		
		sum = sum.replaceAll(pattern, "");
		sum = sum.replaceAll(pattern2, "");
		int ind = sum.lastIndexOf("==Notes==");
		sum = sum.substring(0,ind).trim();
		
		int ind2 = sum.indexOf("==");
		int ind3 = sum.indexOf(")")+1;
		String toReturn = sum.substring(0, ind2);
		
		return toReturn;
	}
	
}
