import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class Wolf {
	/*
	 * This class makes finding general data form Wolfram Alpha easy
	 */

	private static String myurl = "http://api.wolframalpha.com/v2/query?input=";
	private String charset = "UTF-8";
	private WAEngine en;
	private WAQuery qu;
	private String in;

	public Wolf() {
		en = makeEngine();
	}

	private WAEngine makeEngine() {
		WAEngine en = new WAEngine();
		en.setAppID("KH2TXA-P884RH5W7G");
		en.addFormat("plaintext");
		en.addFormat("image");
		en.createQuery();
		return en;
	}

	private WAQuery makeQuery(String str) {
		qu = en.createQuery(str);
		return qu;
	}
	
	private boolean sortaMatch(String s1, String s2){
		String[] ss1 = s1.split(" ");
		String[] ss2 = s2.split(" ");
		for (int i = 0; i < ss1.length; i++){
			for (int k = 0; k< ss2.length; k++){
				if (ss1[i].contains(ss2[k])){
					return true;
				}
			}
		}
		return false;
	}
	
	private String printQ(WAQuery q, String in) {
		String toReturn = "";
		System.out.println(en.toURL(qu)+" STOP ");
		try {
			WAQueryResult res = en.performQuery(qu);
			System.out.println(en.toURL(qu)+" STOP ");
			if (res.isError()) {
				System.out.println("Error occured");
				  System.out.println("  error code: " + res.getErrorCode());
	                System.out.println("  error message: " + res.getErrorMessage());
	                return "error";

			} else if (!res.isSuccess()) {
				System.out.println("Even WolframAlpha cant help you.");
				return "Even WolframAlpha cant help you.";
			} else {
				for (WAPod p : res.getPods()) {
					if (p.getTitle().equals("Description") ||p.getTitle().equals("Results") || Comparison.contains(in.split(" "), p.getTitle())|| sortaMatch(p.getTitle(), in)) {
						
						System.out.println(p.getTitle());
						for (WASubpod sp : p.getSubpods()) {
							for (Object el : sp.getContents()) {								
								if (el instanceof WAPlainText) {
									System.out.println(((WAPlainText) el)
											.getText());
									toReturn = toReturn+"<br>"+p.getTitle();
									toReturn = toReturn+" "+((WAPlainText) el).getText();
								} else {
										}
							}
						}
					}
				}
			}
		} catch (WAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!toReturn.isEmpty())
			return "Maybe this will help... "+toReturn;
		else if (!in.toLowerCase().contains("definition")){
			String newin = "definition "+in;
			WAQuery tryagain = makeQuery(newin);
			return printQ(tryagain, newin);
		}
		else
			return "I couldn't find anything that I think will help you.";
	}
	/*
	 * This method will take an query as input in the form of a string and will return data about the query from Wolfram Alpha
	 * First searching for data in the form of a description, then definition, then whatever other result types can be seen in the requested query
	 * @param in The input query
	 * @return Text data related to the input query
	 */
	public String trythis(String in){
		WAQuery wq = makeQuery(in);
		return printQ(wq,in);
	}
}



