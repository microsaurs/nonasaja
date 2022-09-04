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
		return "commuHumorWrite"; 				//tiles, kim.xml에 설정
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
		model.addAttribute("url", request.getContextPath()+"/commuboard/list.do"); //스크립트가 보여지고 이동할 곳 지정
				
		
		return "common/resultView"; //타일스 설정안하면 jsp직접호출 
	}
	
	//===========유머 게시판 글 목록==========//
	@RequestMapping("/commuboard/list.do")
	public ModelAndView process( 
			@RequestParam(value="pageNum",defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>(); //Map으로 묶어보냄
		map.put("keyfield", keyfield);
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
		mav.setViewName("commuBoardList"); //tiles ,board.xml에 설정 , 응답할 view 이름을 설정
		mav.addObject("count", count); //addObject : view에 전달할 값을 설정
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
		
		CommunityVO board = boardService.selectBoard(commu_num); //한 건의 레코드 읽음
		
		//제목에 태그를 허용하지 않음
		board.setCommu_title(StringUtil.useNoHtml(board.getCommu_title()));
		
		return new ModelAndView("commuBoardView","board",board);//뷰이름(tiles),속성명,속성값
		
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
				//title 또는 content가 입력되지 않아 유효성 체크에
				//걸리면 파일 정보를 잃어버리기 때문에 품을
				//호출할 때 다시 셋팅해주어야 함.
				CommunityVO vo = boardService.selectBoard(
						            boardVO.getCommu_num());
				return "commuBoardModify";
			}
			//글수정
			boardService.updateBoard(boardVO); //유효성체크 안걸리면
			
			//View에 표시할 메시지
			model.addAttribute("message", "글수정 완료!!");
			model.addAttribute("url", 
					request.getContextPath()+"/board/detail.do?commu_num="+boardVO.getCommu_num());	

			return "common/resultView";
		}
		
		
}		

	










