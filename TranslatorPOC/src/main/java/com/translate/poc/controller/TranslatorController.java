package com.translate.poc.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.translate.poc.exception.BusinessException;
import com.translate.poc.model.LanguageISOModel;
import com.translate.poc.model.TranslatorModel;
import com.translate.poc.service.TranslateService;
import com.translate.poc.util.Constants;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/translate")
public class TranslatorController {
	

	private static Logger LOGGER = LoggerFactory.getLogger(TranslatorController.class);
	
	
	
	@Qualifier("GoogleCloudTranslator")
	@Autowired
	TranslateService googleCloudTranslatorService;
	
	@Qualifier("GoogleTranslator")
	@Autowired
	TranslateService googleTranslatorService;
	
	@Qualifier("WatsonTranslator")
	@Autowired
	TranslateService watsonTranslatorService;
	
	@Qualifier("YandexTranslator")
	@Autowired
	TranslateService yandexTranslatorService;
	
	
	@RequestMapping(value="/translateData", method=RequestMethod.POST)
	public TranslatorModel translateData(@RequestBody TranslatorModel translateModel) throws BusinessException {
	
       if(translateModel.getOriginalText() ==null || translateModel.getSourceLang() ==null || translateModel.getTargetLang()==null || translateModel.getProvider() ==null) {
    	   throw new BusinessException("Mandatory fields cant be empty.");
    	   
       }
		String provider = translateModel.getProvider();
		TranslatorModel model =null;
		
		try {
			
			switch(provider) {
			   
			
			case  Constants.GOOGLE_CLOUD:
				   model=  googleCloudTranslatorService.getTranslation(translateModel.getTargetLang(), translateModel.getSourceLang(), translateModel.getOriginalText(), translateModel.getProvider());
			   break;
			   case  Constants.GOOGLE:
				   model=  googleTranslatorService.getTranslation(translateModel.getTargetLang(), translateModel.getSourceLang(), translateModel.getOriginalText(), translateModel.getProvider());
			   break;
			  
			   case  Constants.MICROSOFT:
			   break;
			   
			   case  Constants.YANDEX:
				   model=  yandexTranslatorService.getTranslation(translateModel.getTargetLang(), translateModel.getSourceLang(), translateModel.getOriginalText(), translateModel.getProvider());
			   break;
			  
			   case  Constants.WATSON:
				   model=  watsonTranslatorService.getTranslation(translateModel.getTargetLang(), translateModel.getSourceLang(), translateModel.getOriginalText(), translateModel.getProvider());
			   break;
			   
			   default:
			   break;
				   
			}
			
		}catch(Exception e) {
			LOGGER.error(" error in translating for provider {}  and error ",translateModel.getProvider(),e.getMessage());
			e.printStackTrace();
			
		}
		
		return model;
	}
		
	@RequestMapping(value="/getSupportedLanguages/{provider}", method=RequestMethod.	GET)
	public List<LanguageISOModel> getSupportedLanguages(@PathVariable("provider")  String provider) throws BusinessException {
	
	
	if(provider ==null) {
		throw new BusinessException("Mandatory fields cant be empty.");
	}
	
	List<LanguageISOModel> langList = Collections.EMPTY_LIST;
    
	try {
	 
			switch(provider) {
			case  Constants.GOOGLE_CLOUD:
				langList= googleTranslatorService.getSupportedLanguages(provider);
			   break;
			   case  Constants.GOOGLE:
				   langList= googleTranslatorService.getSupportedLanguages(provider);
			   break;
			  
			   case  Constants.MICROSOFT:
			   break;
			   
			   case  Constants.YANDEX:
				   langList=  yandexTranslatorService.getSupportedLanguages(provider);
			   break;
			  
			   case  Constants.WATSON:
				   langList= watsonTranslatorService.getSupportedLanguages(provider);
			   break;
			   
			   default:
				   langList= googleTranslatorService.getSupportedLanguages(provider);
			   break;
				   
			}
			
		}catch(Exception e) {
			LOGGER.error(" error in getting supported languages for provider {}  and error ",provider,e.getMessage());
			
		}
		
    return langList;
	}
	
}
