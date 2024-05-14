# 스프링 핵심 원리 프로젝트

## 핵심 원리

### 1. 의존성 주입(Dependency Injection, DI)

- `ApplicationContext`: 스프링이 제공하는 IoC 컨테이너로, 객체를 생성하고 의존성을 주입하는 역할을 합니다.
- `@Autowired`: 의존성 주입을 위한 어노테이션으로, 스프링 컨테이너가 자동으로 해당 타입의 빈을 주입합니다.
- `@Component`, `@Service`, `@Repository`, `@Controller`: 스프링 빈으로 등록하기 위한 어노테이션입니다.

### 2. 제어의 역전(Inversion of Control, IoC)

- 객체의 생성과 관리를 개발자가 아닌 스프링 컨테이너가 담당합니다.
- 개발자는 객체 간의 의존성을 설정하고, 스프링 컨테이너가 객체를 생성하고 주입하는 방식으로 프로그램을 작성합니다.

### 3. AOP (Aspect-Oriented Programming)

- `@Aspect`: 관점 지향 프로그래밍을 위한 어노테이션으로, 특정한 기능을 적용할 대상을 지정합니다.
- `@Pointcut`: 어드바이스를 적용할 타겟 메서드를 지정하기 위한 포인트컷을 정의합니다.
- `@Before`, `@After`, `@Around`: 어드바이스를 정의하는 어노테이션입니다.
