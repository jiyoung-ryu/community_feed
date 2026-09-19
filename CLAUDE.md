# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 성격

객체 지향 설계 학습용 커뮤니티 피드 서비스. 프레임워크(Spring 등) 없이 순수 Java + Gradle로 도메인 모델과 계층 분리를 연습하는 코드베이스다. 프로덕션 실행 진입점은 사실상 없고(`Main`은 IntelliJ 기본 템플릿), **테스트가 유일한 실행 수단**이다.

- Java 25 (IntelliJ language level JDK_25, JDK: azul-25)
- JUnit 5 (junit-bom 6.0.0), 외부 라이브러리 없음 — Mockito/AssertJ 등 추가 의존성 없이 `org.junit.jupiter.api.Assertions`와 직접 만든 Fake 객체만 사용

## 명령어

```bash
./gradlew build                                   # 컴파일 + 테스트
./gradlew test                                    # 전체 테스트
./gradlew test --tests 'kr.co.bullets.post.application.PostServiceTest'   # 클래스 단위
./gradlew test --tests '*PostServiceTest.givenPostRequestDtoWhenCreateThenReturnPost'  # 메서드 단위
```
린터/포매터 설정은 없다.

## 아키텍처

### 계층 (패키지 = 계층)

바운디드 컨텍스트별(`user`, `post`)로 나뉘고, 각 컨텍스트 안에서 계층이 갈린다:

- `<context>/domain` — 순수 도메인 객체. 외부 의존 없음. 모든 불변 조건 검증이 여기 생성자/메서드에 있다.
- `<context>/application` — 유스케이스 서비스. 도메인을 조립하고 리포지토리를 호출.
- `<context>/application/interfaces` — 리포지토리 **인터페이스**(포트). 구현체는 main에 없다.
- `<context>/application/dto` — 서비스 입력용 `record`. 이름은 `<Action><Entity>RequestDto`.
- `common/domain` — 컨텍스트 공용 도메인 원시값 (`PositiveIntegerCounter`).

의존 방향은 application → domain 단방향. domain은 어떤 것도 위로 의존하지 않는다. 리포지토리 구현은 테스트 소스의 Fake만 존재하므로(의존성 역전 실습), 저장 로직을 추가할 땐 인터페이스를 먼저 `application/interfaces`에 정의한다.

### 도메인 설계 규칙 (코드 전반에 일관되게 적용됨)

- **검증은 생성자에서**. `Post`, `Comment`, `User` 모두 null 체크를 생성자에서 하고 `IllegalArgumentException`을 던진다.
- **카운터는 원시 int가 아니라 값 객체**. 좋아요/팔로워/팔로잉 수는 전부 `PositiveIntegerCounter`로 감싼다 — 음수 방지 로직이 한 곳에 모인다. (`post/domain/like/LikeCounter`는 이것으로 대체된 잔재로, 실사용처가 없다.)
- **컨텐츠 길이 정책은 `Content` 상속 계층에 캡슐화**. 추상 클래스 `Content`가 템플릿 메서드 `checkLength`를 두고, `PostContent`(5~500자)와 `CommentContent`가 각각 정책을 구현한다. 수정 시 `DatetimeInfo.updateEditDatetime()`이 함께 호출된다.
- **권한 검사는 도메인 안에서**. "작성자만 수정 가능", "본인 글에 좋아요 불가" 같은 규칙은 서비스가 아니라 `Post.updateContent` / `Post.like` 안에 있다.
- **`equals`/`hashCode`는 id 기준**. 엔티티(`User`, `Post`, `Comment`)는 id로만 동등성을 판단한다.
- **중복 상태 검사는 서비스에서**. 이미 좋아요/팔로우 했는지는 도메인이 알 수 없으므로 서비스가 리포지토리에 물어보고 분기한다 (`PostService.likePost`, `UserRelationService.followUser`).

### 테스트 구조

- `common/FakeObjectFactory` — Fake 리포지토리와 서비스를 static 싱글턴으로 묶어 제공하는 수동 DI 컨테이너. 서비스 테스트는 여기서 서비스를 얻는다. **static이라 테스트 간 상태가 공유된다**; 저장소를 비우는 메서드가 없으므로 테스트는 특정 개수/전역 상태에 의존하지 않게 작성한다.
- `<Xxx>ServiceTestTemplate` — 공통 픽스처(유저, 게시물)를 필드로 선언한 부모 클래스. 서비스 테스트는 이를 상속한다.
- `<context>/repository/Fake*Repository` — 인메모리 `Map` 기반 구현. `save`에서 id가 null이면 `store.size() + 1`을 새 id로 부여한다.
- 테스트 메서드 이름은 `given<조건>When<행위>Then<결과>` 카멜케이스이며, 본문은 `// given / when / then` 주석으로 구분한다.
