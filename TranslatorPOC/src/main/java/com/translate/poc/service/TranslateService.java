package com.translate.poc.service;

import java.util.List;

import com.translate.poc.model.LanguageISOModel;
import com.translate.poc.model.TranslatorModel;

public interface TranslateService {
	
	public TranslatorModel getTranslation(String target, String source,String text,String provider);
	
	public List<LanguageISOModel> getSupportedLanguages(String provider);
}
