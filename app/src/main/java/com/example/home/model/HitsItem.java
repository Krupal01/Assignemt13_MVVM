package com.example.home.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import com.example.home.db.Converter;
import com.google.gson.annotations.SerializedName;

@Entity
public class HitsItem{

	@TypeConverters(Converter.class)
	@SerializedName("comment_text")
	private Object commentText;

	@TypeConverters(Converter.class)
	@SerializedName("story_text")
	private Object storyText;

	@SerializedName("author")
	private String author;

	@TypeConverters(Converter.class)
	@SerializedName("story_id")
	private Object storyId;

	@TypeConverters(Converter.class)
	@SerializedName("_tags")
	private List<String> tags;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("created_at_i")
	private int createdAtI;

	@PrimaryKey
	@NonNull
	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("points")
	private int points;

	@TypeConverters(Converter.class)
	@SerializedName("_highlightResult")
	private HighlightResult highlightResult;

	@SerializedName("num_comments")
	private int numComments;

	@TypeConverters(Converter.class)
	@SerializedName("story_title")
	private Object storyTitle;

	@TypeConverters(Converter.class)
	@SerializedName("parent_id")
	private Object parentId;

	@TypeConverters(Converter.class)
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

	public void setCommentText(Object commentText) {
		this.commentText = commentText;
	}
	public void setStoryText(Object storyText) {
		this.storyText = storyText;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setStoryId(Object storyId) {
		this.storyId = storyId;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public void setCreatedAtI(int createdAtI) {
		this.createdAtI = createdAtI;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void setHighlightResult(HighlightResult highlightResult) {
		this.highlightResult = highlightResult;
	}
	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}
	public void setStoryTitle(Object storyTitle) {
		this.storyTitle = storyTitle;
	}
	public void setParentId(Object parentId) {
		this.parentId = parentId;
	}
	public void setStoryUrl(Object storyUrl) {
		this.storyUrl = storyUrl;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

}