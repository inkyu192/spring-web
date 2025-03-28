## 개발 환경
- **Language & Framework:** Java, Kotlin, Spring Web, Spring Data JPA, QueryDSL
- **Database:** MySQL, PostgreSQL, Redis
- **Test:** JUnit5, Kotest, Mockito
- **Etc:** Docker, Docker Compose, Gradle

---

## 환경 설정

`docker-compose.yml`을 사용하여 MySQL, PostgreSQL, Redis를 실행할 수 있다.

```yaml
services:
  mysql:
    image: mysql:latest
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: my_db
      MYSQL_USER: my_user
      MYSQL_PASSWORD: my_password
    ports:
      - "3306:3306"

  postgres:
    image: postgres:latest
    environment:
      POSTGRES_DB: my_db
      POSTGRES_USER: my_user
      POSTGRES_PASSWORD: my_password
    ports:
      - "5432:5432"

  redis:
    image: redis:latest
    ports:
      - "6379:6379"
```

---

## 아키텍처
```sh  
┌── application  # 애플리케이션 계층  
│  
├── domain  # 도메인 계층  
│  
├── infrastructure  # 인프라스트럭처 계층  
│  
└── presentation  # 프레젠테이션 계층  
```
- **application (애플리케이션 계층):** 사용자 요청을 처리하고 도메인 계층을 활용한다.
- **domain (도메인 계층):** 비즈니스 규칙과 도메인 모델을 관리한다.
- **infrastructure (인프라스트럭처 계층):** 외부 시스템, DB, 메시징, 설정 등 기술적 세부 사항을 관리한다.
- **presentation (프레젠테이션 계층):** 클라이언트 요청을 수신하고 애플리케이션 계층에 전달한다.
