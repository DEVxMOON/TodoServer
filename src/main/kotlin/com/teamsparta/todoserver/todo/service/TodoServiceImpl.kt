package com.teamsparta.todoserver.todo.service

import com.teamsparta.todoserver.exception.ModelNotFoundException
import com.teamsparta.todoserver.todo.dto.CreateTodoRequest
import com.teamsparta.todoserver.todo.dto.TodoResponse
import com.teamsparta.todoserver.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.todo.dto.UpdateTodoRequest
import com.teamsparta.todoserver.todo.entity.Todo
import com.teamsparta.todoserver.todo.entity.toResponse
import com.teamsparta.todoserver.todo.repository.TodoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(private val todoRepository: TodoRepository) :TodoService{
    override fun getAllTodos(limit:Int,offset:Int): Page<TodoResponse> {
        val pageable = PageRequest.of(offset/limit,limit)
        return todoRepository.findAllWithComments(pageable).map { it.toResponse() }
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            Todo(
                title = createTodoRequest.title,
                body = createTodoRequest.body,
                createdAt = createTodoRequest.date,
                author = createTodoRequest.author
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (title, author, body, createdAt) = updateTodoRequest

        todo.title = title
        todo.author = author
        todo.body = body
        todo.createdAt = createdAt

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        todoRepository.delete(todo)
    }

    @Transactional
    override fun makeTodoDone(todoId: Long, updateTodoDoneRequest: UpdateTodoDoneRequest) :TodoResponse{
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val (done) =updateTodoDoneRequest
        todo.done = done
        return todoRepository.save(todo).toResponse()
    }
}