package kr.spring.community.controller;

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

import kr.spring.community.service.CommuBoardService;
import kr.spring.community.vo.CommunityFavVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class CommuBoardAjaxController {
	private static final Logger logger =
			LoggerFactory.getLogger(CommuBoardAjaxController.class);
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private CommuBoardService boardService;
	
	//======================유머 게시판 시작===================//
	//===========부모글 업로드 파일 삭제===========//
	@RequestMapping("/commuboard/deleteFile.do")
	@ResponseBody
	public Map<String,String> processFile(
			         @RequestParam int commu_num,
			                HttpSession session){
		Map<String,String> mapJson = 
				new HashMap<String,String>();
		
		MemberVO user = 
			   (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			boardService.deleteFile(commu_num);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}

	
	//==========부모글 좋아요============//
	//부모글 좋아요 등록
	@RequestMapping("/commuboard/writeFav.do")
	@ResponseBody
	public Map<String,Object> writeFav(
			             CommunityFavVO fav,
			             HttpSession session){
		logger.debug("<<부모글 좋아요 등록>> : " + fav);
		
		Map<String,Object> mapJson= 
				new HashMap<String,Object>();
		
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 셋팅
			fav.setMem_num(user.getMem_num());
			
			//기존에 등록된 좋아요 확인
			CommunityFavVO boardFav = 
					boardService.selectFav(fav);
			
			if(boardFav!=null) {//등록된 좋아요 정보가 있는 경우
				boardService.deleteFav(boardFav.getFav_num());
				
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
				mapJson.put("count", boardService.selectFavCount(
						                      fav.getCommu_num()));
				
			}else {//등록된 좋아요 정보가 없는 경우
				boardService.insertFav(fav);
				
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
				mapJson.put("count", boardService.selectFavCount(
						                    fav.getCommu_num()));
			}
		}
		return mapJson;
	}
	//부모글 좋아요 읽기
	@RequestMapping("/commuboard/getFav.do")
	@ResponseBody
	public Map<String,Object> getFav(
					 CommunityFavVO fav,
			         HttpSession session){
		
		logger.debug("<<게시판 좋아요 읽기>> : " + fav);
		
		Map<String,Object> mapJson = 
				new HashMap<String,Object>();
		
		MemberVO user = 
			  (MemberVO)session.getAttribute("user");
		if(user==null) {//로그인이 되지 않은 경우
			mapJson.put("status", "noFav");
			mapJson.put("count", boardService.selectFavCount(
					                     fav.getCommu_num()));
		}else {//로그인 된 경우
			fav.setMem_num(user.getMem_num());
			
			//등록된 좋아요 정보 읽기
			CommunityFavVO boardFav = boardService.selectFav(fav);
			
			if(boardFav!=null) {//좋아요 등록
				mapJson.put("status", "yesFav");
				mapJson.put("count", boardService.selectFavCount(
						                     fav.getCommu_num()));
			}else {//좋아요 미등록
				mapJson.put("status", "noFav");
				mapJson.put("count", boardService.selectFavCount(
						                     fav.getCommu_num()));
			}
		}
		return mapJson;		
	}
	
	//=======댓글 등록======//
	@RequestMapping("/commuboard/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(
			  CommunityReplyVO boardReplyVO,
			  HttpSession session,
			  HttpServletRequest request){
		
		logger.debug("<<댓글 등록>> : " + boardReplyVO);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		MemberVO user = 
			(MemberVO)session.getAttribute("user");
		if(user==null) {//로그인 안 됨
			mapAjax.put("result", "logout");
		}else {//로그인 됨
			//회원번호 등록
			boardReplyVO.setMem_num(
					             user.getMem_num());
			
			//댓글 등록
			boardService.insertReply(boardReplyVO);
			mapAjax.put("result","success");
		}
		return mapAjax;
	}
	
	//==========댓글 목록==========//
	@RequestMapping("/commuboard/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(
			 @RequestParam(value="pageNum",defaultValue="1") 
			  int currentPage,
			  @RequestParam int commu_num,
			  HttpSession session){
		
		logger.debug("<<currentPage>> : " + currentPage);
		logger.debug("<<board_num>> : " + commu_num);
		
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("commu_num", commu_num); //부모글에 달려있는 댓글을 가져와야함
		
		//총 글의 개수
		int count = 
			boardService.selectRowCountReply(map);
		
		PagingUtil page = 
				new PagingUtil(currentPage,count,
						rowCount,pageCount,null);
		
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		List<CommunityReplyVO> list = null;
		if(count > 0) {
			list = boardService.selectListReply(map);
		}else {//데이터가 없으면 list는 null로 날라가는데 비어있는list로 날림 , 자바스크립트에서 비어있는 배열로 인식한다.
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
	@RequestMapping("/commuboard/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(
				  CommunityReplyVO boardReplyVO,
			      HttpSession session,
			      HttpServletRequest request){
		
		logger.debug("<<댓글 수정>> : " + boardReplyVO);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		CommunityReplyVO db_reply = 
				boardService.selectReply(
						     boardReplyVO.getReply_num());
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
	@RequestMapping("/commuboard/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(
			            @RequestParam int reply_num,
			            HttpSession session){
		
		logger.debug("<<reply_num>> : " + reply_num);
		
		Map<String,String> mapAjax =
				new HashMap<String,String>();
		
		MemberVO user = 
			(MemberVO)session.getAttribute("user");
		CommunityReplyVO db_reply = 
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
			
			mapAjax.put("result", "success"); //정상적으로 삭제시 문구 보냄
		}else {
			//로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		return mapAjax;
	}
	//======================유머 게시판 끝===================//

	
	//======================레시피 게시판 시작===================//
	//===========레시피 부모글 업로드 파일 삭제===========//
	@RequestMapping("/commuRecipe/deleteFile.do")
	@ResponseBody
	public Map<String,String> processFile2(
			         @RequestParam int commu_num,
			                HttpSession session){
		Map<String,String> mapJson = 
				new HashMap<String,String>();
		
		MemberVO user = 
			   (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			boardService.deleteFile2(commu_num);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	//==========부모글 좋아요============//
		//레시피 부모글 좋아요 등록
		@RequestMapping("/commuRecipe/writeFav.do")
		@ResponseBody
		public Map<String,Object> writeFav2(
							 RecipeFavVO fav,
				             HttpSession session){
			logger.debug("<<부모글 좋아요 등록>> : " + fav);
			
			Map<String,Object> mapJson= 
					new HashMap<String,Object>();
			
			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			if(user==null) {
				mapJson.put("result", "logout");
			}else {
				//로그인된 회원번호 셋팅
				fav.setMem_num(user.getMem_num());
				
				//기존에 등록된 좋아요 확인
				RecipeFavVO boardFav = 
						boardService.selectFav2(fav);
				
				if(boardFav!=null) {//등록된 좋아요 정보가 있는 경우
					boardService.deleteFav2(boardFav.getFav_num());
					
					mapJson.put("result", "success");
					mapJson.put("status", "noFav");
					mapJson.put("count", boardService.selectFavCount2(
							                      fav.getCommu_num()));
					
				}else {//등록된 좋아요 정보가 없는 경우
					boardService.insertFav2(fav);
					
					mapJson.put("result", "success");
					mapJson.put("status", "yesFav");
					mapJson.put("count", boardService.selectFavCount2(
							                    fav.getCommu_num()));
				}
			}
			return mapJson;
		}
		
		//레시피 부모글 좋아요 읽기
		@RequestMapping("/commuRecipe/getFav.do")
		@ResponseBody
		public Map<String,Object> getFav2(
						 RecipeFavVO fav,
				         HttpSession session){
			
			logger.debug("<<게시판 좋아요 읽기>> : " + fav);
			
			Map<String,Object> mapJson = 
					new HashMap<String,Object>();
			
			MemberVO user = 
				  (MemberVO)session.getAttribute("user");
			if(user==null) {//로그인이 되지 않은 경우
				mapJson.put("status", "noFav");
				mapJson.put("count", boardService.selectFavCount2(
						                     fav.getCommu_num()));
			}else {//로그인 된 경우
				fav.setMem_num(user.getMem_num());
				
				//등록된 좋아요 정보 읽기
				RecipeFavVO boardFav = boardService.selectFav2(fav);
				
				if(boardFav!=null) {//좋아요 등록
					mapJson.put("status", "yesFav");
					mapJson.put("count", boardService.selectFavCount2(
							                     fav.getCommu_num()));
				}else {//좋아요 미등록
					mapJson.put("status", "noFav");
					mapJson.put("count", boardService.selectFavCount2(
							                     fav.getCommu_num()));
				}
			}
			return mapJson;		
		}
		//=======댓글 등록======//
		@RequestMapping("/commuRecipe/writeReply.do")
		@ResponseBody
		public Map<String,String> writeReply2(
				  RecipeReplyVO boardReplyVO,
				  HttpSession session,
				  HttpServletRequest request){
			
			logger.debug("<<댓글 등록>> : " + boardReplyVO);
			
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO user = 
				(MemberVO)session.getAttribute("user");
			if(user==null) {//로그인 안 됨
				mapAjax.put("result", "logout");
			}else {//로그인 됨
				//회원번호 등록
				boardReplyVO.setMem_num(
						             user.getMem_num());
				
				//댓글 등록
				boardService.insertReply2(boardReplyVO);
				mapAjax.put("result","success");
			}
			return mapAjax;
		}
		
		//==========댓글 목록==========//
		@RequestMapping("/commuRecipe/listReply.do")
		@ResponseBody
		public Map<String,Object> getList2(
				 @RequestParam(value="pageNum",defaultValue="1") 
				  int currentPage,
				  @RequestParam int commu_num,
				  HttpSession session){
			
			logger.debug("<<currentPage>> : " + currentPage);
			logger.debug("<<board_num>> : " + commu_num);
			
			Map<String,Object> map = 
					new HashMap<String,Object>();
			map.put("commu_num", commu_num); //부모글에 달려있는 댓글을 가져와야함
			
			//총 글의 개수
			int count = 
				boardService.selectRowCountReply2(map);
			
			PagingUtil page = 
					new PagingUtil(currentPage,count,
							rowCount,pageCount,null);
			
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			List<RecipeReplyVO> list = null;
			if(count > 0) {
				list = boardService.selectListReply2(map);
			}else {//데이터가 없으면 list는 null로 날라가는데 비어있는list로 날림 , 자바스크립트에서 비어있는 배열로 인식한다.
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
		@RequestMapping("/commuRecipe/updateReply.do")
		@ResponseBody
		public Map<String,String> modifyReply2(
					  RecipeReplyVO boardReplyVO,
				      HttpSession session,
				      HttpServletRequest request){
			
			logger.debug("<<댓글 수정>> : " + boardReplyVO);
			
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			RecipeReplyVO db_reply = 
					boardService.selectReply2(
							     boardReplyVO.getReply_num());
			if(user==null) {//로그인이 되지 않는 경우
				mapAjax.put("result", "logout");
			}else if(user!=null && 
				  user.getMem_num()==db_reply.getMem_num()) {
				//로그인 회원번호와 작성자 회원번호 일치
				
				
				
				//댓글 수정
				boardService.updateReply2(boardReplyVO);
				mapAjax.put("result", "success");
			}else {
				//로그인 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result", "wrongAccess");
			}
			return mapAjax;
		}
		//==========댓글 삭제==========//
		@RequestMapping("/commuRecipe/deleteReply.do")
		@ResponseBody
		public Map<String,String> deleteReply2(
				            @RequestParam int reply_num,
				            HttpSession session){
			
			logger.debug("<<reply_num>> : " + reply_num);
			
			Map<String,String> mapAjax =
					new HashMap<String,String>();
			
			MemberVO user = 
				(MemberVO)session.getAttribute("user");
			RecipeReplyVO db_reply = 
					boardService.selectReply2(reply_num);
			if(user==null) {
				//로그인이 되지 않은 경우
				mapAjax.put("result", "logout");
			}else if(user!=null && 
			  user.getMem_num()==db_reply.getMem_num()) {
				//로그인이 되어 있고 
				//로그인한 회원번호와 작성자 회원번호 일치
				
				//댓글 삭제
				boardService.deleteReply2(reply_num);
				
				mapAjax.put("result", "success"); //정상적으로 삭제시 문구 보냄
			}else {
				//로그인한 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result", "wrongAccess");
			}
			return mapAjax;
		}	
	
	
	
	
	//======================레시피 게시판 끝===================//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
