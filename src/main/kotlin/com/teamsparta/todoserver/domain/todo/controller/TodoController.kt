package com.teamsparta.todoserver.domain.todo.controller

import com.teamsparta.todoserver.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todoserver.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController (private val todoService: TodoService) {

    @GetMapping
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodos())
    }

    @GetMapping("/{todoId}")
    fun getTodoById(@PathVariable todoId: Long) : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId))
    }

    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest):ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable todoId: Long, @RequestBody updateTodoRequest: UpdateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long) : ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}