package com.springboot.blog.springbootblogrestapi.controller

import com.springboot.blog.springbootblogrestapi.payload.CommentDto
import com.springboot.blog.springbootblogrestapi.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/")
class CommentController(val commentService: CommentService) {
    @PostMapping("posts/{postId}/comments")
    fun createComment(
        @PathVariable("postId") id: Long, @RequestBody commentDto: CommentDto
    ): ResponseEntity<CommentDto> {
        return ResponseEntity(commentService.createComment(id, commentDto), HttpStatus.CREATED)
    }

    @GetMapping("posts/{postId}/comments")
    fun getCommentByPostId(@PathVariable("postId") id: Long): List<CommentDto> {
        return commentService.getCommentByPostId(id)
    }


    @GetMapping("posts/{postId}/comments/{commentId}")
    fun getCommentById(@PathVariable postId: Long, @PathVariable commentId: Long): ResponseEntity<CommentDto> {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId))
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    fun updateCommentById(
        @PathVariable("postId") postId: Long,
        @PathVariable("commentId") commentId: Long,
        @RequestBody commentDto: CommentDto
    ): ResponseEntity<CommentDto> {
        val updateComment = commentService.updateCommentById(postId, commentId, commentDto)
        return ResponseEntity.ok(updateComment)
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    fun deleteById(@PathVariable postId: Long, @PathVariable commentId: Long): ResponseEntity<String> {
        commentService.deleteById(postId, commentId)
        return ResponseEntity.ok("Comment successfully deleted")
    }

}