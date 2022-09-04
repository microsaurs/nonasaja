package kr.spring.member.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.KakaoProfile;
import kr.spring.member.vo.MemberVO;
import kr.spring.member.vo.OAuthToken;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//자바빈(VO) 초기화, form 커스텀 태그 사용
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//===========회원가입=============//
	//회원가입 - 폼
	@GetMapping("/member/registerUser.do")
	public String form() {
		logger.debug("<회원가입 시도>");
		return "memberRegister";
	}
	
	//회원가입 - 데이터 처리
	@PostMapping("/member/registerUser.do")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model) {
		logger.debug("<회원가입> : " + memberVO);
		
		//유효성 체크
		if(result.hasErrors()) {
			return form();
		}
		//회원가입
		memberService.insertMember(memberVO);
		model.addAttribute("accessMsg","회원가입이 완료되었습니다.");
		return "common/notice";
	}
	
	//=============네이버===============//
	@GetMapping("/member/naverCheck.do")
	public String naverCheck() {
		return "member/naverCheck";
	}
	//네이버 로그인 or 최초 가입시 추가 정보 기입
	@GetMapping("/member/naverLogin.do")
	public String naverConfirm(String id, HttpSession session) {
		logger.debug("<네이버 로그인 진입>");
		//DB에 저장된 id값 있는지 확인
		//MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO db_member = memberService.selectCheckMember(id);
		if(db_member!=null) {
			//로그인
			session.setAttribute("user", db_member);
			return "redirect:/main/main.do";
		}
		return "memberNaverLogin";
	}
	//네이버 최초 회원가입 - 추가 정보 저장
	@PostMapping("/member/naverLogin.do")
	public String submitNaver(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		logger.debug("<네이버 회원 최초 가입>");
		logger.debug("<회원 값 세팅> : " + memberVO);
		//유효성 체크
		//네이버 연동으로 id,name,nickname,email,phone 정보는 세팅되어있어서
		//우편번호,주소,상세주소만 유효성 체크
		if(result.hasFieldErrors("zipcode") || result.hasFieldErrors("addr1") || result.hasFieldErrors("addr2")) {
			return "memberNaverLogin";
		}
		
		//DB에 값 저장 (회원가입 처리)
		memberService.insertNaverMember(memberVO);
		
		//로그인
		session.setAttribute("user", memberVO);
		return "redirect:/main/main.do";
	}
	@Value("${secret.key}")
	private String key;
	//=======카카오=======//
	@GetMapping("/auth/kakao/callback")
	//@ResponseBody = Data를 리턴해주는 컨트롤러 함수
	public @ResponseBody String kakaoCallback(String code) {
		//code값을 받으면 인증 완료! code값을 이용해 accessToken을 받는다.
		//accessToken : 현재 로그인한 사람의 개인정보에 접근하기 위한 토큰
		
		//POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
		//HttpsURLConnection url = new HttpsConnection()과 같음
		//Retrofit2(안드로이드), OKHttp, RestTemplate
		
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "a8abdc39c132bcec49dcef03bb7a10d1");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers); 
		
		//Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",
														HttpMethod.POST,
														kakaoTokenRequest,
														String.class
														);
		//Gson, Json Simple, ObjectMapper 라이브러리 중 택일 사용
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e){
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 액세스 토큰 : "+oauthToken.getAccess_token());
		
		//사용자 정보 조회
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity<>(headers2); 
		
		//Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me",
														HttpMethod.POST,
														kakaoProfileRequest,
														String.class
														);
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e){
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//member 오브젝트 :id,passwd,nickname,name,phone,email,zipcode,addr1,2,interest
		//카카오에서 가져올 수 있는 정보 : id,nickname,email
		//가져올 수 없는 정보 : passwd,name,phone,zipcode,addr1,2,interest
		System.out.println("카카오 아이디(번호):"+kakaoProfile.getId());
		System.out.println("카카오 이메일:"+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("카카오 유저 아이디 :" + kakaoProfile.getId());
		System.out.println("카카오 유저 닉네임 :" + kakaoProfile.getProperties().getNickname());
		System.out.println("카카오 유저 이메일 :" + kakaoProfile.getKakao_account().getEmail());
		//카카오,네이버 연동 계정 유저는 비밀번호를 설정할 수 없는데
		//그 대안으로 yml 파일 맨 아래에 값을 지정해 연동회원인 경우 모두 같은 값을 넣는 방법을 썼다
		//yml 맨 하단 참조 -> 현재 메서드 상단 @Value 참조
		//member.setPasswd(key);
		//실제 서비스시 key 값은 절대! 노출되면 안됨!!!!
		//연동회원이 비밀번호변경 불가능하게 해야함
		//연동 포털 구분 가능하게 컬럼 추가
		
		//UUID란 - 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
		//비밀번호 분실시 임시 비밀번호로 활용 가능
		//UUID garbagePassword = UUID.randomUUID();
		//위 garbagePassword를 값으로 넣을때 .toString() 해준다
		
		//멤버에 값 대입하고 sql 실행
		/*
		 * MemberVO memberVO = new MemberVO();
		 * memberVO.setId(kakaoProfile.getId().toString());
		 * memberVO.setNickname(kakaoProfile.getProperties().getNickname());
		 * memberVO.setEmail(kakaoProfile.getKakao_account().getEmail());
		 * memberService.insertMember(memberVO);
		 */
		
		//카카오 계정으로 이미 가입한 사람인지 첫 가입인지 분기
		
		
		return response2.getBody();
	}
	//로그인 - 폼
	@GetMapping("/member/login.do")
	public String formLogin() {
		return "memberLogin";
	}
	//로그인 - 데이터 처리
	@PostMapping("/member/login.do")
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<회원 로그인> : " + memberVO);
		
		//유효성 체크 결과 오류있으면 폼 호출
		//id,pw만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		//로그인 체크
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(memberVO.getId());
			
			boolean check = false;
			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}
			
			if(check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				logger.debug("<인증 성공>");
				logger.debug("<id> : " + member.getId());
				
				return "redirect:/main/main.do";
			}
			//인증 실패 : check=false
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			//인증 실패
			if(member!=null && member.getAuth()==1) {
				//정지회원
				result.reject("noAuthority");
			}else {
				//id 혹은 pw 불일치
				result.reject("invalidIdOrPassword");
			}
			logger.debug("<인증 실패>");
			
			return formLogin();
		}
		
	}
	//로그아웃
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/main.do";
	}
	
	//마이페이지
	@RequestMapping("/member/myPage.do")
	public String myPage(HttpSession session, Model model) {
		//로그인한 회원 정보 읽기
		MemberVO user = (MemberVO)session.getAttribute("user");
		//상세 정보 조회
		MemberVO member = memberService.selectMember(user.getMem_num());
		logger.debug("<회원상세정보> : " + member);
		model.addAttribute("member", member);
		
		return "memberView";
	}
	//회원정보 수정 - 폼
	@GetMapping("/member/update.do")
	public String formUpdate(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		model.addAttribute("memberVO", memberVO);
		logger.debug("<회원정보수정 접속> : " + memberVO);
		
		return "memberModify";
	}
	//회원정보 수정 - 데이터 처리
	@PostMapping("/member/update.do")
	public String submitUpdate(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<회원정보수정 전> : " + memberVO);

		//유효성 체크
		if(result.hasErrors()) {
			return "memberModify";
		}
		
		try {
			MemberVO user = (MemberVO)session.getAttribute("user");
			memberVO.setMem_num(user.getMem_num());
			memberVO.setAuth(user.getAuth());
			
			boolean check = false;
			if(memberVO!=null) {
				//비밀번호 일치 여부 체크
				check = memberVO.isCheckedPasswd(user.getPasswd());
			}
			if(check) {
				//회원정보 수정
				memberService.updateMember(memberVO);
				return "redirect:/member/myPage.do";
			}
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			result.reject("invalidPassword");
			logger.debug("<인증실패>");
			return "memberModify";
		}
	}
	
	//프로필 사진 출력(로그인 전용)
	@RequestMapping("/member/photoView.do")
	public ModelAndView getProfile(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			//로그인 안된 경우
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			mav.addObject("imageFile", readbyte);
			mav.addObject("filename", "face.png");
			mav.setViewName("imageView");
		}else {
			//로그인 된 경우
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO,request,mav);
		}
		
		return mav;
	}
	//프로필 사진 출력(회원번호 지정)
	@RequestMapping("/member/viewProfile.do")
	public ModelAndView getProfileByMem_num(@RequestParam int mem_num, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		viewProfile(memberVO,request,mav);
		
		return mav;
	}
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, ModelAndView mav) {
		if(memberVO.getPhoto_name()==null) {
			//업로드한 프로필 사진이 없는 경우										/webapp -절대경로
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			mav.addObject("imageFile", readbyte);
			mav.addObject("filename", "face.png");
		}else {
			//업로드한 프로필 사진이 있는 경우
			mav.addObject("imageFile", memberVO.getPhoto());
			mav.addObject("filename", memberVO.getPhoto_name());
		}
		//뷰 이름 지정
		mav.setViewName("imageView");
	}
}
