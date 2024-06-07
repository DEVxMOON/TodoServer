package com.teamsparta.todoserver.todo.controller

import com.teamsparta.todoserver.user.dto.GetUserInfoRequest
import com.teamsparta.todoserver.todo.dto.TodoRequest
import com.teamsparta.todoserver.todo.dto.TodoResponse
import com.teamsparta.todoserver.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
class TodoController(private val todoService: TodoService) {

    @GetMapping
    fun getTodoList(
        @RequestParam(defaultValue = "desc") order: String,
        @RequestParam(defaultValue = "") author: String,
        @RequestParam limit: Int,
        @RequestParam offset: Int
    ): ResponseEntity<List<TodoResponse>> {
        val todos = if (author.isNotBlank()) todoService.getAllTodos(limit, offset)
            .filter { it.author == author } else todoService.getAllTodos(limit, offset)

        val sortedTodos = when (order) {
            "asc" -> todos.sortedBy { it.date }
            "desc" -> todos.sortedByDescending { it.date }
            else -> throw IllegalArgumentException("Invalid order parameter: $order")
        }

        return ResponseEntity.status(HttpStatus.OK).body(sortedTodos)
    }

    @GetMapping("/{todoId}")
    fun getTodoById(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoById(todoId))
    }

    @PostMapping
    fun createTodo(
        @Valid @RequestBody todoRequest: TodoRequest,
        bindingResult: BindingResult,
        @RequestBody getUserInfoRequest: GetUserInfoRequest
    ): ResponseEntity<TodoResponse> {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
        return todoService.validateToken(getUserInfoRequest.token).let{
            ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(it.name,todoRequest))
        }
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @Valid @RequestBody todoRequest: TodoRequest,
        bindingResult: BindingResult,
        @RequestBody getUserInfoRequest: GetUserInfoRequest
    ): ResponseEntity<TodoResponse> {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return todoService.checkOwner(getUserInfoRequest.token,todoId).let{
            ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodo(todoId, todoRequest))
        }

    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @PathVariable todoId: Long,
        @RequestBody getUserInfoRequest: GetUserInfoRequest

    ): ResponseEntity<Unit> {
        return todoService.checkOwner(getUserInfoRequest.token,todoId).let{
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(todoService.deleteTodo(todoId))
        }
    }

    @PatchMapping("/{todoId}")
    fun makeTodoDone(
        @PathVariable todoId: Long,
        @RequestBody updateTodoDoneRequest: UpdateTodoDoneRequest,
        @RequestBody getUserInfoRequest: GetUserInfoRequest
    ): ResponseEntity<TodoResponse> {
        return todoService.checkOwner(getUserInfoRequest.token,todoId).let{
            ResponseEntity.status(HttpStatus.OK).body(todoService.makeTodoDone(todoId,updateTodoDoneRequest))
        }
    }
}