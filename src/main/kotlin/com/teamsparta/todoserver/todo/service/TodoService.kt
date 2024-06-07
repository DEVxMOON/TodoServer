package com.teamsparta.todoserver.todo.service

import com.teamsparta.todoserver.exception.ModelNotFoundException
import com.teamsparta.todoserver.user.dto.GetUserInfoRequest
import com.teamsparta.todoserver.user.dto.UserResponse
import com.teamsparta.todoserver.user.service.UserService
import com.teamsparta.todoserver.todo.dto.TodoRequest
import com.teamsparta.todoserver.todo.dto.TodoResponse
import com.teamsparta.todoserver.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.todo.entity.Todo
import com.teamsparta.todoserver.todo.entity.toResponse
import com.teamsparta.todoserver.todo.repository.TodoRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userService: UserService
) {
    fun getAllTodos(limit: Int, offset: Int): Page<TodoResponse> {
        val pageable = PageRequest.of(offset / limit, limit)
        return todoRepository.findAllWithComments(pageable).map { it.toResponse() }
    }

    fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    fun createTodo(name:String, todoRequest: TodoRequest): TodoResponse {
        return todoRepository.save(
            Todo(
                title = todoRequest.title,
                body = todoRequest.body,
                createdAt = todoRequest.date,
                author = name
            )
        ).toResponse()
    }

    @Transactional
    fun updateTodo(todoId: Long, todoRequest: TodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (title, body, createdAt) = todoRequest

        todo.title = title
        todo.body = body
        todo.createdAt = createdAt

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        todoRepository.delete(todo)
    }

    @Transactional
    fun makeTodoDone(
        todoId: Long,
        updateTodoDoneRequest: UpdateTodoDoneRequest
    ): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (done) = updateTodoDoneRequest
        todo.done = done
        return todoRepository.save(todo).toResponse()
    }

    fun validateToken(token:String):UserResponse{
        return userService.getUserInfo(GetUserInfoRequest(token))
    }

    fun checkOwner(token:String, feedId:Long):Boolean{
        val todo = todoRepository.findByIdOrNull(feedId)
            ?: throw EntityNotFoundException("feed not found")
        return validateToken(token).name == todo.author
    }

}