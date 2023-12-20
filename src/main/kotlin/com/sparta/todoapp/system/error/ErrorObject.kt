package com.sparta.todoapp.system.error

data class ErrorObject (
    val errorCode : Int,
    val message: String,
    val payload: Any
)