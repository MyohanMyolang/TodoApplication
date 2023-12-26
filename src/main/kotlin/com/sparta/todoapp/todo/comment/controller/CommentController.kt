package com.sparta.todoapp.todo.comment.controller

import com.sparta.todoapp.global.util.responseEntity
import com.sparta.todoapp.todo.comment.dto.DeleteCommentDto
import com.sparta.todoapp.todo.comment.dto.RequestCommentDto
import com.sparta.todoapp.todo.comment.dto.UpdateCommentDto
import com.sparta.todoapp.todo.comment.service.CommentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo/comment")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun addComment(@RequestBody @Valid requestDto: RequestCommentDto) = responseEntity(HttpStatus.CREATED) {
        commentService.addComment(requestDto)
    }

    @PatchMapping
    fun updateComment(@RequestBody @Valid updateDto: UpdateCommentDto) = responseEntity(HttpStatus.OK) {
        commentService.updateComment(updateDto)
    }

    @DeleteMapping
    fun deleteComment(@RequestBody @Valid deleteDto: DeleteCommentDto) = responseEntity(HttpStatus.NO_CONTENT){
        commentService.deleteComment(deleteDto)
    }
}