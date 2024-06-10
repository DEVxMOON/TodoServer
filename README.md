# 프로젝트 설명

Todo App을 위한 백엔드 서버 개발

Notion : https://hr-devlog.notion.site/TodoApp-Backend-Server-91529934cfb446d3b57d62ce4a07b6a9?pvs=74

## 기능 설명

1. Todo
    1. 게시글 작성 : 로그인한 사용자는 할일 카드를 작성할 수 있다. ( 로그인한 사용자의 이름으로 작성된다.) 
    2. 게시글 수정 : 로그인한 사용자는 자신이 작성한 할일 카드를  수정할 수 있다. 
    3. 게시글 삭제 : 로그인한 사용자는 자신이 작성한 할일 카드를  삭제할 수 있다. 
    4. 게시글 완료 : 로그인한 사용자는 자신이 작성한 할일 카드의 완료 여부를 표시할 수 있다. 
    5. 게시글 전체 보기 : 모든 사용자(비로그인 포함)는 할일 카드를  전부 볼 수 있다.
        1. paging 기법을 사용 - offset 방식
    6. 게시글 상세 보기 : 모든 사용자는 할일 카드의 상세 정보를 볼 수 있다.
2. Comment
    1. 댓글 추가 : 로그인한 사용자는 할일 카드에 댓글을 작성할 수 있다. ( 로그인한 사용자의 이름으로 작성된다.) 
    2. 댓글 수정 : 로그인한 사용자는 자신이 작성한 댓글을 수정할 수 있다.
    3. 댓글 삭제 : 로그인한 사용자는 자신이 작성한 댓글을 삭제할 수 있다.
3. 사용자
    1. 일반 사용자 : 로그인 아이디, 패스워드, 사용자 명을 통해 회원가입을 한다.
    2. 소셜 로그인 사용자 : 소셜 로그인을 통해 회원가입을 한다.

게시글과 댓글은 로그인한 사용자만이 작성하고, 자신이 작성한 글과 댓글만 수정 및 삭제가 가능하다.

## API 명세

| Domain | Command | Method | API Path | Response |
| --- | --- | --- | --- | --- |
| Todo | 할일 카드 작성 | POST | /todos | 201 |
| Todo | 할일 카드 목록 조회 | GET | /todos | 200 |
| Todo | 선택한 할일 조회 | GET | /todos/{id} | 200 |
| Todo | 선택한 할일 수정 | PUT | /todos/{id} | 200 |
| Todo | 선택한 할일 삭제 | DELETE | /todos/{id} | 204 |
| Todo | 선택한 할일 완료 | PATCH | /todos/{id}/done | 200 |
| Comment | 댓글 작성 | POST | /todos/{id}/comments | 200 |
| Comment | 댓글 수정 | PUT | /todos/{id}/comments/{commentId} | 200 |
| Comment | 댓글 삭제 | DELETE | /todos/{id}/comments/{commentId} | 204 |
| User | 사용자 회원가입 | POST | /member/signup | 201 |
| User | 사용자 로그인 | POST | /member/login | 200 |
| User | 사용자 로그아웃 | DELETE | /member/logout | 200 |
| User | 사용자 정보 | POST | /member/userinfo | 204 |

(+ 변경 사항 : User Domain 추가 ) 

## ERD

![Untitled](https://github.com/DEVxMOON/TodoServer/assets/137713546/c84a45e5-fe3c-45bc-8e0d-d221ac6bd538)


( + 변경 사항 : User Domain 추가)

## 패키지 구조

```
src
│─main
│  ├─kotlin
│  │  └─com
│  │      └─teamsparta
│  │          └─todoserver
│  │              ├─exception
│  │              ├─infra
│  │              │  ├─security
│  │              │  │  └─jwt
│  │              │  └─swagger
│  │              ├─todo
│  │              │  ├─controller
│  │              │  ├─dto
│  │              │  ├─entity
│  │              │  ├─repository
│  │              │  └─service
│  │              └─user
│  │                  ├─controller
│  │                  ├─dto
│  │                  ├─entity
│  │                  ├─loginUser
│  │                  │  ├─entity
│  │                  │  ├─repository
│  │                  │  └─service
│  │                  ├─repository
│  │                  └─service
│  └─resources
└─test
    ├─kotlin
    │  └─todoserver
    │      ├─controller
    │      ├─entity
    │      └─service
    └─resources
```
