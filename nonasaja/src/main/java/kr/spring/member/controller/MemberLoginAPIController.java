package kr.spring.member.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.KakaoProfile;
import kr.spring.member.vo.MemberVO;
import kr.spring.member.vo.NaverProfile;
import kr.spring.member.vo.OAuthToken;

@Controller
public class MemberLoginAPIController {
	private static final Logger logger = LoggerFactory.getLogger(MemberLoginAPIController.class);
	
	@Autowired
	private MemberService memberService;
	
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//=================네이버=====================//
	//네이버 인가코드,액세스 토큰 받아서 회원정보 불러오기
	@GetMapping("/auth/naver/callback") // /member/naverLogin.do /auth/kakao/callback
	public @ResponseBody ResponseEntity<?> naverConfirm(String code, HttpSession session) {
		// code값을 받으면 인증 완료! code값을 이용해 accessToken을 받는다.
		// accessToken : 현재 로그인한 사람의 개인정보에 접근하기 위한 토큰

		// POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// HttpsURLConnection url = new HttpsConnection()과 같음
		// Retrofit2(안드로이드), OKHttp, RestTemplate

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "p5qDyh8lEAltU8wkoV_r");
		params.add("client_secret", "UgKq6J74t8");
		params.add("redirect_uri", "http://localhost:8080/auth/naver/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST,
														naverTokenRequest, String.class);
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
//		System.out.println("header : "+headers);
//		System.out.println("response"+response);
//		System.out.println("params : "+params);
//		System.out.println("tokenRequest : "+naverTokenRequest);
//		System.out.println("네이버 액세스 토큰 : " + oauthToken.getAccess_token());

		// 사용자 정보 조회
		RestTemplate rt2 = new RestTemplate();
		 
		// HttpHeader 오브젝트 생성 
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		//headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기 
		HttpEntity<MultiValueMap<String,String>> naverProfileRequest = new HttpEntity<>(headers2);
		
		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음. 
		ResponseEntity<String> response2 = rt2.exchange("https://openapi.naver.com/v1/nid/me",
														HttpMethod.POST, naverProfileRequest, String.class); 
		ObjectMapper objectMapper2 = new ObjectMapper(); 
		
		NaverProfile naverProfile = null;
		
		try { 
			naverProfile = objectMapper2.readValue(response2.getBody(), NaverProfile.class); 
		} catch (JsonMappingException e) { 
			e.printStackTrace();
		} catch (JsonProcessingException e) { 
			e.printStackTrace(); 
		} 
		// 네이버에서 가져올 수 있는 정보 : id,name,nickname,email,phone 
		// 가져올 수 없는 정보 :passwd,zipcode,addr1,2,interest 
		//기존 회원인지 새로 가입하는 회원인지 체크
		MemberVO member = memberService.selectCheckMember(naverProfile.getResponse().getId());
		if(member==null) {
			logger.debug("<네이버 기존회원 아님, 회원가입 처리.....>");
			//카카오 리소스 서버로부터 id, nickname, email 값 추출
			String id = naverProfile.getResponse().getId();
			String name = naverProfile.getResponse().getName();
			String nickname = naverProfile.getResponse().getNickname();
			String email = naverProfile.getResponse().getEmail();
			String phone = naverProfile.getResponse().getMobile();
			
			//GET 방식으로 회원가입 폼으로 전송
			headers2.setLocation(URI.create("/member/memberNaverLogin.do?id="+id+"&nickname="+nickname+"&email="+email+"&name="+name+"&phone="+phone));
			return new ResponseEntity<>(headers2, HttpStatus.MOVED_PERMANENTLY);
		}else {
			logger.debug("<네이버 기존 회원, 로그인 처리.....> 정보 : " + member);
			session.setAttribute("user", member);
			//로그인 후 메인페이지로 이동
			HttpHeaders headers3 = new HttpHeaders();
			headers3.setLocation(URI.create("/"));
			return new ResponseEntity<>(headers3, HttpStatus.MOVED_PERMANENTLY);
		}
	}
	
	//네이버가 보내온 정보가 세팅된 폼 호출
	@GetMapping("/member/memberNaverLogin.do")
	public String naverForm(HttpServletRequest request, Model model) {
		model.addAttribute("naverid", request.getParameter("id"));
		model.addAttribute("navername", request.getParameter("name"));
		model.addAttribute("navernick", request.getParameter("nickname"));
		model.addAttribute("naveremail", request.getParameter("email"));
		model.addAttribute("naverphone", request.getParameter("phone"));
		logger.debug("<네이버가 보낸 정보.....> : "+ model);
		return "memberNaverLogin";
	}
	//네이버 연동 회원가입 완료
	@PostMapping("/member/registerNaverUser.do")
	public String naverSubmit(MemberVO memberVO, Model model) {
		logger.debug("<회원가입> : " + memberVO);
		// 회원가입
		memberService.insertNaverMember(memberVO);
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		return "common/notice";
	}
	
	//===============카카오================//
	@Value("${secret.key}")
	private String key;

	//=======카카오=======//
	//카카오 인가코드,액세스 토큰 받아서 회원정보 불러오기
	@GetMapping("/auth/kakao/callback")
	// @ResponseBody = Data를 리턴해주는 컨트롤러 함수 ResponseEntity<?>
	public @ResponseBody ResponseEntity<?> kakaoCallback(String code, HttpServletRequest request, HttpSession session) {
		logger.debug("<카카오 연동 진입>");
		// code값을 받으면 인증 완료! code값을 이용해 accessToken을 받는다.
		// accessToken : 현재 로그인한 사람의 개인정보에 접근하기 위한 토큰
		// POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// HttpsURLConnection url = new HttpsConnection()과 같음
		// Retrofit2(안드로이드), OKHttp, RestTemplate
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성 : 헤더값 세팅
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성 : 바디 값 세팅
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "a8abdc39c132bcec49dcef03bb7a10d1");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);
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

		//System.out.println("카카오 액세스 토큰 : " + oauthToken.getAccess_token());

		// 사용자 정보 조회
		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest, String.class);
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// 카카오에서 가져올 수 있는 정보 : id,nickname,email
		// 가져올 수 없는 정보 : passwd,name,phone,zipcode,addr1,2,interest
		//기존 회원인지 새로 가입하는 회원인지 체크
		MemberVO member = memberService.selectCheckMember(kakaoProfile.getId().toString());
		if(member==null) {
			logger.debug("<카카오 기존회원 아님, 회원가입 처리.....>");
			//카카오 리소스 서버로부터 id, nickname, email 값 추출
			String id = kakaoProfile.getId().toString();
			String nickname = kakaoProfile.getProperties().getNickname();
			String email = kakaoProfile.getKakao_account().getEmail();
			
			//GET 방식으로 회원가입 폼으로 전송
			headers2.setLocation(URI.create("/member/memberKakaoLogin.do?id="+id+"&nickname="+nickname+"&email="+email));
			return new ResponseEntity<>(headers2, HttpStatus.MOVED_PERMANENTLY);
		}else {
			logger.debug("<카카오 기존 회원, 로그인 처리.....> 정보 : " + member);
			session.setAttribute("user", member);
			//로그인 후 메인페이지로 이동
			HttpHeaders headers3 = new HttpHeaders();
			headers3.setLocation(URI.create("/"));
			return new ResponseEntity<>(headers3, HttpStatus.MOVED_PERMANENTLY);
		}

	}
	
	@GetMapping("/member/memberKakaoLogin.do")
	public String kakaoForm(HttpServletRequest request, Model model) {
		model.addAttribute("kakaoid", request.getParameter("id"));
		model.addAttribute("kakaonick", request.getParameter("nickname"));
		model.addAttribute("kakaoemail", request.getParameter("email"));
		logger.debug("<카카오가 보낸 정보.....> : "+ model);
		return "memberKakaoLogin";
	}
	
	@PostMapping("/member/registerKakaoUser.do")
	public String kakaoSubmit(MemberVO memberVO, Model model) {
		logger.debug("<회원가입> : " + memberVO);
		
		// 회원가입
		memberService.insertKakaoMember(memberVO);
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		return "common/notice";
	}

}
