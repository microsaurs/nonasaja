package kr.spring.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

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
	
	//회원가입
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
}
