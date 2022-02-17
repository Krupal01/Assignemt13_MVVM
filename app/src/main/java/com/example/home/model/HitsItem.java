package com.example.home.model;

import java.util.List;

public class HitsItem{
	private Object commentText;
	private Object storyText;
	private String author;
	private Object storyId;
	private List<String> tags;
	private String createdAt;
	private int createdAtI;
	private String title;
	private String url;
	private int points;
	private HighlightResult highlightResult;
	private int numComments;
	private Object storyTitle;
	private Object parentId;
	private Object storyUrl;
	private String objectID;

	public Object getCommentText(){
		return commentText;
	}

	public Object getStoryText(){
		return storyText;
	}

	public String getAuthor(){
		return author;
	}

	public Object getStoryId(){
		return storyId;
	}

	public List<String> getTags(){
		return tags;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getCreatedAtI(){
		return createdAtI;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public int getPoints(){
		return points;
	}

	public HighlightResult getHighlightResult(){
		return highlightResult;
	}

	public int getNumComments(){
		return numComments;
	}

	public Object getStoryTitle(){
		return storyTitle;
	}

	public Object getParentId(){
		return parentId;
	}

	public Object getStoryUrl(){
		return storyUrl;
	}

	public String getObjectID(){
		return objectID;
	}

	public boolean isEqual(HitsItem item){
		return author.equals(item.getAuthor()) &&
				createdAt.equals(item.getCreatedAt()) &&
				title.equals(item.getTitle()) &&
				url.equals(item.getUrl()) &&
				objectID.equals(item.getObjectID());
	}
}