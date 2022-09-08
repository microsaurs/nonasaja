package kr.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 자바빈(VO) 초기화, form 커스텀 태그 사용
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	// ===========회원가입=============//
	// 회원가입 - 폼
	@GetMapping("/member/registerUser.do")
	public String form() {
		logger.debug("<회원가입 시도>");
		return "memberRegister";
	}

	// 회원가입 - 데이터 처리
	@PostMapping("/member/registerUser.do")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model) {
		logger.debug("<회원가입> : " + memberVO);

		// 유효성 체크
		if (result.hasErrors()) {
			return form();
		}
		// 회원가입
		memberService.insertMember(memberVO);
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		return "common/notice";
	}

	// =============네이버===============//
	// 네이버 최초 회원가입 - 추가 정보 저장
	@PostMapping("/member/naverLogin.do")
	public String submitNaver(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {

		logger.debug("<네이버 회원 최초 가입>");
		logger.debug("<회원 값 세팅> : " + memberVO);
		// 유효성 체크
		// 네이버 연동으로 id,name,nickname,email,phone 정보는 세팅되어있어서
		// 우편번호,주소,상세주소만 유효성 체크
		if (result.hasFieldErrors("zipcode") || result.hasFieldErrors("addr1") || result.hasFieldErrors("addr2")) {
			return "memberNaverLogin";
		}

		// DB에 값 저장 (회원가입 처리)
		memberService.insertNaverMember(memberVO);

		// 로그인
		session.setAttribute("user", memberVO);
		return "redirect:/main/main.do";
	}

	// 로그인 - 폼
	@GetMapping("/member/login.do")
	public String formLogin() {
		return "memberLogin";
	}

	// 로그인 - 데이터 처리
	@PostMapping("/member/login.do")
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<회원 로그인> : " + memberVO);

		// 유효성 체크 결과 오류있으면 폼 호출
		// id,pw만 체크
		if (result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		// 로그인 체크
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(memberVO.getId());

			boolean check = false;
			if (member != null) {
				// 비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}

			if (check) {
				// 인증 성공, 로그인 처리
				session.setAttribute("user", member);
				logger.debug("<인증 성공>");
				logger.debug("<id> : " + member.getId());

				return "redirect:/main/main.do";
			}
			// 인증 실패 : check=false
			throw new AuthCheckException();
		} catch (AuthCheckException e) {
			// 인증 실패
			if (member != null && member.getAuth() == 1) {
				// 정지회원
				result.reject("noAuthority");
			} else {
				// id 혹은 pw 불일치
				result.reject("invalidIdOrPassword");
			}
			logger.debug("<인증 실패>");

			return formLogin();
		}

	}

	// 로그아웃
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main/main.do";
	}

	// 마이페이지
	@RequestMapping("/member/myPage.do")
	public String myPage(HttpSession session, Model model) {
		// 로그인한 회원 정보 읽기
		MemberVO user = (MemberVO) session.getAttribute("user");
		// 상세 정보 조회
		MemberVO member = memberService.selectMember(user.getMem_num());
		logger.debug("<회원상세정보> : " + member);
		model.addAttribute("member", member);

		return "memberView";
	}

	// 회원정보 수정 - 폼
	@GetMapping("/member/update.do")
	public String formUpdate(HttpSession session, Model model) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		model.addAttribute("memberVO", memberVO);
		logger.debug("<회원정보수정 접속> : " + memberVO);

		return "memberModify";
	}

	// 회원정보 수정 - 데이터 처리
	@PostMapping("/member/update.do")
	public String submitUpdate(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<회원정보수정 전> : " + memberVO);

		// 유효성 체크
		if (result.hasErrors()) {
			return "memberModify";
		}

		try {
			MemberVO user = (MemberVO) session.getAttribute("user");
			memberVO.setMem_num(user.getMem_num());
			memberVO.setAuth(user.getAuth());

			boolean check = false;
			if (memberVO != null) {
				// 비밀번호 일치 여부 체크
				check = memberVO.isCheckedPasswd(user.getPasswd());
			}
			if (check) {
				// 회원정보 수정
				memberService.updateMember(memberVO);
				return "redirect:/member/myPage.do";
			}
			throw new AuthCheckException();
		} catch (AuthCheckException e) {
			result.reject("invalidPassword");
			logger.debug("<인증실패>");
			return "memberModify";
		}
	}

	// 프로필 사진 출력(로그인 전용)
	@RequestMapping("/member/photoView.do")
	public ModelAndView getProfile(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		MemberVO user = (MemberVO) session.getAttribute("user");
		if (user == null) {
			// 로그인 안된 경우
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			mav.addObject("imageFile", readbyte);
			mav.addObject("filename", "face.png");
			mav.setViewName("imageView");
		} else {
			// 로그인 된 경우
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO, request, mav);
		}

		return mav;
	}

	// 프로필 사진 출력(회원번호 지정)
	@RequestMapping("/member/viewProfile.do")
	public ModelAndView getProfileByMem_num(@RequestParam int mem_num, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		MemberVO memberVO = memberService.selectMember(mem_num);

		viewProfile(memberVO, request, mav);

		return mav;
	}

	// 프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, ModelAndView mav) {
		if (memberVO.getPhoto_name() == null) {
			// 업로드한 프로필 사진이 없는 경우 /webapp -절대경로
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			mav.addObject("imageFile", readbyte);
			mav.addObject("filename", "face.png");
		} else {
			// 업로드한 프로필 사진이 있는 경우
			mav.addObject("imageFile", memberVO.getPhoto());
			mav.addObject("filename", memberVO.getPhoto_name());
		}
		// 뷰 이름 지정
		mav.setViewName("imageView");
	}
}
