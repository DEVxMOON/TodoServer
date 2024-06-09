package todoserver.entity

import com.teamsparta.todoserver.todo.entity.Comment
import com.teamsparta.todoserver.todo.entity.Todo
import com.teamsparta.todoserver.todo.entity.toResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class CommentTest : StringSpec ({
    val createAt = LocalDateTime.now()
    val todo = Todo(title = "Test Todo", author = "Test Author", body = "Test Body", createdAt = createAt)

    "comment 객체 생성 - 전체 조건 만족"{
        val comment = Comment(name = "Author", body= "Comment Body", todo)

        comment.name shouldBe "Author"
        comment.body shouldBe "Comment Body"
        comment.todo shouldBe todo
    }

    //실패 case
    "comment body 가 null 인 경우"{
        shouldThrow<IllegalArgumentException> {
            Comment(name = "Author", body = "", todo)
        }
    }

    "comment 객체 -> toResponse 로"{
        val comment = Comment(name = "Author", body = "Comment Body", todo)
        comment.id = 1L

        val response = comment.toResponse()
        response.id shouldBe 1L
        response.name shouldBe "Author"
        response.body shouldBe "Comment Body"
    }
})