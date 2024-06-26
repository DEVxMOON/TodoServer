package com.teamsparta.todoserver.todo.entity

import com.teamsparta.todoserver.todo.dto.CommentResponse
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(
    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name ="body")
    var body: String,

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    var todo: Todo,

    ){
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id=id!!,
        name=name,
        body = body,
    )
}