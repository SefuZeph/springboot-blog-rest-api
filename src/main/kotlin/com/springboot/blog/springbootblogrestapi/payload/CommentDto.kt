package com.springboot.blog.springbootblogrestapi.payload

data class CommentDto(
    val id: Long,  val name: String, val email: String, val body: String
)