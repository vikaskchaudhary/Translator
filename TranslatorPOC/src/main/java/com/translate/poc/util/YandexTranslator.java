package com.translate.poc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.JSONParser;

import com.translate.poc.model.LanguageISOModel;

public class YandexTranslator {
	
	 public static String callUrlAndParseResult(String langFrom, String langTo, String word) throws Exception {
		 
		 
		 	String url="https://translate.yandex.net/api/v1.5/tr.json/translate?lang="+langFrom+"-"+langTo+"&key="+Constants.YANDEX_KEY+"&text="+URLEncoder.encode(word, "UTF-8");
		 	URL obj = new URL(url);
		 	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
		 	con.setRequestProperty("User-Agent", "Mozilla/5.0");
		 	con.setRequestMethod("GET");
		 	con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			
		 		  	  
			 // Reading response from input Stream
			  BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
			  String output;
			  StringBuffer response = new StringBuffer();
			 
			  while ((output = in.readLine()) != null) {
			   response.append(output);
			  }
			  in.close();
			 
			  //printing result from response
			 
			  Object obj1 = new JSONParser().parse(response.toString());
			  org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj1;
			  org.json.simple.JSONArray jo1 = (org.json.simple.JSONArray) jo.get("text");
			 
			  String result=jo1.get(0).toString();
			  				
			return result;
		 
	 }
	 
	 
	 	@SuppressWarnings("rawtypes")
		public static List<LanguageISOModel> getSupportedLanguages() throws Exception{
		 
		 String URI="https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key="+Constants.YANDEX_KEY;
		 
		    URL obj = new URL(URI);
		 	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
		 	con.setRequestProperty("User-Agent", "Mozilla/5.0");
		 	con.setRequestMethod("GET");
		 	
		 	  BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
			  String output;
			  StringBuffer response = new StringBuffer();
			 
			  while ((output = in.readLine()) != null) {
			   response.append(output);
			  }
			  in.close();
			  
			    
			  Object obj1 = new JSONParser().parse(response.toString());
			  org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj1;
			  org.json.simple.JSONObject jo1 = (org.json.simple.JSONObject) jo.get("langs");
			  
			  
			  Set keys = jo1.keySet();
			  Iterator a = keys.iterator();
			  
			  List<LanguageISOModel> languageList=new ArrayList<LanguageISOModel>();
				
			  while(a.hasNext()) {
				    
				   LanguageISOModel langModel= new LanguageISOModel();
			    	
				    String key = (String)a.next();
			        String value = (String)jo1.get(key);
			        
			        langModel.setAbbr(key);
			        langModel.setLanguageName(value);
			      
			        languageList.add(langModel);
			  }
			  
			  return  languageList;
	 }


}
