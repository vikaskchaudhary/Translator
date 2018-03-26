package com.translate.poc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;

public class GoogleCloudTranslator {
	
public static String callUrlAndParseResult(String langFrom, String langTo, String word) throws Exception {
		
	@SuppressWarnings("deprecation")
	Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyDYF-pzOsiFkfKViL65Yu738pEhkL8IIqw").build().getService();
	  //  String text = "Hello, world!";

	    // Translates some text into Russian
	    Translation translation =
	        translate.translate(
	        		word,
	            
	            TranslateOption.sourceLanguage(langFrom),
	            TranslateOption.targetLanguage(langTo));


	    //System.out.printf("Text: %s%n", text);
	   // System.out.printf("Translation: %s%n", translation.getTranslatedText());
		return translation.getTranslatedText();
	}

}
