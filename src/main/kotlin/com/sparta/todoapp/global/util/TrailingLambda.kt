package com.sparta.todoapp.global.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> responseEntity(status: HttpStatus, func: () -> T): ResponseEntity<T> {
//	measureTimedValue(func).also {
//		println("경과시간 : ${it.duration}")
//		return ResponseEntity.status(status).body(it.value) }
	func.invoke().also { return ResponseEntity.status(status).body(it) }
}