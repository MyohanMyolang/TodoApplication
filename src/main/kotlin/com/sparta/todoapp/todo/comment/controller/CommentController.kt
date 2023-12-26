package com.sparta.todoapp.todo.comment.controller

import com.sparta.todoapp.todo.comment.service.CommentService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo/comment")
class CommentController(
    private val commentService: CommentService
){

}