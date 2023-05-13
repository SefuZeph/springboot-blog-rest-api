package com.springboot.blog.springbootblogrestapi.service.impl

import com.springboot.blog.springbootblogrestapi.entity.Comment
import com.springboot.blog.springbootblogrestapi.exception.BlogApiException
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException
import com.springboot.blog.springbootblogrestapi.payload.CommentDto
import com.springboot.blog.springbootblogrestapi.repository.CommentRepository
import com.springboot.blog.springbootblogrestapi.repository.PostRepository
import com.springboot.blog.springbootblogrestapi.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(val commentRepository: CommentRepository, val postRepository: PostRepository) :
    CommentService {
    override fun createComment(postId: Long, commentDto: CommentDto): CommentDto {
        val comment = mapToEntity(commentDto)
        // retrieve post entity by id
        val post = postRepository.findById(postId).orElseThrow { ResourceNotFoundException("POST", "Id", postId) }
        comment.post = post
        val saveComment = commentRepository.save(comment)
        return mapToDto(saveComment)
    }

    override fun getCommentByPostId(id: Long): List<CommentDto> {
        val comments = commentRepository.findByPostId(id)
        return comments.map {
            mapToDto(it)
        }
    }

    override fun getCommentById(postId: Long, commentId: Long): CommentDto {
        // retrieve post entity by id
        val post = postRepository.findById(postId).orElseThrow { ResourceNotFoundException("POST", "Id", postId) }
        // retrieve comment entity by id
        val comment =
            commentRepository.findById(commentId).orElseThrow { ResourceNotFoundException("Comment", "Id", commentId) }

        if (comment.post.id != post.id) {
            throw BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post")
        }

        return mapToDto(comment)
    }

    override fun updateCommentById(postId: Long, commentId: Long, commentDto: CommentDto): CommentDto {
        // retrieve post entity by id
        val post = postRepository.findById(postId).orElseThrow { ResourceNotFoundException("POST", "Id", postId) }
        // retrieve comment entity by id
        val comment =
            commentRepository.findById(commentId).orElseThrow { ResourceNotFoundException("Comment", "Id", commentId) }

        if (comment.post.id != post.id) {
            throw BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post")
        }
        val updateComment = Comment(
            id = comment.id,
            name = commentDto.name, email = commentDto.email, body = commentDto.body, post = post
        )
        return mapToDto(commentRepository.save(updateComment))
    }

    override fun deleteById(postId: Long, commentId: Long) {
        // retrieve post entity by id
        val post = postRepository.findById(postId).orElseThrow { ResourceNotFoundException("POST", "Id", postId) }
        // retrieve comment entity by id
        val comment =
            commentRepository.findById(commentId).orElseThrow { ResourceNotFoundException("Comment", "Id", commentId) }

        if (comment.post.id != post.id) {
            throw BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post")
        }

        commentRepository.deleteById(commentId)

    }

    // entity to dto
    fun mapToDto(comment: Comment): CommentDto {
        return CommentDto(id = comment.id, name = comment.name, email = comment.email, body = comment.body)
    }

    // dto to entity
    fun mapToEntity(commentDto: CommentDto): Comment {
        return Comment(
            id = commentDto.id, name = commentDto.name, email = commentDto.email, body = commentDto.body,
        )
    }
}