package com.translate.poc.util;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.translate.poc.model.LanguageISOModel;

public class WatsonTranslator {

public static String callUrlAndParseResult(String langFrom, String langTo, String word) throws Exception {
		
	LanguageTranslator service = new LanguageTranslator();
	service.setUsernameAndPassword(Constants.IBM_USERNAME,Constants.IBM_PASSWOD);
	TranslateOptions translateOptions;
	TranslationResult result;
	
	if(!langFrom.equals(langTo) && !langFrom.equals("en") && !langTo.equals("en")) {
		translateOptions = new TranslateOptions.Builder()
				  .addText(word)
				  .source(langFrom)
				  .target("en")
				  .build();

				 result = service.translate(translateOptions)
				  .execute();
				 
		 translateOptions = new TranslateOptions.Builder()
				  .addText(result.getTranslations().get(0).getTranslation())
				  .source("en")
				  .target(langTo)
				  .build();

	}else
	{

	 translateOptions = new TranslateOptions.Builder()
	  .addText(word)
	  .source(langFrom)
	  .target(langTo)
	  .build();
	}

	 result = service.translate(translateOptions)
	  .execute();
		return result.getTranslations().get(0).getTranslation();
	}

public static List<LanguageISOModel> getSupportedLanguages() throws Exception{
	
	List<LanguageISOModel> languageList= new ArrayList<LanguageISOModel>();
	
	LanguageISOModel model = new LanguageISOModel();
	 
	 model.setAbbr(Constants.ENGLISH_CODE);
     model.setLanguageName(Constants.ENGLISH);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.ARABIC_CODE);
     model.setLanguageName(Constants.ARABIC);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.FRENCH_CODE);
     model.setLanguageName(Constants.FRENCH);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.GERMAN_CODE);
     model.setLanguageName(Constants.GERMAN);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.ITALIAN_CODE);
     model.setLanguageName(Constants.ITALIAN);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.JAPANESE_CODE);
     model.setLanguageName(Constants.JAPANESE);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.PORTUGUESE_CODE);
     model.setLanguageName(Constants.PORTUGUESE);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.KOREAN_CODE);
     model.setLanguageName(Constants.KOREAN);
     languageList.add(model);
     model = new LanguageISOModel();
     model.setAbbr(Constants.SPANISH_CODE);
     model.setLanguageName(Constants.SPANISH);
     languageList.add(model);
    
      return languageList;
	
}
	
}
