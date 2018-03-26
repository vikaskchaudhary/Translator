package com.translate.poc.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.translate.poc.model.LanguageISOModel;
import com.translate.poc.model.TranslatorModel;
import com.translate.poc.service.TranslateService;
import com.translate.poc.util.GoogleCloudTranslator;
import com.translate.poc.util.GoogleTranslator;

@Service
@Qualifier("GoogleCloudTranslator")
public class GoogleCloudTranslatorImpl implements TranslateService {

	private static Logger LOGGER = LoggerFactory.getLogger(GoogleCloudTranslatorImpl.class);
	
	
	
	@Override
	public TranslatorModel getTranslation(String target, String source, String text, String provider) {
		
		String translate = null;
		try {
			translate = GoogleCloudTranslator.callUrlAndParseResult(source, target, text);
		} catch (Exception e) {
			LOGGER.error(" error in translating for provider {}  and error ",provider,e.getMessage());
			e.printStackTrace();
		}
		
		TranslatorModel tmod=new TranslatorModel();
		tmod.setOriginalText(text);
		tmod.setProvider(provider);
		tmod.setSourceLang(source);
		tmod.setTargetLang(target);
		tmod.setTranslatedText(translate);
		return tmod;
	
	}

	@Override
	public List<LanguageISOModel> getSupportedLanguages(String provider) {
	
		List<LanguageISOModel> langList = Collections.EMPTY_LIST;
		try {
			 langList = GoogleTranslator.getSupportedLanguages();
			
			}
		catch (Exception e) {
			LOGGER.error(" error in geting SupportedLanguages for provider {}  and error ",provider,e.getMessage());
		}
		return langList;
	}

}
