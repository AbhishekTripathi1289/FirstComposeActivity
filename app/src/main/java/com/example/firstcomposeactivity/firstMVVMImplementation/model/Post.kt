package com.example.firstcomposeactivity.firstMVVMImplementation.model

data class Post(
	val post: List<PostItem?>? = null
)

data class PostItem(
	val id: Int? = null,
	val title: String? = null,
	val body: String? = null,
	val userId: Int? = null
)

