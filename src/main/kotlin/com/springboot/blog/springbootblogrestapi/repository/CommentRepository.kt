package com.springboot.blog.springbootblogrestapi.repository

import com.springboot.blog.springbootblogrestapi.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByPostId(id: Long): List<Comment>
}