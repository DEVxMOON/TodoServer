package com.teamsparta.todoserver.domain.todo.controller

import com.teamsparta.todoserver.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.domain.todo.service.TodoService
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
        @RequestParam(defaultValue = "") author: String
    ): ResponseEntity<List<TodoResponse>> {
        val todos = if (author.isNotBlank()) todoService.getAllTodos()
            .filter { it.author == author } else todoService.getAllTodos()

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
    fun createTodo(@Valid @RequestBody createTodoRequest: CreateTodoRequest,
                   bindingResult:BindingResult): ResponseEntity<TodoResponse> {
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(createTodoRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @Valid @RequestBody updateTodoRequest: UpdateTodoRequest,
        bindingResult: BindingResult
    ): ResponseEntity<TodoResponse> {

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity .status(HttpStatus.OK).body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)  .body(todoService.deleteTodo(todoId))
    }

    @PatchMapping("/{todoId}")
    fun updateTodoDone(
        @PathVariable todoId: Long,
        @RequestBody updateTodoDoneRequest: UpdateTodoDoneRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodoDone(todoId, updateTodoDoneRequest))
    }
}