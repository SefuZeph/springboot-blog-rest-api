package com.springboot.blog.springbootblogrestapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Long,
    @Column(unique = true, nullable = false, name = "title") private val title: String,
    @Column(name = "description", nullable = false) private val description: String,
    @Column(name = "content", nullable = false) private val content: String,
)