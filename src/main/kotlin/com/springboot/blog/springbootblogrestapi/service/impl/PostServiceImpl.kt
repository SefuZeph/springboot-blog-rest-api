package com.springboot.blog.springbootblogrestapi.service.impl

import com.springboot.blog.springbootblogrestapi.entity.Post
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException
import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.repository.PostRepository
import com.springboot.blog.springbootblogrestapi.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(val postRepository: PostRepository) : PostService {
    override fun createPost(postDto: PostDto): PostDto {
        val post = postRepository.save(mapToEntity(postDto))
        return mapToDto(post)
    }

    override fun getAllPosts(): List<PostDto> {
        val posts = postRepository.findAll()
        return posts.map { post ->
            mapToDto(post)
        }
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