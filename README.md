# Todo App Server
Spring Boot
Kotlin


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


# 패키지 구조

```kotlin
domain
  ⎿ todo
      ⎿ controller
      ⎿ service
      ⎿ repository
      ⎿ dto
      ⎿ model
  ⎿ comment
      ⎿ controller
      ⎿ service
      ⎿ repository
      ⎿ dto
      ⎿ model
exception
infra
  ⎿ swagger
```

# 구현부
(추후 업데이트 예정)
