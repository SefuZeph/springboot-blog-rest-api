package com.springboot.blog.springbootblogrestapi.exception

import org.springframework.http.HttpStatus

class BlogApiException(var status: HttpStatus, message: String) : RuntimeException() {

}