package todoserver.service

import com.teamsparta.todoserver.todo.dto.TodoRequest
import com.teamsparta.todoserver.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.todo.entity.Todo
import com.teamsparta.todoserver.todo.repository.TodoRepository
import com.teamsparta.todoserver.todo.service.TodoService
import com.teamsparta.todoserver.user.dto.GetUserInfoRequest
import com.teamsparta.todoserver.user.dto.UserResponse
import com.teamsparta.todoserver.user.service.UserService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.time.LocalDateTime
import java.util.*

class TodoServiceTest : StringSpec({
    val todoRepository = mockk<TodoRepository>()
    val userService = mockk<UserService>()
    val todoService = TodoService(todoRepository, userService)
    val token = "123"
    val user = UserResponse(0, "HR", "hr")

    // getAllTodos, validateToken, checkOwner 테스트 X
    // 성공 테스트만 작성해둠.

    "getTodoById should return correct response" {
        val todoId = 5L
        val todo = Todo(
            title = "Title",
            author = user.name,
            body = "Body",
            createdAt = LocalDateTime.now()
        ).apply { id = todoId }
        every { todoRepository.findById(todoId) } returns Optional.of(todo)

        val result = todoService.getTodoById(todoId)

        result.title shouldBe "Title"
        result.body shouldBe "Body"
    }

    "createTodo should return correct response" {
        val todoId = 5L
        val todoRequest = TodoRequest(
            "Title",
            "Body",
            LocalDateTime.now(),
            GetUserInfoRequest(token)
        )
        val todo = Todo(
            title = todoRequest.title,
            author = user.name,
            body = todoRequest.body,
            createdAt = todoRequest.date
        ).apply { id = todoId }

        every { todoRepository.save(any<Todo>()) } returns todo

        val result = todoService.createTodo(user, todoRequest)

        result.title shouldBe "Title"
        result.body shouldBe "Body"

        verify(exactly = 1) { todoRepository.save(any<Todo>()) }
    }

    "updateTodo should return correct response" {
        val todoId = 5L
        val existingTodo = Todo(
            title = "Title",
            author = user.name,
            body = "Body",
            createdAt = LocalDateTime.now()
        ).apply { id = todoId }

        val todoRequest = TodoRequest(
            "Title",
            "Body changed",
            LocalDateTime.now(),
            GetUserInfoRequest(token)
        )

        every { todoRepository.findById(todoId) } returns Optional.of(existingTodo)
        every { todoRepository.save(any<Todo>()) } returns existingTodo.apply { body = todoRequest.body }

        val result = todoService.updateTodo(todoId, todoRequest)

        result.title shouldBe "Title"
        result.body shouldBe "Body changed"

        verify(exactly = 1) { todoRepository.save(existingTodo) }
    }


    "deleteTodo should return correct response" {
        val todoId = 5L
        val todo = Todo(
            title = "Title",
            author = user.name,
            body = "Body",
            createdAt = LocalDateTime.now()
        ).apply { id = todoId }

        every { todoRepository.findById(todoId) } returns Optional.of(todo)
        every { todoRepository.delete(todo) } just Runs

        todoService.deleteTodo(todoId)

        verify(exactly = 1) { todoRepository.delete(todo) }
    }

    "makeTodoDone should return correct response" {
        val todoId = 5L
        val todo = Todo(
            title = "Title",
            author = user.name,
            body = "Body",
            createdAt = LocalDateTime.now(),
            done = false
        ).apply { id = todoId }

        every { todoRepository.findById(todoId) } returns Optional.of(todo)
        every { todoRepository.save(any<Todo>()) } answers { firstArg() }

        val updateTodo = UpdateTodoDoneRequest(done = true)
        val result = todoService.makeTodoDone(todoId, updateTodo)

        result.done shouldBe true

        verify(exactly = 1) { todoRepository.save(todo) }
    }
})
