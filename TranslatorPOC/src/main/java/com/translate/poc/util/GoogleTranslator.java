package com.translate.poc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.neovisionaries.i18n.LanguageAlpha3Code;
import com.neovisionaries.i18n.LanguageCode;
import com.translate.poc.exception.BusinessException;
import com.translate.poc.model.LanguageISOModel;

public class GoogleTranslator {
	

	public static String callUrlAndParseResult(String langFrom, String langTo, String word) throws Exception {
		
		String url = "https://translate.googleapis.com/translate_a/single?" + "client=gtx&" + "sl=" + langFrom + "&tl="
				+ langTo + "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String inputJson = response.toString();
		return parseResult(inputJson);
	}

	private static String parseResult(String inputJson) throws Exception {
		

		JSONArray jsonArray = new JSONArray(inputJson);
		JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
		
		String sent="";
		for(int i=0;i<jsonArray2.length();i++){
			JSONArray jsonArray3 = (JSONArray) jsonArray2.get(i);
			sent=sent+jsonArray3.get(0).toString();
			
		}
		
		return sent;
	}
	
	
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public static List<LanguageISOModel> getSupportedLanguages() throws Exception{
		
		String url = "https://translation.googleapis.com/language/translate/v2/languages?key=AIzaSyDTfzSfoz9pvl8nlhvEAhwdnK7sQpheKA4";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String inputJson = response.toString();
		
		List<LanguageISOModel> languageList=new ArrayList<>();
		
		Object obj1 = new JSONParser().parse(inputJson);
		JSONObject jo = (JSONObject) obj1;
		JSONObject jo1 = (JSONObject) jo.get("data");
		org.json.simple.JSONArray ja = (org.json.simple.JSONArray) jo1.get("languages");
		 
		 Iterator itr2 = ja.iterator();
         
	        while (itr2.hasNext()) 
	        {
	        	 Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
	        	 while (itr1.hasNext()) {
	        		 LanguageISOModel langModel= new LanguageISOModel();
		                Map.Entry pair = itr1.next();
		                String val= pair.getValue().toString();
		                LanguageCode list;
		                LanguageAlpha3Code list1;
		                try{
				                if(val.length()==2){
				                	langModel.setLanguageName(LanguageCode.valueOf((String) pair.getValue()).getName());
				                }else{
				                	 langModel.setLanguageName(LanguageAlpha3Code.valueOf((String) pair.getValue()).getName());
				                }
				                langModel.setAbbr(pair.getValue().toString());
			                }catch(Exception ex){
			                	
			                }
		                if(langModel.getAbbr()!=null)
		                languageList.add(langModel);
		                
		            }
	        }
	        languageList.removeAll(Collections.singleton(null));
	        return languageList;
		
	}
}
