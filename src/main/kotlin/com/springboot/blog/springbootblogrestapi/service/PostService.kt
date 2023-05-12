package com.springboot.blog.springbootblogrestapi.service

import com.springboot.blog.springbootblogrestapi.payload.PostDto
import com.springboot.blog.springbootblogrestapi.payload.PostResponse

interface PostService {

    fun createPost(postDto: PostDto): PostDto

    fun getAllPosts(pageNo:Int,pageSize:Int,sortBy:String,sortDir:String): PostResponse

    fun getPostById(id:Long):PostDto

    fun updatePost(postDto: PostDto,id:Long):PostDto

    fun deletePost(id:Long)

}