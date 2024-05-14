package com.teamsparta.todoserver.domain.todo.controller

import com.teamsparta.todoserver.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController {

    @GetMapping
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        TODO()
    }

    @GetMapping("/{todoId}")
    fun getTodoById(@PathVariable todoId: Int) : ResponseEntity<TodoResponse> {
        TODO()
    }

    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest):ResponseEntity<TodoResponse> {
        TODO()
    }

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable todoId: Int, @RequestBody updateTodoRequest: UpdateTodoRequest): ResponseEntity<TodoResponse> {
        TODO()
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Int) : ResponseEntity<TodoResponse> {
        TODO()
    }

}