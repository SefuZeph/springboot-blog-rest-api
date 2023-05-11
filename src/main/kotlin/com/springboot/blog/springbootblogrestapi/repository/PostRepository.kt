package com.springboot.blog.springbootblogrestapi.repository

import com.springboot.blog.springbootblogrestapi.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {}