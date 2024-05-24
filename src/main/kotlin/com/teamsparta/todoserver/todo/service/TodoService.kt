package com.teamsparta.todoserver.todo.service

import com.teamsparta.todoserver.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoRequest
import org.springframework.data.domain.Page

interface TodoService {
    fun getAllTodos(limit:Int, offset:Int):Page<TodoResponse>
    fun getTodoById(todoId:Long): TodoResponse
    fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse
    fun updateTodo(todoId:Long, updateTodoRequest: UpdateTodoRequest): TodoResponse
    fun deleteTodo(todoId:Long)
    fun makeTodoDone(todoId:Long, updateTodoDoneRequest: UpdateTodoDoneRequest):TodoResponse
}