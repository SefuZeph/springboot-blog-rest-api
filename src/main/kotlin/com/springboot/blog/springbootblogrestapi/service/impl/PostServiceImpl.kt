package com.springboot.blog.springbootblogrestapi.service.impl

import com.springboot.blog.springbootblogrestapi.entity.Post
import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.repository.PostRepository
import com.springboot.blog.springbootblogrestapi.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(val postRepository: PostRepository) : PostService {
    override fun createPost(postDto: PostDto): PostDto {
        val post = postRepository.save(
            Post(
                title = postDto.title, content = postDto.content, description = postDto.description
            )
        )

        // convert entity to dto
        return PostDto(id = post.id, title = post.title, content = post.content, description = post.description)
    }
}