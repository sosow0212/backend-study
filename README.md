# 퇴근 후 진행하는 학습용 Repository

- 아키텍처, 코틀린, 스프링, 회사에서 사용하는 기술들을 학습해보자

## 목차

---

### Kotlin (24.12.20 시작, 진행중)

- [ ] Kotlin 학습
    - branch : kotlin-racing-console
    - 간단한 자동차 경주 console 프로그램 만들기
        - mvc 버전
        - 헥사고날 버전
    - 관련 서적 : 이펙티브 코틀린
    - Kotest 사용해보기

---

### Spring(Kotlin)

- [ ] Kotlin + Spring FW 사용해보기
    - branch : kotlin-spring-basic
    - Java와 다른 점 고민해보기

---

### Kotlin Spring + Hexagonal architecture 맛보기 (24.12.19 시작, 진행중)

- 24.12.20 (basic -> 추후 다시 보기 현재는 코틀린이 너무 미흡)
- [ ] Kotlin + Spring FW 헥사고날 아키텍처 맛보기
    - branch : hexagonal-architecture
    - 관련 서적 : 헥사고날 아키텍처 설계와 구현
    - Entity, Domain 분리해보고 고민해보기

---

### 전자세금계산서 현업 관련 (API Call)

- TODO. 요청을 정확히 하나만 전달하고, 실패 경우 핸들링하기
    - API 서버에서 Timeout이 발생한 경우도 여러 케이스 생각해야 함 (ex. API 서버엔 요청이 갔지만 응답 중 타임아웃 or API 서버에 요청이 가기 전 타임아웃 등등)
    - 실패한 경우 어떻게 하면 효율적으로 재처리 하면 좋을지, 재처리 과정을 했음에도 실패한다면 어떻게 관리하는 게 효율적일지 고민 필요