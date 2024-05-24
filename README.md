# Todo App Server
Spring Boot 이해 프로젝트

사용 언어 : Kotlin

스프링부트의 기본적인 동작 방법을 배울 수 있었다.

본 프로젝트는 기본적으로 Todo에 대한 정보를 저장하고, 각각의 Todo에 comment 정보를 담는다.


# 목차
API 명세
ERD
패키지 구조
구현

# API 명세

| Command | Method | API Path | Response |
| --- | --- | --- | --- |
| 할일 카드 작성 | POST | /todos | 201 |
| 할일 카드 목록 조회 | GET | /todos | 200 |
| 선택한 할일 조회 | GET | /todos/{id} | 200 |
| 선택한 할일 수정 | PUT | /todos/{id} | 200 |
| 선택한 할일 삭제 | DELETE | /todos/{id} | 204 |
| 선택한 할일 완료 | PATCH | /todos/{id}/done | 200 |
| 댓글 작성 | POST | /todos/{id}/comments | 200 |
| 댓글 수정 | PUT | /todos/{id}/comments/{commentId} | 200 |
| 댓글 삭제 | DELETE | /todos/{id}/comments/{commentId} | 204 |

# ERD
![todo-app](https://github.com/DEVxMOON/TodoServer/assets/137713546/05cccc09-910d-44f8-b26e-9e33206745a6)

- TODO 항목이 사라질 경우 연결되어있는 댓글도 삭제된다. (참조 무결성)

# 패키지 구조
도메인 구조에서 계층형 구조로 변경.(Todo의 서브 도메인이 Comment)

```kotlin
controller
	 ⎿ TodoController
	 ⎿ CommentController
	 
service
	 ⎿ TodoService
 	 ⎿ TodoServiceImpl
	 ⎿ CommentService
	 ⎿ CommentServiceImpl
	 
repository
	 ⎿ TodoRepository
	 ⎿ CommentRepository
	 
dto
	 ⎿ userDto
	 ⎿ courseDto
	 
entity
	 ⎿ Todo
	 ⎿ Comment

common
	 ⎿ exception
	     ⎿ ModelNotFoundException

infra
	 ⎿ SwaggerConfig
```

# 구현부

1. Todo는 생성, 삭제, 수정, 그리고 불러오기가 가능하다. 불러오기는 2가지 방법이 존재하는데, Id를 지정해 불러오는 것과 전체 불러오기가 있다.
2. 전체 불러오기의 경우 정렬 조건이 존재한다. - 작성 시간에 따른 정렬 (default : desc)
3. 전체 불러오기의 경우 특정 사용자의 Todo만 불러올 수 있다. - 작성자명을 보낼 경우, 해당 작성자의 Todo만 필터링
4. Todo의 데이터 양이 많아질 경우를 대비해 pagination기능을 추가하여 검색시간을 줄였다. - offset 활용
6. 각각의 Todo에는 Comment를 달 수 있다.
7. Comment는 생성, 삭제, 수정이 가능하다.
8. Comment 생성시 작성자명, 패스워드, 그리고 내용을 생성한다.
9. 삭제 및 수정은 작성자명과 패스워드가 일치해야지만 가능하다.
10. Todo를 삭제할 경우 해당 Todo에 연결되어있는 Comment들 또한 자동으로 삭제된다.


