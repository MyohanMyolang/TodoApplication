package com.sparta.todoapp.system.error.exception

class AlreadyHasMember(override val message: String) : RuntimeException(message) {
}