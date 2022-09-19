package kr.spring.used.controller;

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
import kr.spring.used.service.UsedService;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.util.PagingUtil;

@Controller
public class UsedAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(UsedAjaxController.class);
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private UsedService usedService;
	
	//=========부모글 업로드 파일 삭제===========//
	@RequestMapping("/used/deleteFile.do")
	@ResponseBody
	public Map<String, String> processFile(
			@RequestParam int used_num,
					HttpSession session){
		
		Map<String, String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			usedService.deleteFile(used_num);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	
	//==========부모글 좋아요===========//
	//부모글 좋아요 등록
	@RequestMapping("/used/writeFav.do")
	@ResponseBody
	public Map<String, Object> writeFav(UsedFavVO fav,
										HttpSession session){
		logger.debug("<<부모글 좋아요 등록>> : " + fav);
		
		Map<String, Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user.getMem_num());
			
			//기존에 등록된 좋아요 확인
			UsedFavVO usedFav = usedService.selectFav(fav);
			
			if(usedFav!=null) {//등록된 좋아요 정보가 있는 경우
				usedService.deleteFav(usedFav.getFav_num());
				
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
				mapJson.put("count", usedService.selectFavCount(fav.getUsed_num()));
			}else {//등록된 좋아요 정보가 없는 경우
				usedService.insertFav(fav);
				
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
				mapJson.put("count", usedService.selectFavCount(fav.getUsed_num()));
			}
		}
		return mapJson;
	}
	//부모글 좋아요 읽기
	@RequestMapping("/used/getFav.do")
	@ResponseBody
	public Map<String, Object> getFav(UsedFavVO fav,
									  HttpSession session){
		logger.debug("<<게시판 좋아요 읽기>> : " + fav);
		
		Map<String, Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {//로그인이 되지 않은 경우
			mapJson.put("status", "noFav");
			mapJson.put("count", usedService.selectFavCount(fav.getUsed_num()));
		}else {//로그인 된 경우
			fav.setMem_num(user.getMem_num());
			
			//등록된 좋아요 정보 읽기
			UsedFavVO usedFav = usedService.selectFav(fav);
			
			if(usedFav!=null) {//좋아요 등록
				mapJson.put("status", "yesFav");
				mapJson.put("count", usedService.selectFavCount(fav.getUsed_num()));
			}else {//좋아요 미등록
				mapJson.put("status", "noFav");
				mapJson.put("count", usedService.selectFavCount(fav.getUsed_num()));
			}
		}
		return mapJson;
	}
	
	//==========댓글 등록==========//
	@RequestMapping("/used/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(
			UsedReplyVO usedReplyVO,
			HttpSession session,
			HttpServletRequest request){
		
		logger.debug("<<댓글 등록>> : " + usedReplyVO);
		
		Map<String, String> mapAjax = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {//로그인 안 됨
			mapAjax.put("result", "logout");
		}else {//로그인 됨
			//회원번호 등록
			usedReplyVO.setMem_num(user.getMem_num());
			
			//댓글 등록
			usedService.insertReply(usedReplyVO);
			mapAjax.put("result","success");
		}

		return mapAjax;
	}
	
	//==========댓글 목록==========//
	@RequestMapping("/used/listReply.do")
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value="pageNum",defaultValue = "1")
			int currentPage,
			@RequestParam int used_num,
			HttpSession session){
		
		logger.debug("<<currentPage>> : " + currentPage);
		logger.debug("<<used_num>> : " + used_num);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("used_num", used_num);
		
		//총 글의 개수
		int count = usedService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		List<UsedReplyVO> list = null;
		if(count > 0) {
			list = usedService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		Map<String, Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		
		//로그인 한 회원정보 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!=null) {
			mapAjax.put("user_num", user.getMem_num());
		}
		
		return mapAjax;
	}
	
	//===========댓글 수정===========//
	@RequestMapping("/used/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(
				UsedReplyVO usedReplyVO,
				HttpSession session,
				HttpServletRequest request){
		
		logger.debug("<<댓글 수정>> : " + usedReplyVO);
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		UsedReplyVO db_reply = usedService.selectReply(
											usedReplyVO.getReply_num());
		
		if(user==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else if(user!=null && 
				user.getMem_num()==db_reply.getMem_num()) {
			//로그인 회원번호와 작성자 회원번호 일치
			
			//댓글 수정
			usedService.updateReply(usedReplyVO);
			mapAjax.put("result", "success");
		}else {
			//로그인 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		return mapAjax;
	}
	//==========댓글 삭제==========//
		@RequestMapping("/used/deleteReply.do")
		@ResponseBody
		public Map<String,String> deleteReply(
				            @RequestParam int reply_num,
				            HttpSession session){
			
			logger.debug("<<reply_num>> : " + reply_num);
			
			Map<String,String> mapAjax =
					new HashMap<String,String>();
			
			MemberVO user = 
				(MemberVO)session.getAttribute("user");
			UsedReplyVO db_reply = 
					usedService.selectReply(reply_num);
			if(user==null) {
				//로그인이 되지 않은 경우
				mapAjax.put("result", "logout");
			}else if(user!=null && 
			  user.getMem_num()==db_reply.getMem_num()) {
				//로그인이 되어 있고 
				//로그인한 회원번호와 작성자 회원번호 일치
				
				//댓글 삭제
				usedService.deleteReply(reply_num);
				
				mapAjax.put("result", "success"); //정상적으로 삭제시 문구 보냄
			}else {
				//로그인한 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result", "wrongAccess");
			}
			return mapAjax;
		}

	
	
	
	
	
	
	
}
