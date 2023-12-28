# Todo Application

## 실행 방법

resources에 .env 파일을 만든다.

```kotlin
POSTGRES_URL = "db URL"
POSTGRES_ID = "db ID"
POSTGRES_PW = "db PW"
```
위와 같이 입력한다.

---

# 주특기 숙련 개인과제

# Step 1
- ## 할일카드 작성 기능
    - `할 일 제목`, `할일 내용`, `작성일`, `작성자 이름` 을 저장할 수 있습니다.
    - 저장된 할 일의 정보를 반환 받아 확인할 수 있습니다.

- ## 선택한 할 일 조회 기능

    - 선택한 할 일의 정보를 조회할 수 있습니다.
    - 반환 받은 할 일 정보에는 `할 일 제목`,`할일 내용`, `작성일`, `작성자 이름`정보가 들어있습니다.

- ## 할일카드 목록 조회 기능

    - 등록된 할 일 전체를 조회할 수 있습니다.
    - 조회된 할 일 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.

- ## 선택한 할 일 수정 기능

    - 선택한 할 일의 `할 일 제목`, `작성자명`, `작성 내용`을 수정할 수 있습니다.
    - 수정된 할 일의 정보를 반환 받아 확인할 수 있습니다.

- ## 선택한 할 일 삭제 기능
    - 선택한 게시글을 삭제할 수 있습니다.

- ## 추가사항
    - Board만들기

# Step 2
- ## **할일카드 완료 기능 API**
  - 완료처리 한 할일카드는 목록조회시 `완료 여부`필드가 TRUE 로 내려갑니다.
  - `완료 여부` 기본값은 FALSE
- ## **댓글 작성 API**
  - 댓글을 작성할 때 `작성자 이름`과 `비밀번호`를 함께 받기
  - 선택한 할 일의 DB 저장 유무를 확인하기
  - 선택한 할 일이 DB에 저장되어 있다면 댓글을 등록하고 등록된 댓글 반환하기
    - 응답에서 `비밀번호`는 제외하고 반환해주세요.
- ## **댓글 수정 API**
  - 선택한 댓글의 DB 저장 유무를 확인하기
  - `작성자 이름`과 `비밀번호`를 함께 받아 저장된 값과 일치하면 수정 가능
  - 선택한 댓글이 있다면 댓글 수정하고 수정된 댓글 반환하기
    - 응답에서 `비밀번호`는 제외하고 반환해주세요.
- ## **댓글 삭제 API**
  - 선택한 댓글의 DB 저장 유무를 확인하기
  - `작성자 이름`과 `비밀번호`를 함께 받아 저장된 값과 일치하면 수정 가능
  - 선택한 댓글이 있다면 댓글 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
- ## 선택한 할 일 조회 기능 응답에 연관된 댓글 목록 추가하기
  - **STEP 1**에서 만든 선택한 할 일 조회 api의 응답에 연관된 댓글 목록을 추가해주세요
  - 연관되지 않은 댓글은 포함되지 않아야 합니다.
  - 할 일 목록 api에는 추가하지 말아주세요.

# Step 3
- 할 일 목록 api에 작성일을 기준으로 오름차순, 내림차순 정렬하는 기능을 추가하기
  - api를 요청할 때 정렬 기준(오름차순, 내림차순)을 포함하기
  - 정렬 기준을 통해 정렬한 할 일 목록 반환하기
- 할 일 목록 api에 작성자를 기준으로 필터하는 기능을 추가하기
  - api를 요청할 때 작성자 이름을 포함하기
  - 작성자 이름이 일치하는 할 일 목록 반환하기
- 할 일 작성, 수정 api에 validation을 추가하기
  - 할 일을 작성하거나 수정할 때, 할일 제목이 1자 이상, 200자 이내인지 검사하기
  - 할 일 본문이 1자 이상 1000자 이하인지 검사하기
  - 조건을 충족하지 않는다면 기능 실패 응답하기
- **ResponseEntity**를 사용하여 api의 응답으로 적절한 코드를 반환해주세요.
  - 조회기능 성공: status 200 OK
  - 작성기능 성공: status 201 Created
  - 수정기능 성공: status 200 OK
  - 삭제기능 성공: status 204 No Content
  - 작성, 수정기능 실패: status 400 Bad Request
## ERD

![erd](https://private-user-images.githubusercontent.com/85920191/293009938-4bb9470a-f477-4e40-b91b-fb73a68fc737.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE3MDM2NzE5OTIsIm5iZiI6MTcwMzY3MTY5MiwicGF0aCI6Ii84NTkyMDE5MS8yOTMwMDk5MzgtNGJiOTQ3MGEtZjQ3Ny00ZTQwLWI5MWItZmI3M2E2OGZjNzM3LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFJV05KWUFYNENTVkVINTNBJTJGMjAyMzEyMjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjMxMjI3VDEwMDgxMlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWU3ODI4MDYxMzdmNmRiZTUwMDk2M2NlN2FjMTkzNTg5MTMxZmIzYTI4YTExN2ZiZjFiN2VhZDNmZTZjNWEzNjYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.8Zh5tzqnH5N4U7UCnY-kq95rNmHfGJ0U3rxs0y9AiSU)

## API 명세서

### auth

### comment

### board

![image](https://github.com/MyohanMyolang/TodoApplication/assets/85920191/ff59fe3f-0e89-4c7e-9a5a-af874e293c59)

### card

---


---
