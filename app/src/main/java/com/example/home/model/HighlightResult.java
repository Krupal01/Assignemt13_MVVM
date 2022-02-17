package com.example.home.model;

import com.google.gson.annotations.SerializedName;

public class HighlightResult{

	@SerializedName("author")
	private Author author;

	@SerializedName("title")
	private Title title;

	@SerializedName("url")
	private Url url;

	@SerializedName("story_text")
	private StoryText storyText;

	public Author getAuthor(){
		return author;
	}

	public Title getTitle(){
		return title;
	}

	public Url getUrl(){
		return url;
	}

	public StoryText getStoryText(){
		return storyText;
	}
}