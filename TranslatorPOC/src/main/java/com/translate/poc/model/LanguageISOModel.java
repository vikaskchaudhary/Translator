package com.translate.poc.model;

public class LanguageISOModel {
	
	String languageName;
	String abbr;
	
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	
	
	@Override
	public String toString() {
		return "LanguageISOModel [languageName=" + languageName + ", abbr=" + abbr + "]";
	}
	
	
}
