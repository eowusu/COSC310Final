package com.a3;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import javax.json.JsonObject;

import org.json.JSONObject;




public class Ans {
	JsonObject j;
	
	public static void go(String q){
		String url = "http://en.wikipedia.org/w/api.php";
		String charset = "UTF-8";
		String param1 = "value1";
		try {
			
			String query = String.format("param1=%s",URLEncoder.encode(param1, charset));
			URLConnection connection = new URL(url + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
			String isst = response.toString();
			JSONObject json = new JSONObject(isst);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (Exception e){
			System.out.println("other exception");
		}
	}
}
