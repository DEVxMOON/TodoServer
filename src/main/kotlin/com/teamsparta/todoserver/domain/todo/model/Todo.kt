package com.teamsparta.todoserver.domain.todo.model

import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "todo")
class Todo(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "author", nullable = false)
    var author: String,

    @Column(name = "body")
    var body: String?,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDate,

    @Column(name="done", nullable = false)
    var done: Boolean=false,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id=id!!,
        title=title,
        author=author,
        date=createdAt,
        body = body!!,
    )
}