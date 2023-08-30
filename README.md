# Reservation_system_Chatbot

5/28 작성

9월 달안에 완성해야할 챗봇 예약 시스템

제약조건
1. 카카오톡으로 해야함 -> 요금 발생
2. 데이터를 따로 관리해줘야함 -> MariaDB 사용
3. 서버용 컴퓨터 필요 -> 2달동안은 켜놔야함

필요기능 ( 예약이 완료되었다고 문구가 뜨지 않는다면 예약된것이 아님을 공지해야함 ) 
1. 전 예약시 어떤식으로 예약해야하는지 설명 제공 -> 특수전 안한다고 말하기, 예약받는 위치 알려주기, 그리고 전집 위치 소개
2. 예약시 작성해야할 목록 : 전 얼마어치, 몇시에 찾으러올건지, 전화번호
3. 예약후 전화번호로 찾아가라는 메세지와 함께 예약이 완료되었다는 문구 표시 or 새벽에 사러나오라는 문구 제공(밤샘)
4. 예약이 가득 찰 경우 더이상 받지 못한다는 문자 제공 or 서버를 안 킬 예정
5. 예약 조회 및 수정, 삭제 기능 -> 예약후 명절 하루전까지 취소나 변경이 가능하고 그후에는 불가능으로 공지
6. 선입금은 받지않고 함, 카카오톡으로 하는만큼 전화번호를 기입시킴 -> 나중에 고려해봐야할듯 사기예약등이 존재할 수 도있음
7. 예약 당일날 카카오톡 발송 ( 몇시에 예약이 완료되었으니 늦지않게 찾아가주세요~ ) -> 요금발생


6/25 카카오톡 챗봇 만들기

*카카오톡 챗봇을 만들려면 카카오톡 디벨로퍼에서 허가를 받아야함*

챗봇 관리자 센터 : 카카오  AI 설계 플랫폼이다.

오픈빌더만을 이용해 챗봇을 제작한다면 조금 더 다양한 개념과 사용법을 익혀야하지만 우리가 만든 챗봇 연동에 필요한
두가지 개념만을 알아도된다.

주요 개념

시나리오 : 봇 안에서 사용자가 경험할 수 있는 서비스 단위, 

	기본시나리오
	- 웰컴 블록 : 봇이 사용자를 처음 만날때 발송하는 웰컴메세지를 정의함, 최초 1회만 작동
	- 폴백 블록 : 봇이 사용자의 발화 의도를 이해하지 못할때 내뱉는 메세지를 정의
	- 탈출 블록 : 봇의 되묻기 상황등에서 사용자가 대화를 초기화하거나 탈출하고 싶을때 쓰는 사용자 명령어를 정의( **사용자의 발화를 우리가 만든 챗봇 엔진에서 처리하기 때문에 사용하지 않음)
	
블록 : 사용자 의도(인텐트)를 처리하는 가장 작은 단위, 이 블록들이 모여서 하나의 시나리오로 구성된다.

스킬 : 외부에서 구현한 기능을 호출해 사용자에게 정보를 출력해주는 기능이다, 단어에서 유추할 수 있듯이 스킬을 통해 챗봇 기술을 추가할 수 있다.
        스킬은 블록의 출력 기능과 비슷하지만 응답 설정을 프로그램으로 처리할 수 있는 장점이 있다.
**즉, 외부에서 만든 기능을 카카오톡 챗봇에 맞게 응답을 출력할 수 있다.

어떻게 우리가 만든 챗봇 엔진을 카카오톡 챗봇에 연결할 수 있을까?
: 공식적으로 오픈빌더는 외부 챗봇 엔진을 지원하지 않는다, 하지만 사용자 발화 의도를 이해하지 못했을 때 대응할 수 있는 폴백 블록을 지원한다.
폴백 블록은 사용자 발화를 처리할 수 있는 시나리오 블록이 더 이상 없을때 실행된다. 보통 학습되지 않은 발화가 입력될 때 폴백블록으로 대응한다.
**우리는 모든 사용자 발화를 폴백 블록에서 처리하도록 기본 블록 이외에 다른 시나리오 블록은 생성하지 않는다.
그다음 폴백 블록에서 챗봇 API 서버와 통신할 수 있는 스킬을 지정한다면 외부 챗봇 엔진을 사용할 수 있다. 즉, 챗봇 API 서버를 스킬 서버로 사용한다.

만약 다양한 시나리오 블록이 존재한다면 발화 주제에 따라 카카오 챗봇 엔진과 우리가 제작한 챗봇엔진을 같이 사용할 수 있다.
즉 카카오 챗봇 엔진에서 폴백 처리되는 발화를 우리가 제작한 챗봇 엔진에서 처리하는 구조이다. 이런 구조를 응용하면 학습 데이터 관리에 용이한 카카오톡 챗봇을 개발할 수 있다.

스킬을 사용하기 위해서는 스킬 서버가 필요하다.
스킬 서버는 카카오 봇 시스템에서 받은 요청을 처리해 적절한 응답을 반환한다.
각각의 요청은 HTTP POST 메서드를 통해 전달되고 JSON형태로 데이터를 주고받습니다.

### Spring을 통해 개발
스프링 기본 개념
Spring Boot 애플리케이션에서는 보통 다음과 같은 패턴을 따릅니다:

Controller (컨트롤러):

HTTP 요청을 받아들이고 응답을 생성하는 역할을 합니다.
클라이언트로부터의 요청을 해당하는 서비스나 비즈니스 로직으로 라우팅합니다.
Service (서비스):

비즈니스 로직을 수행합니다.
여러 컨트롤러에서 공유되는 로직을 추상화하고, 리포지토리를 활용하여 데이터를 조작하거나 다른 작업을 수행합니다.
Repository (리포지토리):

데이터베이스와의 상호작용을 처리합니다.
데이터를 저장, 검색, 수정, 삭제하는 역할을 수행합니다.
그리고 Spring Boot 애플리케이션의 진입점은 주로 main 메소드가 있는 클래스입니다. 이 클래스는 스프링 컨텍스트를 초기화하고 관리하며, 여기서 @SpringBootApplication 어노테이션을 통해 컴포넌트 스캔, 설정 등을 설정합니다. 따라서 main 메소드를 실행함으로써 Spring Boot 애플리케이션이 구동됩니다.

컨트롤러, 서비스, 리포지토리는 @Controller, @Service, @Repository 어노테이션을 사용하여 Spring에게 해당 클래스가 어떤 역할을 하는지 알려주고, Spring이 이들을 관리하도록 합니다. 이렇게 스프링은 컴포넌트들을 스캔하고 애플리케이션을 실행시켜 컨트롤러를 통해 클라이언트 요청을 처리하며, 서비스와 리포지토리를 활용하여 비즈니스 로직과 데이터베이스 조작을 수행합니다.

build.gradle로 의존성을 추가합니다.

application.properties로 서버 및 데이터베이스 정보를 입력합니다.

### 스프링으로 카카오톡 채팅창에 버튼만들기

스프링(Spring)을 사용하여 카카오톡 채팅창에 버튼을 띄우기 위해서는 카카오톡 플러스친구 API를 호출하여 메시지를 보내야 합니다.

<dependencies>
    <!-- 다른 의존성들 -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.8</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
KakaoChatbotController.java:
java
Copy code
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@RestController
public class KakaoChatbotController {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody String userId) {
        String sendMessageUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        String messageJson = generateMessageJson(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + kakaoApiKey);

        HttpEntity<String> request = new HttpEntity<>(messageJson, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(sendMessageUrl, request, String.class);
        return response;
    }

    private String generateMessageJson(String userId) {
        // 메시지 JSON 생성 및 버튼 정보 설정
        String messageJson = "{"
            + "\"object_type\": \"text\","
            + "\"text\": \"버튼을 눌러주세요!\","
            + "\"link\": {"
                + "\"web_url\": \"https://www.example.com\""
            + "},"
            + "\"buttons\": ["
                + "{"
                    + "\"label\": \"버튼 1\","
                    + "\"action\": \"webLink\","
                    + "\"webLinkUrl\": \"https://www.example.com/button1\""
                + "},"
                + "{"
                    + "\"label\": \"버튼 2\","
                    + "\"action\": \"webLink\","
                    + "\"webLinkUrl\": \"https://www.example.com/button2\""
                + "}"
            + "]"
        + "}";

        return messageJson;
    }
}

위 코드는 스프링 부트에서 작동하는 간단한 REST 컨트롤러 예제입니다. 요청이 들어오면 해당 사용자에게 버튼이 포함된 메시지를 보내는 역할을 합니다.

위 코드에서 ${kakao.api.key} 부분은 카카오톡 API 키가 저장되어 있는 환경 변수나 설정 파일에서 값을 가져오는 것을 가정하고 있습니다.


