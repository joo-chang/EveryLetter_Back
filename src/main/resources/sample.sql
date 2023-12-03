INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate, profileUrl)values ('jooc00@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창창', 'ROLE_ADMIN', now(3), now(3), 'https://everyletters.s3.ap-northeast-2.amazonaws.com/68d7a3ab-8d91-46b8-8301-75071a04ccf4_FB23503D-DC91-4D08-A978-543BEA20623B_1_105_c.jpeg');
INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate) values ('jooc01@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창1', 'ROLE_LETTER', now(3), now(3));
INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate) values ('jooc02@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창2', 'ROLE_USER', now(3), now(3));
INSERT INTO USER(`email`, `password`, `nickname`, `role`, createdDate, modifiedDate, profileUrl) values ('jooc0311@gmail.com', '{bcrypt}$2a$10$0ELRTFHBSGS0ECkdMmyAauMQCp7Tzp29qOtzOFxCrPt9yVQXwy7AO', '창gmail', 'ROLE_USER', now(3), now(3), 'https://everyletters.s3.ap-northeast-2.amazonaws.com/46594354-345f-471a-a746-637e8efb4684_50EACBF2-0D4C-46AA-98C5-B7AD29515E22_1_105_c.jpeg');
INSERT INTO CATEGORY(`name`, content) value ('IT', '개발자들의 다양한 이야기들을 담는 공간입니다.');
INSERT INTO CATEGORY(`name`, content) value ('수다', '모든 사람들의 고민, 사는 얘기 등 다양한 이야기를 나눠보세요.');
INSERT INTO POST(createdDate, modifiedDate, title, content, categoryId, userId) value (now(3), now(3), 'Java 면접 준비', '### Java 장단점

- **장점**
    1. 운영체제에 독립적
      - JVM에서 동작하기 때문에 플랫폼에 종속적이지 않다.
    2. 객체지향 언어
      - 캡슐화, 상속, 추상화, 다형성 등을 지원하여 객체지향 프로그래밍이 가능하다.
    3. 동적 로딩을 지원
      - 애플리케이션이 실행될 때 모든 객체가 생성되지 않고, 각 객체가 필요한 시점에 클래스를 동적으로 로딩해서 생성된다.
      - 또한 유지보수 시 해당 클래스만 수정하면 되기 때문에 전체 애플리케이션을 다시 컴파일 할 필요가 없다. 따라서 유지보수가 쉽고 빠르다.
- **단점**
    1. 비교적 느림
      - 한번 컴파일링으로 실행 가능한 기계어가 만들어지지 않고, JVM에 의해 기계어로 번역되고 실행되는 과정을 거치기 때문에 실행 속도가 조금 느리다.
    2. 작성해야 하는 코드가 비교적 길다

<br>

---
### 객체지향 프로그래밍의 특징

- 장점
    - 객체지향적 설계로 프로그램을 유연하고 변경이 용이하게 만들 수 있다.
    - 각각의 객체들이 독립적인 역할을 하기 때문에 코드변경을 최소화하고 유지보수가 쉽고 빠르다.
- 특징
- 추상화
    - 사물들의 공통적인 특징을 파악해서 하나의 개념(집합)으로 다루는 것
    - 목적과 관련 없는 부분은 제거하여 필요한 부분만 표현하기 위한 개념
- 캡슐화
    - 정보 은닉 : 사용하지 않는 정보를 외부에서 접근하지 못하도록 제한
    - 캡슐화는 높은 응집도, 낮은 결합도로 유연함과 유지보수성 증가할 수 있게 해준다.
- 상속
    - 기존 클래스를 재활용하여 새롭게 클래스를 정의할 수 있게 도와주는 개념
- 다형성
    - 형태가 같은데 다른 기능을 하는 것을 의미
    - 오버라이딩, 오버로딩

<br>

---
### 객체지향 vs 절차지향

**객체지향 프로그래밍**
- 구현해야할 객체들 사이의 상호작용을 프로그래밍하는 방식
- 상속, 다형성, 캡슐화, 추상화를 통해 결합도를 낮추고 응집도를 높일 수 있으며 코드의 재사용성도 높일 수 있다.

**절차지향 프로그래밍**
- 실행하고자 하는 절차를 정하고, 순차적으로 프로그래밍하는 방식으로 속도가 빠르다.
- 엄격하게 순서가 정해져 있어 비효율적이고 유지보수가 어렵다.
- 목적을 달성하기 위한 일의 흐름에 중점을 둔다.

<br>

---
### 객체지향 설계 원칙 (SOLID)

**단일 책임 원칙 (SRP : Single Response Principle)**

- `하나의 클래스는 하나의 책임` 만 가져야 한다.
- 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것.

**개방-폐쇄 원칙 (OCP : Open-Closed Principle)**

- 클래스는 `확장에 열려있으나 변경에는 닫혀있어야 한다.`
- 기능 추가 시 클래스 확장을 통해 쉽게 구현하고, 확장에 따른 클래스 수정은 최소화 하도록 프로그램을 작성해야 하는 설계 기법이다.

**리스코프 치환 원칙 (LSP : Liskov Substitution Principle)**

- 인터페이스 규약에 맞게 구현해야 한다.
- 자동차 인터페이스의 앞으로 가는 기능을 뒤로 가게 구현하면 LSP 위반.

**인터페이스 분리 원칙 (ISP : Interface Segregation Principle)**

- 인터페이스를 각각 사용에 맞게 끔 잘게 분리해야한다.
- SRP는 클래스의 단일 책임 강조, ISP는 인터페이스의 단일 책임 강조

**의존관계 역전 원칙 (DIP : Dependency Inversion Principle)**

- 추상화에 의존해야지 구체화에 의존하면 안된다.
- 구현 클래스에 의존하지 말고, 인터페이스에 의존해야 한다.

<br>

---
### JVM (Java Virtual Machine)

- JVM 역할은 자바 애플리케이션을 클래스 로더를 통해 읽어 자바 API와 함께 실행하는 것
- Java와 OS 사이에서 중개자 역할을 수행하여 Java가 OS에 구애받지 않고 독립적으로 작동이 가능하다.
- 메모리 관리, Garbage Collection을 수행한다.

**JVM 구성 요소**

- Class Loader : 클래스 파일을 Runtime Data Area의 메서드 영역으로 불러오는 역할
- Execution Engine : 메모리(Runtime Data Area)에 적재된 클래스들을 기계어로 변경해 실행
- Garbage Collector : 메모리 관리 기법 중 하나. 힙 메모리에서 참조되지 않는 개체들 제거
- Runtime Data Area : 런타임 시 실제 데이터가 저장되는 곳. OS로 부터 할당 받은 메모리 영역

**JVM 실행 과정**

1. JVM은 OS로부터 메모리(Runtime Data Area)를 할당 받음
2. 컴파일러(javac)가 소스코드(.java)를 읽어 바이트코드(.class)로 변환
3. Class Loader를 통해 Class 파일을 JVM 내에 Runtime Data Area로 로딩
4. 로딩된 Class 파일을 Execution Engine을 통해 해석 및 실행

**JVM 메모리 구조**

- 메서드(static) 영역
  - 클래스가 사용되면 해당 클래스 파일을 읽어들여 클래스에 대한 정보(바이트 코드)를 메서드 영역에 저장
  - 클래스와 인터페이스, 메서드, 필드, static 변수, final 변수 등이 저장되는 영역
- JVM 스택 영역
  - 스레드마다 존재하여 스레드가 시작할 때마다 할당
  - 지역 변수, 매개 변수, 연산 중 발생하는 임시 데이터 저장
  - 메서드 호출 마다 개별적 스택 생성
- JVM 힙 영역
  - 런타임 시 동적으로 할당하여 사용하는 영역
  - new 연산자로 생성된 객체와 배열 저장
  - 참조가 없는 객체는 GC의 대상
- PC Register
  - JVM은 스택 기반의 가상 머신으로 CPU에 직접 접근하지 않고 Stack에서 주소를 뽑아서 PC Register에 저장된다.
- Native Method Stack
  - Java 이외의 언어에 제공되는 Method 정보가 저장되는 공간
  - Low Level 코드를 실행하는 스택

<br>

### Process/Thread 개념

**프로세스**

- 프로세스는 일반적으로 CPU에 의해 메모리에 올려져 실행중인 프로그램
- 메모리 공간을 포함하여 독립적인 실행 환경을 가지고 있다.
- JVM은 주로 하나의 프로세스로 실행되며, 동시에 여러 작업을 수행하기 위해 멀티 스레드를 지원한다.

**스레드**

- 스레드는 프로세스 안에서 작업을 실행하는 단위
- 자바에서는 JVM에 의해 관리
- 프로세스에는 적어도 한 개 이상의 스레드가 있고 스레드를 추가 생성 시 멀티 스레드 환경이 된다.
- 멀티 스레드들은 프로세스의 리소스를 공유

<br>

---
### Thread-Safe

- 여러 스레드에서 클래스나 클래스의 객체에 동시에 접근하더라도 정확하게 동작하는 것
- 여러 스레드에서 공유되는 클래스에 대한 접근을 관리하는 것

<br>

---
### Syncronized

- 여러 개의 스레드가 한 개의 자원을 사용하고자 할 때, 현재 사용중인 스레드를 제외하고 나머지 스레드들은 접근할 수 없게 막는 개념이다.
- Java에서 Syncronized 키워드를 제공해 멀티 스레드 환경에서 스레드 간 동기화를 시켜 데이터의 Thread-Safe를 보장한다.

<br>

', 1, 1);
INSERT INTO POST(createdDate, modifiedDate, title, content, categoryId, userId) value (now(3), now(3), 'JWT 토큰 발급', '## Session/Cookie vs Token

사용자 인증 방식에는 크게 Session/Cookie, Token 방식이 있다.

### Session / Cookie

Session 방식은 사용자가 로그인 요청을 보내면 사용자를 확인 후 Session ID를 발급하고 그 발급한 ID를 이용하여 다른 요청과 응답을 처리하는 방식이다.

![[Pasted image 20230514225823.png]]

이 경우 프로그램이 커져서 관리하는 Session이 늘어날 경우 별도로 세션 저장소를 관리해주어야 하는 번거로움이 있다.

---

### Token

Token 방식은 저장소 없이 로그인 시 토큰을 발급하고, 데이터 요청 시에 발급받은 토큰을 헤더를 통해 전달하여 응답받는 방식으로 진행된다.

![[Pasted image 20230514225959.png]]


### Token 방식 로그인 흐름

#### 1. 로그인으로 토큰(JWT) 발급

로그인을 통해 토큰을 발급 받는다. jjwt 라이브러리를 이용하여 JWT(JSON Web Token) 방식으로 생성한다.

![[Pasted image 20230514233722.png]]

JWT는 세 부분으로 나누어져 필요한 모든 정보를 자체적으로 지니고 있다는 특징이 있다.
따라서 별도의 디비 접근 없이 해당 토큰의 유효성 검증이 가능하다.


#### 2. 발급 받은 토큰을 웹 스토리지에 저장

프론트에서 로그인 요청을 보내면 백엔드에서 토큰을 발급하여 응답으로 토큰을 보내준다. 그리고 토큰을 웹 스토리지에 저장한다.

Sotrage는 key-value의 형태로 데이터를 저장하는 저장소로, 하위 항목에  Local Storage, Session Stroage, Cookies가 있다.
각 항목별로  정보의 휘발성, 보안 등 특징이 있고 개발자의 의도에 부합하는 특징을 가진 선택지를 고르면 된다.


#### 3. 저장된 토큰을 가지고 나의 정보 요청

로그인 후 Local Storage에 토큰이 저장되어 있는 상태이다. 프론트에서 토큰을 헤더에 담아 백엔드로 요청을 보내고 백엔드에서는 토큰 안에 담긴 정보를 확인하여 그에 해당하는 응답을 준다.

#### Interceptor

![[Pasted image 20230515212539.png]]

인터셉터는 요청이 Controller로 가기 전에 요청을 가로채 작업을 처리할 수 있다. 여기서는 요청 안에 토큰이 있는지 확인하고, 토큰 안에 있는 내용을 디코딩하여 요청안에 다시 넣어주는 작업을 한다.

---

### 구현

#### 1. 로그인으로 토큰 발급

1.1 프론트엔드에서 로그인 요청

```java
public class User {
	@Id
	private Long id;
	private String name;
	private String pwd;
}
```

```javascript
fetch("/login", {
	method: ''post'',
	headers: {
		''content-type'' : ''application/json''
	},
	body : JSON.stringify({
		name : $name.value,
		pwd : $pwd.value
	})
}).then(res => res.json())
	.then(token => {
		localStorage.setItem("jwt", token.accessToken)
		alert("로그인");
	})
```

fetch API로 사용자가 입력한 Id/pwd를 보내면 백엔드에서 생성한 토큰을 응답 받아 localStorage에 저장하는 구조이다.

1.2 Controller

```java

@RestController
public class LoginController{
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
		String token = userService.createToken(loginRequest);
		return ResponseEntity.ok().body(
			new TokenResponse(token, "bearer"));
	}
}
```

LoginRequest 객체를 만들어 프론트에서 보낸 정보를 받아 일련의 과정(UserService.createToken)을 거쳐 토큰을 생성하고, 생성된 토큰은 TokenResponse 객체로 감싸 프론트로 보내준다.

1.3 createToken

UserService.createToken

```java
public String createToken(LoginRequest loginRequest) {
	User user = userRepository.findByName(loginRequest.getName())
	.orElseThrow(IllegalArgumentException::new);
	//비밀번호 확인 등의 유효성 검사 진행
	return jwtTokenProvider.createToken(user.getName());
}
```

핵심 - jwtTokenProvider.createToken
jwt 방식을 이용하여 토큰을 생성하려면 라이브러리를 추가해야 한다.

```
implementation ''io.jsonwebtoken:jjwt:0.9.1''
```

```java
@Component
public class JwtTokenProvider{
	private String secretKey;
	private long validityInMilliseconds;

	public JwtTokenProvider(@Value("${security.jwt.token.secret-key}")
	String secretKey, @Value("${security.jwt.token.expire-length}")
	long validityInMilliseconds){
		this.secretKey =
		Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.validityInMilliseconds = validityInMilliseconds;
	}

	// 토큰 생성
	public String createToken(String subject){
		Claims claims = Jwts.claims().setSubject(subject);
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()
		.setClaims(claims)
		.setIssuedAt(now)
		.setExpiration(validity)
		.signWith(SignatureAlgorithm.HS256, secretKey)
		.compact();
	}

	//토큰에서 값 추출
	public String getSubject(String token) {
		return Jwts.parser().setSigningKey(secretKey).
		parseClaimsJws(token).getBody().getSubject();
	}
	//유효한 토큰인지 확인
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims =
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
```

```
application.properties

security.jwt.token.secret-key= secretsecretsecretsecretsecret security.jwt.token.expire-length= 3600000
```

1.4 Storage 에 토큰 저장

![[Pasted image 20230520210703.png]]

로그인 시 LocalStorage에 jwt라는 이름으로 토큰이 추가된다.

---

### 2. 토큰으로 내 정보 요청

2.1 프론트에서 내 정보 요청

fetch API를 이용하여 내 정보 요청

```javascript

fetch("/info", {
	method: ''get'',
	headers: {
		''content-type'': ''application/json'',
		''Authorization'': ''Bearer '' + localStorage.getItem("jwt")
	}
}).then(res => res.json())
	.then(json => alert("이름 : " + json.name + ", 비밀번호 : " + json.pwd))
```

```java

@GetMappint("/info")
public ResponseEntity<UserResponse> getUserFromToken(HttpServletRequest Request){
	String name = (String) request.getAttribute("name");
	User user = userService.findByName((String) request.getAttribute("name"));
	return ResponseEntity.ok().body(UserResponse.of(user));
}
```

프론트에서 요청 시 헤더에  **''Authorization'': ''Bearer '' + localStorage.getItem("jwt")** 값을 넣어 전송한다.

2.2 Interceptor

요청이 controller에 도달하기 전에 Interceptor로 요청을 가로채 header에 포함된 토큰의 내용을 디코딩하여 그 내용을 다시 요청으로 담아 controller로 전달해 줘야한다.
그러기 위해 어떤 요청에 대해서 인터셉터 할 것인지 interceptor를 등록해 놓아야 한다.
WebMvcConfigurer 를 이용한다.

```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
        System.out.println(">>> 인터셉터 등록");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/info");
    }
}
}
```

WebMvcConfig.addInterceptors에서 ''/info'' 로 들어오는 요청에 대해 bearerAuthInterceptor를 등록해준다. 이렇게 하면 애플리케이션이 실행될 때 인터셉터를 등록하고 그 주소로 들어오는 요청을 기다리는 상태가 된다.

인터셉터를 등록하였고 구현
HandlerInterceptor를 Implements 하여 구현한다.

```java
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {
    private AuthorizationExtractor authExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthInterceptor(AuthorizationExtractor authExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        System.out.println(">>> interceptor.preHandle 호출");
        String token = authExtractor.extract(request, "Bearer");
        if (StringUtils.isEmpty(token)) {
            return true;
        }

        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        String name = jwtTokenProvider.getSubject(token);
        request.setAttribute("name", name);
        return true;

    }
}
```

info라는 주소로 요청을 보내서 Interceptor가 호출되면 interceptor는 prehandle 메서드를 호출한다.
preHandle 동작
- request로 부터 authExtractor.extract로 토큰을 추출
- jwtTokenProvider.getSubject로 토큰을 디코딩
- request.setAttribute로 요청에 디코딩한 값을 세팅

2.3 헤더에서 토큰 추출

```java
@Component
public class AuthorizationExtractor {
    public static final String AUTHORIZATION = "Authorization";
    public static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";

    public String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }
}
```

request 헤더 중에 ''Authorization'' 항목의 값을 가져와서 그 안에 토큰 타입을 제외한 토큰 자체를 가져오는 로직이다.

2.4 Controller

헤더에 토큰을 담아 요청을 보냈고, 요청을 가로채 토큰을 확인한 다음 요청에 그 토큰이 의미하는 값을 담아 주었다. 이후 controller에서 그 값을 받아 응답을 내려주면 된다.

```java
@GetMappint("/info")
public ResponseEntity<UserResponse> getUserFromToken(HttpServletRequest request){
	String name = (String) request.getAttribute("name");
	User user = userService.findByName(name);
	return ResponseEntity.ok().body(UserResponse.of(user));
}
```
', 1, 1);