package com.example.assignment13_mvvm_kotlin.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.assignment13_mvvm_kotlin.converter.Converter
import com.google.gson.annotations.SerializedName

data class Page(

	@field:SerializedName("hits")
	val hits: List<HitsItem>?= listOf(),

	@field:SerializedName("exhaustiveTypo")
	val exhaustiveTypo: Boolean? = null,

	@field:SerializedName("hitsPerPage")
	val hitsPerPage: Int? = null,

	@field:SerializedName("processingTimeMS")
	val processingTimeMS: Int? = null,

	@field:SerializedName("query")
	val query: String? = null,

	@field:SerializedName("nbHits")
	val nbHits: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("nbPages")
	val nbPages: Int? = null,

	@field:SerializedName("exhaustiveNbHits")
	val exhaustiveNbHits: Boolean? = null
)

data class Url(

	@field:SerializedName("matchLevel")
	val matchLevel: String? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("matchedWords")
	val matchedWords: List<Any?>? = null
)

data class Title(

	@field:SerializedName("matchLevel")
	val matchLevel: String? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("matchedWords")
	val matchedWords: List<Any?>? = null
)

data class HighlightResult(

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("url")
	val url: Url? = null
)

data class Author(

	@field:SerializedName("matchLevel")
	val matchLevel: String? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("matchedWords")
	val matchedWords: List<Any?>? = null
)

@Entity
data class HitsItem(

	@TypeConverters(Converter::class)
	@field:SerializedName("comment_text")
	val commentText: Any? = null,

	@TypeConverters(Converter::class)
	@field:SerializedName("story_text")
	val storyText: Any? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@TypeConverters(Converter::class)
	@field:SerializedName("story_id")
	val storyId: Any? = null,

	@field:SerializedName("_tags")
	val tags: List<String?>? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("created_at_i")
	val createdAtI: Int? = null,

	@NonNull
	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("title")
	val title: String ,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("points")
	val points: Int? = null,

	@field:SerializedName("_highlightResult")
	val highlightResult: HighlightResult? = null,

	@field:SerializedName("num_comments")
	val numComments: Int? = null,

	@TypeConverters(Converter::class)
	@field:SerializedName("story_title")
	val storyTitle: Any? = null,

	@TypeConverters(Converter::class)
	@field:SerializedName("parent_id")
	val parentId: Any? = null,

	@TypeConverters(Converter::class)
	@field:SerializedName("story_url")
	val storyUrl: Any? = null,

	@field:SerializedName("objectID")
	val objectID: String? = null
)
