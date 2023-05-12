package com.springboot.blog.springbootblogrestapi.controller

import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.payload.PostResponse
import com.springboot.blog.springbootblogrestapi.service.PostService
import com.springboot.blog.springbootblogrestapi.utils.AppConstants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
    fun getAllPosts(
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) pageNo: Int,
        @RequestParam(
            value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false
        ) pageSize: Int,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) sortBy: String,
        @RequestParam(
            value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false
        ) sortDir: String
    ): PostResponse {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable("id") postId: Long): ResponseEntity<PostDto> {
        return ResponseEntity.ok(postService.getPostById(postId))
    }

    @PutMapping("/{id}")
    fun updatePost(@RequestBody postDto: PostDto, @PathVariable("id") postId: Long): ResponseEntity<PostDto> {
        return ResponseEntity.ok(postService.updatePost(postDto, postId))
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable("id") postId: Long): ResponseEntity<String> {
        postService.deletePost(postId)
        return ResponseEntity("Post successfully deleted!", HttpStatus.OK)
    }
}
