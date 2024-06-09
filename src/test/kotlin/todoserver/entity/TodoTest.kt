package todoserver.entity

import com.teamsparta.todoserver.todo.entity.Comment
import com.teamsparta.todoserver.todo.entity.Todo
import com.teamsparta.todoserver.todo.entity.toResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class TodoTest : StringSpec({
    "todo 객체 생성 - 전체 조건 만족(comment 0)" {
        val createAt = LocalDateTime.now()
        val todo = Todo(title = "Test Todo", author = "Test Author", body = "Test Body", createdAt = createAt)

        todo.title shouldBe "Test Todo"
        todo.title.length shouldBeInRange 1..200
        todo.author shouldBe "Test Author"
        todo.body shouldBe "Test Body"
        todo.title.length shouldBeInRange 1..1000
        todo.createdAt shouldBe createAt
        todo.done shouldBe false
        todo.comments.size shouldBe 0
    }

    "todo 객체 생성 - 전체 조건 만족(comment 1개 이상)" {
        val createAt = LocalDateTime.now()
        val todo = Todo(title = "Test Todo", author = "Test Author", body = "Test Body", createdAt = createAt)
        val comment = Comment(name = "Author", body= "comment body", todo)

        todo.addComment(comment)

        todo.title shouldBe "Test Todo"
        todo.title.length shouldBeInRange 1..200
        todo.author shouldBe "Test Author"
        todo.body shouldBe "Test Body"
        todo.title.length shouldBeInRange 1..1000
        todo.createdAt shouldBe createAt
        todo.done shouldBe false
        todo.comments.size shouldBe 1
    }

    //실패 case
    "todo 제목 글자수가 0이거나 최대 글자수(200) 초과" {
        shouldThrow<IllegalArgumentException> {
            Todo(
                title = "",
                author = "Test Author",
                body = "Test Body",
                createdAt = LocalDateTime.now()
            )
        }

        shouldThrow<IllegalArgumentException> {
            Todo(
                title = "a".repeat(201),
                author = "Test Author",
                body = "Test Body",
                createdAt = LocalDateTime.now()
            )
        }
    }

    //실패 case
    "todo 내용 글자수가 0이거나 최대 글자수(1000) 초과" {
        shouldThrow<IllegalArgumentException> {
            Todo(
                title = "Test Todo",
                author = "Test Author",
                body = "",
                createdAt = LocalDateTime.now()
            )
        }

        shouldThrow<IllegalArgumentException> {
            Todo(
                title = "Test Todo",
                author = "Test Author",
                body = "a".repeat(1001),
                createdAt = LocalDateTime.now()
            )
        }
    }

    "todo 객체 -> toResponse 로" {
        val createAt = LocalDateTime.now()

        val todo = Todo(title = "Test Todo", author = "Test Author", body = "Test Body", createdAt = createAt)
        todo.id = 1L

        val response = todo.toResponse()
        response.id shouldBe 1L
        response.title shouldBe "Test Todo"
        response.author shouldBe "Test Author"
        response.body shouldBe "Test Body"
        response.date shouldBe createAt
        response.done shouldBe false
        response.comments.size shouldBe 0
    }
})