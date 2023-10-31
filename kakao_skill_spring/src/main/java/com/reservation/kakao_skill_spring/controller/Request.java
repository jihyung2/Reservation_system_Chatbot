package com.reservation.kakao_skill_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.reservation.kakao_skill_spring.entity.Database;
import com.reservation.kakao_skill_spring.service.DBService;
import com.reservation.kakao_skill_spring.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kakao")
public class Request {
    Map<String, String> usernum = new HashMap<>();
    Map<String, String> userinfo = new HashMap<>();
    @Autowired
    private DBService dbService;

    @PostMapping("/reservation")
    public Map<String, Object> rText(@RequestBody String request) throws JsonProcessingException {
        Map<String, Object> simpleText = new HashMap<>();

        Map<String, Object> simpleTextObject = new HashMap<>();
        simpleTextObject.put("simpleText", simpleText);

        List<Map<String, Object>> outputs = new ArrayList<>();
        outputs.add(simpleTextObject);

        Map<String, Object> template = new HashMap<>();
        template.put("outputs", outputs);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("version", "2.0");
        responseBody.put("template", template);
        Database data = new Database();

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(request);

            JsonNode userNode = jsonNode.path("userRequest");


            JsonNode useridNode = userNode.path("user").path("id");
            JsonNode usertext = userNode.path("utterance");

            // 필드 값을 문자열로 가져오기
            String userresponse = usertext.asText();
            String userid = useridNode.asText();

            System.out.println("userid: " + userid);
            System.out.println("usertext: " + userresponse);
            System.out.println(usernum.get(userid));


            if ( userresponse.equals("예약하기")){
                simpleText.put("text", "고객님의 번호를 입력해주세요.\n휴대폰 번호는 예약 물건을 찾아가실때 사용됩니다.\nex)01012345678 ");
                usernum.put(userid, "1");
            }

            else if (usernum.get(userid) == "1"){
                try {
                    int number = Integer.parseInt(userresponse);
                    System.out.println(userresponse);

                    usernum.put(userid, "2");
                    simpleText.put("text", "예약할 전 목록을 보내주세요!\n*만원 단위로 작성해주시면 됩니다. 김치전, 부추전은 예약하실 수 없습니다.\n ex) 동태전 3만원, 꼬지전 2만원, 동그랑땡 5만원 ");
                    // 필요한 경우 다른 필드들도 설정해주세요.
                    userinfo.put(userid, userresponse);

                }catch (Exception e){
                    simpleText.put("text", "숫자만 넣어주세요");
                    usernum.put(userid, "0");
                }
            }

            else if (usernum.get(userid) == "2"){
                try {
                    simpleText.put("text", "전 예약 완료!");
                    usernum.put(userid, "0");
                    data.setId(userid);
                    data.setNumber(userinfo.get(userid));
                    data.setText(userresponse);
                    dbService.saveDB(data);



                }catch (Exception e) {
                    e.printStackTrace();
                    simpleText.put("text", "전 주문 단계에서 에러가 발생했습니다. 에러 메시지: " + e.getMessage());
                }
            }

            else if (userresponse.equals("예약 수정 및 조회")) {
                try {
                    simpleText.put("text", "예약 수정을 원하실 경우 '수정', \n조회를 원하실경우 '조회'를 입력해주세요");
                    usernum.put(userid, "4");


                } catch (Exception e) {
                    simpleText.put("text", "수정 및 조회에서 에러가 발생했습니다.");
                    usernum.put(userid, "0");
                }
            }
            else if (usernum.get(userid) == "4" && userresponse.equals("수정")) {
                try {
                    simpleText.put("text", "수정할 번호를 입력해주세요 \nex)01012345678");
                    usernum.put(userid, "5");


                } catch (Exception e) {
                    simpleText.put("text", "수정중 에러 발생");

                }
            }
            else if (usernum.get(userid) == "5") {
                try {
                    int number = Integer.parseInt(userresponse);
                    System.out.println(userresponse);
                    simpleText.put("text", ""); // 수정부분 좀더 추가해야됨
                    usernum.put(userid, "0");


                } catch (Exception e) {
                    simpleText.put("text", "숫자만 넣어주세요");

                }
            }
            else if (usernum.get(userid) == "4" && userresponse.equals("조회")) {
                try {
                    simpleText.put("text", "조회할 번호를 입력해주세요 \nex)01012345678");
                    usernum.put(userid, "7");


                } catch (Exception e) {
                    simpleText.put("text", "조회중에 에러가 발생했습니다.");
                }
            }

            else if (usernum.get(userid) == "7") {
                try {
                    int number = Integer.parseInt(userresponse);
                    System.out.println(userresponse);

                    if( dbService.getSearchDB(userresponse).isEmpty()) {
                        simpleText.put("text", "조회 데이터가 없습니다.");
                    }
                    else {

                        simpleText.put("text", dbService.getSearchDB(userresponse).toString());
                        usernum.put(userid, "0");
                    }

                } catch (Exception e) {
                    simpleText.put("text", "숫자만 입력해주세요");

                }
            }

            else {
                simpleText.put("text", "");
            }
            return responseBody;


        }catch (Exception e) {
            simpleText.put("text", "에러가 발생했습니다");

            return responseBody;

        }
    }
}

