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
import org.springframework.web.bind.annotation.RequestMapping;

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
		
//		String inter = memberVO.getInterest();
//		String[] arr = inter.split(",");
//		model.addAttribute("arr", arr);
		
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
}
