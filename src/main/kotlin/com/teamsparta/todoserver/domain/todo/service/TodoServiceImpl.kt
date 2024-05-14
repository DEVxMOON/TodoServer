package com.teamsparta.todoserver.domain.todo.service

import com.teamsparta.todoserver.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import com.teamsparta.todoserver.domain.todo.dto.UpdateTodoRequest
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl :TodoService{
    override fun getAllTodos(): List<TodoResponse> {
        TODO("Not yet implemented")
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun deleteTodo(todoId: Long) {
        TODO("Not yet implemented")
    }
}