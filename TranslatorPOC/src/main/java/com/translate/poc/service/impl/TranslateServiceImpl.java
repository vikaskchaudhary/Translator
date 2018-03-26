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
import com.translate.poc.util.Constants;
import com.translate.poc.util.GoogleTranslator;
import com.translate.poc.util.MicrosoftTranslator;
import com.translate.poc.util.WatsonTranslator;
import com.translate.poc.util.YandexTranslator;

@Service
@Qualifier("TranslaeService")
public class TranslateServiceImpl implements TranslateService {

	private static Logger LOGGER = LoggerFactory.getLogger(TranslateServiceImpl.class);
	
	@Override
	public TranslatorModel getTranslation(String target, String source,String text,String provider) {
		String translate = null;
		
		try {
			
			switch(provider) {
			
				case Constants.GOOGLE :
					translate = GoogleTranslator.callUrlAndParseResult(source, target, text);
				break;
				case Constants.WATSON :
					translate = WatsonTranslator.callUrlAndParseResult(source, target, text);
				break;
				case Constants.YANDEX :
					translate= YandexTranslator.callUrlAndParseResult(source, target, text);
			    break;
			    
				case Constants.MICROSOFT:
					translate= MicrosoftTranslator.callUrlAndParseResult(source, target, text);
			    break;
			    
			    default:
			    break;
			}
			
		} catch (Exception e) {
			LOGGER.error(" error in translating for provider {}  and error ",provider,e.getMessage());
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
			 
			switch(provider) {
			
				case Constants.GOOGLE :
					langList = GoogleTranslator.getSupportedLanguages();
				break;
				
				case Constants.WATSON :
					langList = WatsonTranslator.getSupportedLanguages();			
				break;
				
				case Constants.YANDEX :
					langList = YandexTranslator.getSupportedLanguages();	
			    break;
			    
				case Constants.MICROSOFT:
					langList = MicrosoftTranslator.getSupportedLanguages();	
				    break;
			    
			    default:
			    break;
			
			}
		} catch (Exception e) {
			LOGGER.error(" error in geting SupportedLanguages for provider {}  and error ",provider,e.getMessage());
		}
		return langList;
	} 
}
