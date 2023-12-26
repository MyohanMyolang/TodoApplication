package com.sparta.todoapp.todo.exception

class UnauthorizedException(override val message: String) : RuntimeException(message) {
}