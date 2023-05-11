package com.springboot.blog.springbootblogrestapi.service.impl

import com.springboot.blog.springbootblogrestapi.entity.Post
import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.repository.PostRepository
import com.springboot.blog.springbootblogrestapi.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(val postRepository: PostRepository) : PostService {
    override fun createPost(postDto: PostDto): PostDto {
        // convert DTO to entity
        val post = postRepository.save(mapToEntity(postDto))

        // convert entity to dto
        return mapToDto(post)
    }

    override fun getAllPosts(): List<PostDto> {

        val posts = postRepository.findAll()
        return posts.map { post ->
            mapToDto(post)
        }
    }


    // map dto to entity
    private fun mapToEntity(postDto: PostDto): Post {
        return Post(
            title = postDto.title, content = postDto.content, description = postDto.description
        )
    }

    // convert entity to dto
    private fun mapToDto(post: Post): PostDto {
        return PostDto(post.id, post.title, post.content, post.description)
    }
}