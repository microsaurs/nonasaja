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

	private int rowCount = 20;
	private int pageCount = 10;
	
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
	public String submitUpdate(MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<회원정보수정 전> : " + memberVO);

		MemberVO user = (MemberVO) session.getAttribute("user");
		
		memberVO.setMem_num(user.getMem_num());
		memberVO.setAuth(user.getAuth());
		
		logger.debug("<memberVO>..."+memberVO);

		memberService.updateMember(memberVO);
		
		session.setAttribute("user", memberVO);
		
		return "redirect:/member/myPage.do";
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
	
	//===========마이페이지 - 중고거래=============//
	@RequestMapping("/member/myPageProduct.do")
	public ModelAndView listProductPage(HttpSession session, 
			@RequestParam(value="pageNum",defaultValue = "1")int currentPage) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPageProduct");
		mav.addObject("member", user);
		
		return mav;
	}
	//===========마이페이지 - 중고거래=============//
	@RequestMapping("/member/myPageUsed.do")
	public ModelAndView listUsedPage(HttpSession session, 
			@RequestParam(value="pageNum",defaultValue = "1")int currentPage) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPageUsed");
		mav.addObject("member", user);
		
		return mav;
	}
	//===========마이페이지 - 동호회=============//
	@RequestMapping("/member/myPageClub.do")
	public ModelAndView listClubPage(HttpSession session, 
			@RequestParam(value="pageNum",defaultValue = "1")int currentPage) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPageClub");
		mav.addObject("member", user);
		
		return mav;
	}
	//===========마이페이지 - 커뮤니티=============//
	@RequestMapping("/member/myPageCommu.do")
	public ModelAndView listCommuPage(HttpSession session, 
			@RequestParam(value="pageNum",defaultValue="1")int currentPage,
			@RequestParam(value="type",defaultValue="1") int type) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPageCommu");
		mav.addObject("member", user);
		
		return mav;
	}
		
	
	//=========비밀번호 변경===========//
	//비밀번호 변경 폼
	@GetMapping("/member/changePassword.do")
	public String formChangePassword() {
		return "memberChangePassword";
	}
	//비밀번호 변경 전송된 데이터 처리
	@PostMapping("/member/changePassword.do")
	public String submitChangePassword(@Valid MemberVO memberVO, BindingResult result, HttpSession session, 
										Model model, HttpServletRequest request) {
		logger.debug("<<비밀번호 변경 처리>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//now_passwd, passwd만 체크
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd")) {
			return formChangePassword();
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		//비밀번호 일치 여부 체크를 위해서 회원정보 호출
		MemberVO db_member = memberService.selectMember(memberVO.getMem_num());
		//폼에서 전송한 현재 비밀번호와 디비에서 받아온 현재 비밀번호 일치 여부 체크
		if(!db_member.getPasswd().equals(memberVO.getNow_passwd())) {
			result.rejectValue("now_passwd", "invalidPassword");
			return formChangePassword();
		}
		
		//비밀번호 변경
		memberService.updatePassword(memberVO);
		
		//비밀번호 변경 성공 메시지를 scrpit로 보여지게 하기
		//model, request 매개변수로 선언
		//View에 표시할 메세지
		model.addAttribute("message", "비밀번호 변경 완료!");
		model.addAttribute("url", request.getContextPath()+"/member/myPage.do");
		
		return "common/resultView";
	}
	
	//==========회원 탈퇴===========//
	//탈퇴 폼
	@GetMapping("/member/delete.do")
	public String formDelete() {
		return "memberDelete";
	}
	//탈퇴 폼에서 전달된 데이터 처리
	@PostMapping("/member/delete.do")
	public String submitDelete(@Valid MemberVO memberVO, BindingResult result, HttpSession session, Model model) {
		logger.debug("<<회원탈퇴>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//id, passwd 필드의 에러만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formDelete();
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		try{
			//로그인 한 회원의 아이디 구하기
			MemberVO db_member = memberService.selectMember(user.getMem_num());
			boolean check = false;
			//검증
			//로그인 한 회원 아이디와 입력한 아이디 대조
			if(db_member!=null && db_member.getId().equals(memberVO.getId())) {
				//비밀번호 일치 여부 체크
				check = db_member.isCheckedPasswd(memberVO.getPasswd());
			}
			
			if(check) {
				//인증성공, 회원정보 삭제
				memberService.deleteMember(memberVO.getMem_num());
				
				//로그아웃
				session.invalidate();
				
				model.addAttribute("accessMsg","회원탈퇴를 완료했습니다.");
				//notice.jsp의 기본 이동이 /main/main.do로 설정 되어 있어서 그대로 사용
				return "common/notice";
			}
			//인증 실패
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			result.reject("invalidIdOrPassword");
			return formDelete();
		}
		
	}
	
}
