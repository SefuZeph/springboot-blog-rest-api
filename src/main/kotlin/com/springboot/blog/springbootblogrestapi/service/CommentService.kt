package com.springboot.blog.springbootblogrestapi.service

import com.springboot.blog.springbootblogrestapi.payload.CommentDto

interface CommentService {
    fun createComment(postId: Long, commentDto: CommentDto): CommentDto

    fun getCommentByPostId(id: Long): List<CommentDto>

    fun getCommentById(postId: Long, commentId: Long): CommentDto

    fun updateCommentById(postId: Long, commentId: Long, commentDto: CommentDto): CommentDto

    fun deleteById(postId: Long, commentId: Long)
}