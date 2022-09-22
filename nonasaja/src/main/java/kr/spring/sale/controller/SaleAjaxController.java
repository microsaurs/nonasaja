package kr.spring.sale.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.vo.MemberVO;
import kr.spring.sale.service.SaleBoardService;
import kr.spring.sale.vo.SaleVO;
import kr.spring.sale.vo.SaleReplyVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class SaleAjaxController {
	private static final Logger logger =
			LoggerFactory.getLogger(SaleAjaxController.class);

	private int rowCount = 10;
	private int pageCount = 10;

	@Autowired
	private SaleBoardService boardService;

	//===========부모글 업로드 파일 삭제===========//
	@RequestMapping("/sale/deleteFile.do")
	@ResponseBody
	public Map<String,String> processFile(
			@RequestParam int board_num,
			HttpSession session){
		Map<String,String> mapJson = 
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			boardService.deleteFile(board_num);
			mapJson.put("result", "success");
		}

		return mapJson;
	}

	//========댓글 등록=========//
	@RequestMapping("/sale/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(
			SaleReplyVO boardReplyVO,
			HttpSession session,
			HttpServletRequest request){

		logger.debug("<<댓글 등록>> : " + boardReplyVO);

		Map<String,String> mapAjax = 
				new HashMap<String,String>();

		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {//로그인 안 됨
			mapAjax.put("result", "logout");
		}else {//로그인 됨
			//회원번호 등록
			boardReplyVO.setMem_num(user.getMem_num());

			//댓글 등록
			boardService.insertReply(boardReplyVO);
			mapAjax.put("result","success");
		}
		return mapAjax;
	}


	//==========댓글 목록==========//
	@RequestMapping("/sale/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="pageNum",defaultValue="1") 
			int currentPage,
			@RequestParam int board_num,
			HttpSession session){

		logger.debug("<<currentPage>> : " + currentPage);
		logger.debug("<<board_num>> : " + board_num);

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("board_num", board_num);

		//총 글의 개수
		int count = 
				boardService.selectRowCountReply(map);

		PagingUtil page = 
				new PagingUtil(currentPage,count,
						rowCount,pageCount,null);

		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());

		List<SaleReplyVO> list = null;
		if(count > 0) {
			list = boardService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}

		Map<String,Object> mapAjax = 
				new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);

		//로그인 한 회원정보 셋팅
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user!=null) {
			mapAjax.put(
					"user_num", user.getMem_num());
		}

		return mapAjax;
	}

	//==========댓글 수정==========//
	@RequestMapping("/sale/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(
			SaleReplyVO boardReplyVO,
			HttpSession session,
			HttpServletRequest request){

		logger.debug("<<댓글 수정>> : " + boardReplyVO);

		Map<String,String> mapAjax = 
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		SaleReplyVO db_reply = 
				boardService.selectReply(boardReplyVO.getReply_num());
		if(user==null) {//로그인이 되지 않는 경우
			mapAjax.put("result", "logout");
		}else if(user!=null && 
				user.getMem_num()==db_reply.getMem_num()) {
			//로그인 회원번호와 작성자 회원번호 일치

			//댓글 수정
			boardService.updateReply(boardReplyVO);
			mapAjax.put("result", "success");
		}else {
			//로그인 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		return mapAjax;
	}
	//==========댓글 삭제==========//
	@RequestMapping("/sale/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(
			@RequestParam int reply_num,
			HttpSession session){

		logger.debug("<<reply_num>> : " + reply_num);

		Map<String,String> mapAjax =
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		SaleReplyVO db_reply = 
				boardService.selectReply(reply_num);
		if(user==null) {
			//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else if(user!=null && 
				user.getMem_num()==db_reply.getMem_num()) {
			//로그인이 되어 있고 
			//로그인한 회원번호와 작성자 회원번호 일치

			//댓글 삭제
			boardService.deleteReply(reply_num);

			mapAjax.put("result", "success");
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		return mapAjax;
	}
}





