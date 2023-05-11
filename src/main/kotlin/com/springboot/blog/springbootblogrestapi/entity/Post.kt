package com.springboot.blog.springbootblogrestapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
    @Column(unique = true, nullable = false, name = "title") val title: String,
    @Column(name = "description", nullable = false) val description: String,
    @Column(name = "content", nullable = false) val content: String,
)