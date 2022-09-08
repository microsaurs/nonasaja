package kr.spring.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.member.service.LionPointService;
import kr.spring.member.vo.OAuthToken;

@Controller
public class LionPointController {
	private static final Logger logger = LoggerFactory.getLogger(LionPointController.class);
	
	@Autowired
	private LionPointService lionPointService;
	
	@RequestMapping("/point/kakaopay")
	public String serviceSubmit() {
		logger.debug("<카카오페이 진입..........>");
		
		RestTemplate rt = new RestTemplate();
		// HttpHeader 오브젝트 생성 : 헤더값 세팅
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Authorization", "KakaoAK 4827aa887dd23fd8be0f3158d9eb8bad");
		
		// HttpBody 오브젝트 생성 : 바디 값 세팅
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		//cid : 가맹점 코드, 10자 String
		params.add("cid", "NONASAJA");
		//partner_order_id : 가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합 String
		params.add("partner_order_id", "n1000");
		//partner_user_id : 가맹점 주문번호, 최대 100자 String
		params.add("partner_user_id", "u1000");
		//상품명, 최대 100자 String
		params.add("item_name", "포인트충전");
		//상품 수량 Integer
		params.add("quantity", "1");
		//상품 총액 Integer
		params.add("total_amount", "5000");
		//상품 비과세 금액 Integer
		params.add("tax_free_amount", "5000");
		//결제 성공 시 redirect url, 최대 255자 String
		params.add("approval_url", "http://localhost:8080/common/notice.do?accessMsg=결제성공&accessUrl=http://localhost:8080/member/myPage");
		//결제 취소 시 redirect url, 최대 255자 String
		params.add("cancel_url", "http://localhost:8080/common/notice.do?accessMsg=결제취소&accessUrl=http://localhost:8080/member/myPage");
		//결제 실패 시 redirect url, 최대 255자 String
		params.add("fail_url", "http://localhost:8080/common/notice.do?accessMsg=결제실패&accessUrl=http://localhost:8080/member/myPage");
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> payRequest = new HttpEntity<>(params, headers);
		
		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kapi.kakao.com/v1/payment/ready",
				HttpMethod.POST,
				payRequest, String.class);
		
		// Gson, Json Simple, ObjectMapper 라이브러리 중 택일 사용
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;

		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return response.toString();
	}
	
}
