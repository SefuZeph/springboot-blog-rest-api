package com.springboot.blog.springbootblogrestapi.payload

data class PostDto(
    val id: Long, val title: String, val description: String, val content: String
)