package com.example.home.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StoryText{

	@SerializedName("matchLevel")
	private String matchLevel;

	@SerializedName("value")
	private String value;

	@SerializedName("matchedWords")
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