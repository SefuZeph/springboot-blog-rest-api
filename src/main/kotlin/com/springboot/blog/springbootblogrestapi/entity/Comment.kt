package com.springboot.blog.springbootblogrestapi.entity

import jakarta.persistence.*

@Entity
@Table(name = "comments")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
    val name: String,
    val email: String,
    val body: String,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id", nullable = false) var post: Post = Post(),
)