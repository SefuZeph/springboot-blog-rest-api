package com.springboot.blog.springbootblogrestapi.controller

import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/posts")
class PostController(val postService: PostService) {

    // create blog post
    @PostMapping
    fun createPost(@RequestBody postDto: PostDto): ResponseEntity<PostDto> {
        return ResponseEntity(postService.createPost(postDto), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllPosts(): List<PostDto> {
        return postService.getAllPosts()
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable("id") postId: Long): ResponseEntity<PostDto> {
        return ResponseEntity.ok(postService.getPostById(postId))
    }
}
