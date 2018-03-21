package com.citi.myclientsnews.model
import com.google.gson.annotations.SerializedName


/**
 * Created by yb34982 on 21/03/2018.
 */
//data class NewsDataResponse(
//	@SerializedName("statusCode") val statusCode : Int,
//	@SerializedName("headers") val headers : Headers,
//	@SerializedName("body") val body : NewDataClasses
//
//)
//
//
//data class Headers(
//		@SerializedName("Content-Type") val contentType: String
//)
//data class NewDataClasses(
//		@SerializedName("status") val status: String,
//		@SerializedName("totalResults") val totalResults: Int,
//		@SerializedName("articles") val articles: List<Article>
//) {
////	constructor() : this("error",0, emptyList())
//}


data class NewDataClass(
		@SerializedName("Status") val status: String,
		@SerializedName("TotalResults") val totalResults: Int,
		@SerializedName("Articles") val articles: List<Article>
)

data class Article(
		@SerializedName("Source") val source: Source,
		@SerializedName("Author") val author: String,
		@SerializedName("Title") val title: String,
		@SerializedName("Description") val description: String,
		@SerializedName("Url") val url: String,
		@SerializedName("urlToImage") val urlToImage: String,
		@SerializedName("publishedAt") val publishedAt: String
)

data class Source(
		@SerializedName("Name") val name: String
)