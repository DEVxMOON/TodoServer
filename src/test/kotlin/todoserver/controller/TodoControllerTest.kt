package todoserver.controller

import com.teamsparta.todoserver.todo.controller.TodoController
import com.teamsparta.todoserver.todo.dto.TodoRequest
import com.teamsparta.todoserver.todo.dto.TodoResponse
import com.teamsparta.todoserver.todo.dto.UpdateTodoDoneRequest
import com.teamsparta.todoserver.todo.service.TodoService
import com.teamsparta.todoserver.user.dto.GetUserInfoRequest
import com.teamsparta.todoserver.user.dto.UserResponse
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@SpringBootTest
class TodoControllerTest : StringSpec({
    val todoService = mockk<TodoService>()
    val todoController = TodoController(todoService)
    val token = "123"
    val user = UserResponse(0, "HR", "hr")

    //getTodoList 테스트 X, 성공사례만 작성

    "getTodoById should return the correct response" {
        val todoId = 1L
        val todo = TodoResponse(todoId, "Title", user.name, LocalDateTime.now(), "Body", true, comments = listOf())
        every { todoService.getTodoById(todoId) } returns todo

        val response = todoController.getTodoById(todoId)

        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe todo
    }

    "createTodo should create and return the correct response" {
        val todoId = 1L
        val todoRequest = TodoRequest("Title", "Body", LocalDateTime.now(), GetUserInfoRequest(token))
        val todoResponse = TodoResponse(todoId, "Title", user.name, LocalDateTime.now(), "Body", true, comments = listOf())
        every { todoService.validateToken(token) } returns user
        every { todoService.createTodo(user, todoRequest) } returns todoResponse

        val response = todoController.createTodo(todoRequest)

        response.statusCode shouldBe HttpStatus.CREATED
        response.body shouldBe todoResponse
    }

    "updateTodo should update and return the correct response" {
        val todoId = 1L
        val todoRequest = TodoRequest("Title", "Body Updated", LocalDateTime.now(), GetUserInfoRequest(token))
        val todoResponse = TodoResponse(todoId, "Title", user.name, LocalDateTime.now(), "Body Updated", true, comments = listOf())
        every { todoService.checkOwner(token, todoId) } returns true
        every { todoService.updateTodo(todoId, todoRequest) } returns todoResponse

        val response = todoController.updateTodo(todoId, todoRequest, mockk(relaxed = true), GetUserInfoRequest(token))

        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe todoResponse
        verify(exactly = 1) { todoService.updateTodo(todoId, todoRequest) }
    }

    "deleteTodo should return the correct response" {
        val todoId = 1L
        every { todoService.checkOwner(token, todoId) } returns true
        every { todoService.deleteTodo(todoId) } returns Unit

        val response = todoController.deleteTodo(todoId, GetUserInfoRequest(token))

        response.statusCode shouldBe HttpStatus.NO_CONTENT
        verify(exactly = 1) { todoService.deleteTodo(todoId) }
    }

    "makeTodoDone should return the correct response" {
        val todoId = 1L
        val updateTodoDoneRequest = UpdateTodoDoneRequest(true)
        val todoResponse = TodoResponse(todoId, "Title", user.name, LocalDateTime.now(), "Body", true, comments = listOf())
        every { todoService.checkOwner(token, todoId) } returns true
        every { todoService.makeTodoDone(todoId, updateTodoDoneRequest) } returns todoResponse

        val response = todoController.makeTodoDone(todoId, updateTodoDoneRequest, GetUserInfoRequest(token))

        response.statusCode shouldBe HttpStatus.OK
        response.body shouldBe todoResponse
        verify(exactly = 1) { todoService.makeTodoDone(todoId, updateTodoDoneRequest) }
    }
})
