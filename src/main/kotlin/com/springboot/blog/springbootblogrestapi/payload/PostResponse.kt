package com.springboot.blog.springbootblogrestapi.payload

data class PostResponse(
    val content: List<PostDto>,
    val pageNo: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean
)