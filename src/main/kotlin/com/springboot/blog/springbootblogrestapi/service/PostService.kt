package com.springboot.blog.springbootblogrestapi.service

import com.springboot.blog.springbootblogrestapi.payload.PostDto

interface PostService {

    fun createPost(postDto: PostDto): PostDto

    fun getAllPosts():List<PostDto>

    fun getPostById(id:Long):PostDto

}