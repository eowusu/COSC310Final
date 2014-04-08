import org.json.JSONException;


public class tesAns {

	public static void main(String[] args) throws JSONException {
		System.out.println("WIKI OUTPUT:");
		System.out.println(WikiFinder.wikiGet("integer"));
		
		System.out.println("TWITTER OUTPUT:");
		Tweeter.setConfig("CojIjpiUR8EsP05s975Jtsmzq", "du6vSyZcFYNJce375oDwRa0BqFtskfBVfb8SJ1Wupkzgg5IWYc", "1017153882-9SeQt5pFBbC3T0XbTCukXFGlAAzuu2PDzHweKqW", "5CcXSnAWXYs0N53mNu6XAjuUqro6L4jHcz11HFXUz2tz9", "CompSciFact");
		System.out.println(Tweeter.gimmeTweets());
	}
	

}
