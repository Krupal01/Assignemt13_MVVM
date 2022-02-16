package com.example.home.model;

import java.util.List;

public class StoryText{
	private String matchLevel;
	private String value;
	private List<Object> matchedWords;

	public String getMatchLevel(){
		return matchLevel;
	}

	public String getValue(){
		return value;
	}

	public List<Object> getMatchedWords(){
		return matchedWords;
	}
}