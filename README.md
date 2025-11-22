# 37-SOPKATHON-SERVER-ANDROID3

SOPT 37기 솝커톤 프로젝트의 백엔드 서버입니다.

## 📚 Tech Stack

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Swagger (SpringDoc OpenAPI)**

## 🏗️ 프로젝트 구조

```
src/main/java/sopt/sopkathon37/
├── Application.java                    # 메인 애플리케이션 클래스
├── domain/                             # 도메인 계층
│   └── {도메인명}/                      
│       ├── controller/                 # 컨트롤러 계층
│       │   └── dto/                   # DTO 패키지
│       │       ├── request/           # 요청 DTO
│       │       └── response/          # 응답 DTO
│       ├── service/                   # 서비스 계층
│       └── repository/                # 레포지토리 계층
│           └── {Entity}.java          # JPA 엔티티
└── global/                            # 글로벌 설정 및 공통 모듈
    ├── config/                        # 설정 클래스
    ├── exception/                     # 예외 처리
    │   ├── GlobalExceptionHandler.java
    │   └── ServerException.java
    ├── message/                       # 응답 메시지
    │   ├── ErrorMessage.java
    │   └── SuccessMessage.java
    └── response/                      # API 응답 포맷
        ├── ApiResponseUtil.java
        └── BaseResponse.java
```

### 도메인 주도 설계 (DDD)

- 각 도메인은 `domain` 패키지 하위에 독립적인 패키지로 구성
- 도메인 내부는 `controller`, `service`, `repository` 3계층 구조
- DTO는 컨트롤러 내부의 `dto` 패키지에서 관리

## 📝 네이밍 컨벤션

### 패키지 네이밍

- 모든 패키지명은 **소문자** 사용
- 단어 구분은 하이픈 없이 연결 (예: `controller`, `repository`)

### 클래스 네이밍

- **Controller**: `{도메인명}Controller`
    - 예: `ExampleController`
- **Service**: `{도메인명}Service`
    - 예: `ExampleService`
- **Repository**: `{도메인명}Repository`
    - 예: `ExampleRepository`
- **Entity**: `{도메인명}` (단수형)
    - 예: `Example`
    - 테이블명은 복수형 사용: `@Table(name = "examples")`
- **Request DTO**: `{기능명}Request`
- **Response DTO**: `{기능명}Response`
    - 예: `ExampleResponse`

### 메서드 네이밍

- **Controller 메서드**: HTTP 메서드 + 리소스명
    - `getExample()`, `createExample()`, `updateExample()`, `deleteExample()`
- **Entity 정적 팩토리 메서드**: `create()`
    - 예: `Example.create(String name)`
- **Response DTO 변환 메서드**: `from()` 또는 `of()`
    - `ExampleResponse.from(Example example)`

### 변수 네이밍

- **camelCase** 사용
- 의존성 주입 필드는 `final` 선언 및 생성자 주입 사용 (`@RequiredArgsConstructor`)

## 🏛️ MVC 레이어 아키텍처

### 1. Controller Layer (Presentation)

**역할**: HTTP 요청/응답 처리, 입력 검증, 서비스 레이어 호출

```java

@RestController
@RequiredArgsConstructor
@RequestMapping("/examples")
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/{exampleId}")
    public ResponseEntity<BaseResponse<ExampleResponse>> getExample() {
        return ApiResponseUtil.success(SuccessMessage.SUCCESS,
            ExampleResponse.from(Example.create("example")));
    }
}
```

**규칙**:

- `@RestController` 사용
- `@RequiredArgsConstructor`로 생성자 주입
- 메서드 반환 타입: `ResponseEntity<BaseResponse<T>>`
- `ApiResponseUtil`을 통한 통일된 응답 반환

### 2. Service Layer (Business Logic)

**역할**: 비즈니스 로직 처리, 트랜잭션 관리

```java

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    // 비즈니스 로직 구현
}
```

**규칙**:

- `@Service` 사용
- `@RequiredArgsConstructor`로 생성자 주입
- 비즈니스 로직과 트랜잭션 처리

### 3. Repository Layer (Data Access)

**역할**: 데이터베이스 접근 및 영속성 관리

```java
public interface ExampleRepository extends JpaRepository<Example, Long> {
    // 커스텀 쿼리 메서드
}
```

**규칙**:

- `JpaRepository<Entity, ID>` 상속
- 메서드명 기반 쿼리 생성 활용

### 4. Entity

**역할**: 데이터베이스 테이블과 매핑되는 도메인 객체

```java

@Table(name = "examples")
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Builder
    private Example(String name) {
        this.name = name;
    }

    public static Example create(String name) {
        return Example.builder()
            .name(name)
            .build();
    }
}
```

**규칙**:

- `@Entity`, `@Table` 사용 (테이블명은 복수형)
- `@NoArgsConstructor(access = AccessLevel.PROTECTED)` 사용
- `@Builder` + 정적 팩토리 메서드 패턴 사용
- `@Getter` 사용, Setter 지양

## 📡 API 응답 스펙

### 기본 응답 포맷

모든 API 응답은 `BaseResponse<T>` 형식을 따릅니다.

```java
public record BaseResponse<T>(
    int status,          // HTTP 상태 코드
    String message,      // 응답 메시지
    T data              // 응답 데이터 (없을 경우 null)
)
```

### 성공 응답

#### 1. 데이터가 있는 성공 응답

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": {
    "id": 1,
    "name": "example"
  }
}
```

#### 2. 데이터가 없는 성공 응답

```json
{
  "status": 200,
  "message": "요청이 성공했습니다."
}
```

### 성공 메시지 종류 (`SuccessMessage`)

| 메시지       | 상태 코드 | 설명             |
|-----------|-------|----------------|
| `SUCCESS` | 200   | 요청이 성공했습니다.    |
| `CREATED` | 201   | 생성되었습니다.       |
| `DELETED` | 200   | 삭제가 완료되었습니다.   |
| `UPDATED` | 200   | 업데이트가 완료되었습니다. |

### 에러 응답

```json
{
  "status": 400,
  "message": "요청 형식이 올바르지 않습니다."
}
```

### 에러 메시지 종류 (`ErrorMessage`)

| 메시지                     | 상태 코드 | 설명                     |
|-------------------------|-------|------------------------|
| `BAD_REQUEST`           | 400   | 요청 형식이 올바르지 않습니다.      |
| `FORBIDDEN`             | 403   | 리소스 접근 권한이 없습니다.       |
| `NOT_FOUND`             | 404   | 요청하는 리소스가 존재하지 않습니다.   |
| `METHOD_NOT_ALLOWED`    | 405   | 잘못된 HTTP Method 요청입니다. |
| `CONFLICT`              | 409   | 이미 존재하는 리소스입니다.        |
| `INTERNAL_SERVER_ERROR` | 500   | 서버 내부 오류입니다.           |

### 예외 처리

`GlobalExceptionHandler`에서 전역 예외를 처리합니다.

```java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<BaseResponse<Void>> handleServerException(ServerException e) {
        return ApiResponseUtil.failure(e.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception e) {
        return ApiResponseUtil.failure(ErrorMessage.INTERNAL_SERVER_ERROR);
    }
}
```

## 📖 API 문서

서버 실행 후 다음 URL에서 Swagger 문서를 확인할 수 있습니다:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/openapi

## 🏗️ Service Architecture

![솝커톤](https://github.com/user-attachments/assets/3a63c775-f329-495d-81c5-c344d03e8ea0)

