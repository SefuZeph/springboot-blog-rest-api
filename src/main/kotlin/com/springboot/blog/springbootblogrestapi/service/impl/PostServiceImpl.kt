package com.springboot.blog.springbootblogrestapi.service.impl

import com.springboot.blog.springbootblogrestapi.entity.Post
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException
import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.payload.PostResponse
import com.springboot.blog.springbootblogrestapi.repository.PostRepository
import com.springboot.blog.springbootblogrestapi.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(val postRepository: PostRepository) : PostService {
    override fun createPost(postDto: PostDto): PostDto {
        val post = postRepository.save(mapToEntity(postDto))
        return mapToDto(post)
    }

    override fun getAllPosts(pageNo: Int, pageSize: Int, sortBy: String, sortDir: String): PostResponse {
        val sort = if (sortDir.lowercase() == Sort.Direction.ASC.name) Sort.by(sortBy).ascending() else Sort.by(sortBy)
            .descending()

        val pageable = PageRequest.of(pageNo, pageSize, sort)
        val posts: Page<Post> = postRepository.findAll(pageable)
        val content = posts.map { post ->
            mapToDto(post)
        }.content

        return PostResponse(
            content = content,
            pageNo = posts.number,
            pageSize = posts.size,
            totalElements = posts.totalElements,
            totalPages = posts.totalPages,
            last = posts.isLast
        )
    }

    override fun getPostById(id: Long): PostDto {
        val post = postRepository.findById(id).orElseThrow {
            ResourceNotFoundException("POST", "Id", id)
        }
        return mapToDto(post)
    }

    override fun updatePost(postDto: PostDto, id: Long): PostDto {
        val post = postRepository.findById(id).orElseThrow {
            ResourceNotFoundException("POST", "Id", id)
        }
        post.title = postDto.title
        post.content = postDto.content
        post.description = postDto.description

        val updatePost = postRepository.save(post)
        return mapToDto(updatePost)
    }

    override fun deletePost(id: Long) {
        val post = postRepository.findById(id).orElseThrow {
            ResourceNotFoundException("POST", "Id", id)
        }
        postRepository.deleteById(post.id)
    }

    private fun mapToEntity(postDto: PostDto): Post {
        return Post(
            title = postDto.title, content = postDto.content, description = postDto.description
        )
    }

    private fun mapToDto(post: Post): PostDto {
        return PostDto(post.id, post.title, post.content, post.description)
    }
}