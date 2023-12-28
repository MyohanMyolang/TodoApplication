package com.sparta.todoapp.system.error.exception

class UnauthorizedException(override val message: String) : RuntimeException(message) {
}