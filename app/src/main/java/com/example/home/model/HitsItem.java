package com.example.home.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HitsItem{

	@SerializedName("comment_text")
	private Object commentText;

	@SerializedName("story_text")
	private Object storyText;

	@SerializedName("author")
	private String author;

	@SerializedName("story_id")
	private Object storyId;

	@SerializedName("_tags")
	private List<String> tags;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("created_at_i")
	private int createdAtI;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("points")
	private int points;

	@SerializedName("_highlightResult")
	private HighlightResult highlightResult;

	@SerializedName("num_comments")
	private int numComments;

	@SerializedName("story_title")
	private Object storyTitle;

	@SerializedName("parent_id")
	private Object parentId;

	@SerializedName("story_url")
	private Object storyUrl;

	@SerializedName("objectID")
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
		return author.equals(item.author) &&
				createdAt.equals(item.createdAt) &&
				title.equals(item.title);
	}
}