package com.teamsparta.todoserver.domain.todo.service

import com.teamsparta.todoserver.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoRequest

interface TodoService {
    fun getAllTodos():List<TodoResponse>
    fun getTodoById(todoId:Long): TodoResponse
    fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse
    fun updateTodo(todoId:Long, updateTodoRequest: UpdateTodoRequest): TodoResponse
    fun deleteTodo(todoId:Long)
    fun updateTodoDone(todoId:Long, updateTodoDoneRequest: UpdateTodoDoneRequest):TodoResponse
}