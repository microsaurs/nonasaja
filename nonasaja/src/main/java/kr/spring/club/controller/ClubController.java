package kr.spring.club.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import kr.spring.club.service.ClubService;
import kr.spring.club.vo.ClubVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class ClubController {
	private static final Logger Logger = LoggerFactory.getLogger(ClubController.class);
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Autowired
	private ClubService clubService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public ClubVO initCommand() {
		return new ClubVO();
	}
	
	//===========게시판 글 등록============//
	//등록 폼
	@GetMapping("/clubboard/write.do")
	public String form() {
		return "clubboardWrite";
	}
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/clubboard/write.do")
	public String submit(@Valid ClubVO clubVO,
			      BindingResult result,
			      HttpServletRequest request,
			      HttpSession session,
			      Model model) {
		
		Logger.debug("<<게시판 글 저장>> : " + clubVO);
		
		
		 //유효성 검사 결과 오류가 있으면 폼 호출 
		if(result.hasErrors()) { return form(); }
		 
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원번호 셋팅
		clubVO.setClub_leader(user.getMem_num());
		
		//글쓰기
		clubService.insertBoard(clubVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/clubboard/list.do");
		
		return "common/resultView";
	}
	
	//===========게시판 글 목록============//
	@RequestMapping("/clubboard/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") 
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		
		//글의 총개수(검색된 글의 개수)
		int count = clubService.selectRowCount(map);
		
		Logger.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = 
				new PagingUtil(currentPage,count,
						rowCount,pageCount,"list.do","&keyfield="+keyfield);
		
		List<ClubVO> list = null;
		if(count > 0) {
			
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = clubService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("clubboardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		 
		return mav;
	}
	
	//========게시판 글상세===========//
	@RequestMapping("/clubboard/detail.do")
	public ModelAndView detail(
			          @RequestParam int club_num) {
		
		Logger.debug("<<board_num>> : " + club_num);
		
		//해당 글의 조회수 증가
		clubService.updateHit(club_num);
		
		ClubVO board = 
				clubService.selectBoard(club_num);
		
		//제목에 태그를 허용하지 않음
		board.setClub_title(
			 StringUtil.useNoHtml(board.getClub_title()));
		//내용에 줄바꿈 처리하면서 태그를 허용하지 않음
		//ckeditor 사용시 아래 코드 주석 처리
		//board.setClub_content(StringUtil.useBrNoHtml(board.getClub_content()));
		
		                          //뷰 이름    속성명   속성값
		return new ModelAndView("clubboardView","board", board);
	}
	
	//===========파일다운로드===========//
	@RequestMapping("/clubboard/file.do")
	public ModelAndView download(
			   @RequestParam int club_num) {
		
		ClubVO board = 
				clubService.selectBoard(club_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("club_img", 
				       board.getClub_img());
		mav.addObject("club_img_name)", 
				          board.getClub_img_name());
		
		return mav;
	}
	//=========이미지 출력=========//
	@RequestMapping("/clubboard/imageView.do")
	public ModelAndView viewImage(
			   @RequestParam int club_num,
			   HttpSession session) {
		
		ClubVO club = 
				clubService.selectBoard(club_num);
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름
		mav.setViewName("imageView");
		
		mav.addObject("imageFile", club.getClub_img());
		mav.addObject("filename", club.getClub_img_name()); 
		 
		return mav;
	}
	//===========게시판 글수정===========//
	//수정 폼
	@GetMapping("/clubboard/update.do")
	public String formUpdate(
			@RequestParam int club_num,
			                         Model model) {
		ClubVO clubVO = 
				clubService.selectBoard(club_num);
		
		model.addAttribute("clubVO", clubVO);
		
		return "clubboardModify";
	}
	
	//수정 폼에서 전송된 데이터 처리
	@PostMapping("/clubboard/update.do")
	public String submitUpdate(@Valid ClubVO clubVO,
			            BindingResult result,
			            HttpServletRequest request,
			            Model model) {
		Logger.debug("<<글수정>> : " + clubVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			//title 또는 content가 입력되지 않아 유효성 체크에
			//걸리면 파일 정보를 잃어버리기 때문에 품을
			//호출할 때 다시 셋팅해주어야 함.
			ClubVO vo = clubService.selectBoard(
							clubVO.getClub_num());
			//ClubVO.setFilename(vo.getFilename()); 
			return "clubboardModify";
		}
		
		//글수정
		clubService.updateBoard(clubVO);
		
		//View에 표히살 메시지
		model.addAttribute("message", "글수정 완료!!");
		model.addAttribute("url", 
				request.getContextPath()+"/clubboard/detail.do?club_num="+clubVO.getClub_num());	

		return "common/resultView";
	}
	
	//==========게시판 글삭제==========//
		@RequestMapping("/clubboard/delete.do")
		public String submitDelete(
				       @RequestParam int club_num,
				       Model model,
				       HttpServletRequest request) {
			
			Logger.debug("<<글삭제>> : " + club_num);
			
			//글삭제
			clubService.deleteBoard(club_num);
			
			//View에 표시할 메시지
			model.addAttribute("message", "글삭제 완료!!");
			model.addAttribute("url", 
					request.getContextPath()+"/clubboard/list.do");
			
			return "common/resultView";
		}
}