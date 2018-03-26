package com.translate.poc.model;

public class TranslatorModel {
	
	String targetLang;
	String sourceLang;
	String originalText;
	String provider;
	String translatedText;
	
	public String getTargetLang() {
		return targetLang;
	}
	public void setTargetLang(String targetLang) {
		this.targetLang = targetLang;
	}
	public String getSourceLang() {
		return sourceLang;
	}
	public void setSourceLang(String sourceLang) {
		this.sourceLang = sourceLang;
	}
	public String getOriginalText() {
		return originalText;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
	
	
	@Override
	public String toString() {
		return "TranslatorModel [targetLang=" + targetLang + ", sourceLang=" + sourceLang + ", originalText="
				+ originalText + ", provider=" + provider + ", translatedText=" + translatedText + "]";
	}
	
	
}
