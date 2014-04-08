import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class Tweeter {
	static String user;
	static Twitter twitter;
	static TwitterFactory fact;
	Query query;
	QueryResult res;
	static ConfigurationBuilder con;
	
	public static void setConfig(String ckey, String csec, String atok, String asec, String username){
		con = new ConfigurationBuilder();
		con.setDebugEnabled(true);
		con.setOAuthConsumerKey(ckey);
		con.setOAuthConsumerSecret(csec);
		con.setOAuthAccessToken(atok);
		con.setOAuthAccessTokenSecret(asec);
		fact = new TwitterFactory(con.build());
		twitter = fact.getInstance();
		user = username;
		
	}
	
	public static String gimmeTweets(){
		String output = "";
		try{
			ResponseList<Status> tlist = twitter.getUserTimeline(user);
			output += tlist.get((int)(Math.random()*tlist.size())).getText();
		}catch(TwitterException e){
			e.printStackTrace();
		}
		return output;
	}
}
