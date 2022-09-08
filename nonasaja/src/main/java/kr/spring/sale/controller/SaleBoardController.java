package kr.spring.sale.controller;


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

import kr.spring.member.vo.MemberVO;
import kr.spring.sale.service.SaleBoardService;
import kr.spring.sale.vo.SaleVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class SaleBoardController {
	private static final Logger logger =
			         LoggerFactory.getLogger(
					          SaleBoardController.class);
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Autowired
	private SaleBoardService boardService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public SaleVO initCommand() {
		return new SaleVO();
	}
	
	//===========게시판 글 등록============//
	//등록 폼
	@GetMapping("/sale/saleBoardWrite.do")
	public String form() {
		return "saleBoardWrite";
	}
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/sale/saleBoardWrite.do")
	public String submit(@Valid SaleVO saleVO,
			      BindingResult result,
			      HttpServletRequest request,
			      HttpSession session,
			      Model model) {
		
		logger.debug("<<게시판 글 저장>> : " + saleVO);
		
		//유효성 검사 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원번호 셋팅
		saleVO.setMem_num(user.getMem_num());
		
		//글쓰기
		boardService.insertBoard(saleVO);
		
		//View에 표시할 메시지
		model.addAttribute(
				"message", "글 등록이 완료되었습니다.");
		model.addAttribute(
		 "url", request.getContextPath()+"/sale/saleBoardList.do");
		
		return "common/resultView";
	}
	
	
	//===========게시판 글 목록============//
	@RequestMapping("/sale/saleBoardList.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") 
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword) {
		
		Map<String,Object> map = 
				    new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//글의 총개수(검색된 글의 개수)
		int count = boardService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,
						currentPage,count,
						rowCount,pageCount,"saleBoardList.do");
		
		List<SaleVO> list = null;
		if(count > 0) {
			
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = boardService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("saleBoardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	//========게시판 글상세===========//
		@RequestMapping("/sale/saleBoardDetail.do")
		public ModelAndView detail(
				          @RequestParam int board_num) {
			
			logger.debug("<<board_num>> : " + board_num);
			
			//해당 글의 조회수 증가
			boardService.updateHit(board_num);
			
			SaleVO board = boardService.selectBoard(board_num);
			
			//제목에 태그를 허용하지 않음
			board.setTitle(
				 StringUtil.useNoHtml(board.getTitle()));
			//내용에 줄바꿈 처리하면서 태그를 허용하지 않음
			//ckeditor 사용시 아래 코드 주석 처리
			/*
			board.setContent(
			 StringUtil.useBrNoHtml(board.getContent()));
			*/
			                          //뷰 이름    속성명   속성값
			return new ModelAndView("saleBoardDetail","board",board);
		}
		
		//===========게시판 글수정===========//
		//수정 폼
		@GetMapping("/sale/update.do")
		public String formUpdate(
				@RequestParam int board_num,
				                         Model model) {
			SaleVO boardVO = boardService.selectBoard(board_num);
			
			model.addAttribute("saleVO", boardVO);
			
			return "saleBoardModify";
		}
		//수정 폼에서 전송된 데이터 처리
		@PostMapping("/sale/update.do")
		public String submitUpdate(@Valid SaleVO boardVO,
				            BindingResult result,
				            HttpServletRequest request,
				            Model model) {
			logger.debug("<<글수정>> : " + boardVO);
			
			//유효성 체크 결과 오류가 있으면 폼 호출
			if(result.hasErrors()) {
				//title 또는 content가 입력되지 않아 유효성 체크에
				//걸리면 파일 정보를 잃어버리기 때문에 품을
				//호출할 때 다시 셋팅해주어야 함.

				//boardVO.setFilename(vo.getFilename());
				return "saleBoardModify";
			}
			
			
			//글수정
			boardService.updateBoard(boardVO);
			
			//View에 표히살 메시지
			model.addAttribute("message", "글수정 완료!!");
			model.addAttribute("url", 
					request.getContextPath()+"/sale/saleBoardDetail.do?board_num="+boardVO.getBoard_num());	

			return "common/resultView";
		}
		
		//==========게시판 글삭제==========//
		@RequestMapping("/sale/delete.do")
		public String submitDelete(
				       @RequestParam int board_num,
				       Model model,
				       HttpServletRequest request) {
			
			logger.debug("<<글삭제>> : " + board_num);
			
			//글삭제
			boardService.deleteBoard(board_num);
			
			//View에 표시할 메시지
			model.addAttribute("message", "글삭제 완료!!");
			model.addAttribute("url", 
					request.getContextPath()+"/sale/saleBoardList.do");
			
			return "common/resultView";
		}
	
}




