package com.springboot.blog.springbootblogrestapi.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(var resourceName: String, var fieldName: String, var fieldValue: Long) :
    RuntimeException() {

}