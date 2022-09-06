package kr.spring.community.controller;

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

import kr.spring.community.service.CommuBoardService;
import kr.spring.community.vo.CommunityVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class CommuBoardController {
private static final Logger logger = LoggerFactory.getLogger(CommuBoardController.class);
	
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Autowired
	private CommuBoardService boardService;
	
	//자바빈(VO) 초기화
	@ModelAttribute 
	public CommunityVO initCommad() {
		return new CommunityVO();
	}
	
	//========유머 글 등록========//
	//등록 폼
	@GetMapping("/commuboard/humorwrite.do")
	public String form() {
		return "commuHumorWrite"; 			
	}
	
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/commuboard/humorwrite.do")
	public String submit(@Valid CommunityVO boardVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		logger.debug("<<유머 게시판 글 저장>> : " + boardVO);
		
		//유효성 검사 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원번호 세팅
		boardVO.setMem_num(user.getMem_num());
		
		//글쓰기
		boardService.insertBoard(boardVO);
		
		//View에 표시할 메시지 
		model.addAttribute("message", "글 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/commuboard/list.do"); 
				
		
		return "common/resultView"; 
	}
	
	//===========유머 게시판 글 목록==========//
	@RequestMapping("/commuboard/list.do")
	public ModelAndView process( 
			@RequestParam(value="pageNum",defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("keyword", keyword);
	
		//글의 총 개수(검색된 글의 개수)
		int count = boardService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
	
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"list.do");
				
		List<CommunityVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = boardService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("commuBoardList"); 
		mav.addObject("count", count); 
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	//========유머게시판 글 상세=========//
	@RequestMapping("/commuboard/detail.do")
	public ModelAndView detail(@RequestParam int commu_num) {
		
		logger.debug("<<board_num>> : " + commu_num);
		
		//해당 글의 조회수 증가
		boardService.updateHit(commu_num);
		
		CommunityVO board = boardService.selectBoard(commu_num); 
		
		//제목에 태그를 허용하지 않음
		board.setCommu_title(StringUtil.useNoHtml(board.getCommu_title()));
		
		return new ModelAndView("commuBoardView","board",board);
		
	}
	
	//=========이미지 출력=========//
	@RequestMapping("/commuboard/imageView.do")
	public ModelAndView viewImage(
			   @RequestParam int commu_num,
			   @RequestParam int board_type) {
		
		CommunityVO board = 
				boardService.selectBoard(commu_num);
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름
		mav.setViewName("imageView");
		
		if(board_type==1) {//프로필 사진
			mav.addObject("imageFile", board.getPhoto());
			mav.addObject("filename", board.getPhoto_name());
		}else if(board_type==2) {//업로드된 이미지
			mav.addObject("imageFile", board.getUploadfile());
			mav.addObject("filename", board.getFilename());
		}else if(board_type==3) {//업로드된 이미지
			mav.addObject("imageFile", board.getUploadfile2());
			mav.addObject("filename", board.getFilename2());
		}else if(board_type==4) {//업로드된 이미지
			mav.addObject("imageFile", board.getUploadfile3());
			mav.addObject("filename", board.getFilename3());
		}
		
		return mav;
	}
	
	
	
	//============게시판 글수정============//
	//수정품
	@GetMapping("/commuboard/update.do")
	public String formUpdate(@RequestParam int commu_num, Model model) { 
		
		CommunityVO boardVO = boardService.selectBoard(commu_num);
		
		model.addAttribute("boardVO", boardVO); 
		
		return "commuBoardModify";
	}
	
	//수정 폼에서 전송된 데이터 처리
		@PostMapping("/commuboard/update.do")
		public String submitUpdate(@Valid CommunityVO boardVO,
				            BindingResult result,
				            HttpServletRequest request,
				            Model model) {
			logger.debug("<<글수정>> : " + boardVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {

				CommunityVO vo = boardService.selectBoard(
						            boardVO.getCommu_num());
				return "commuBoardModify";
			}
			//글수정
			boardService.updateBoard(boardVO); //유효성체크 안걸리면
			
			//View에 표시할 메시지
			model.addAttribute("message", "글수정 완료!!");
			model.addAttribute("url", 
					request.getContextPath()+"/commuboard/detail.do?commu_num="+boardVO.getCommu_num());	

			return "common/resultView";
		}
		
		//==========게시판 글삭제==========//
		@RequestMapping("/commuboard/delete.do")
		public String submitDelete(
				       @RequestParam int commu_num,
				       Model model,
				       HttpServletRequest request) {
			
			logger.debug("<<글삭제>> : " + commu_num);
			
			//글삭제
			boardService.deleteBoard(commu_num);
			
			//View에 표시할 메시지
			model.addAttribute("message", "글삭제 완료!!");
			model.addAttribute("url", 
					request.getContextPath()+"/commuboard/list.do");
			
			return "common/resultView";
		}
		
		
		
		
}		

	










