package kr.spring.community.controller;

import java.util.HashMap;
import java.util.Map;

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
import kr.spring.member.vo.MemberVO;

@Controller
public class CommuBoardAjaxController {
	private static final Logger logger =
			LoggerFactory.getLogger(CommuBoardAjaxController.class);
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private CommuBoardService boardService;
	
	//===========부모글 업로드 파일 삭제===========//
/*	@RequestMapping("/commuboard/deleteFile.do")
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
*/
	
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
}
