package com.teamsparta.todoserver.todo.entity

import com.teamsparta.todoserver.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime

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
    var createdAt: LocalDateTime,

    @Column(name="done", nullable = false)
    var done: Boolean=false,

    @OneToMany(mappedBy = "todo",cascade = [(CascadeType.ALL)],orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf(),
    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment){
        comments.add(comment)
    }

    fun deleteComment(comment: Comment){
        comments.remove(comment)
    }
}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id=id!!,
        title=title,
        author=author,
        date=createdAt,
        body = body!!,
        done=done,
        comments = comments.map{it.toResponse()}
    )
}