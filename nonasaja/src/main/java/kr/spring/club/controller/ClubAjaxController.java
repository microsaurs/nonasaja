package kr.spring.club.controller;

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

import kr.spring.club.vo.ClubReplyVO;
import kr.spring.club.vo.ClubRereplyVO;
import kr.spring.club.service.ClubService;
import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class ClubAjaxController {
	private static final Logger logger =
			LoggerFactory.getLogger(ClubAjaxController.class);
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private ClubService clubService;
	
	//===========부모글 업로드 파일 삭제===========//
		@RequestMapping("/clubboard/deleteFile.do")
		@ResponseBody
		public Map<String,String> processFile(
				         @RequestParam int club_num,
				                HttpSession session){
			Map<String,String> mapJson = 
					new HashMap<String,String>();
 
			MemberVO user = 
				   (MemberVO)session.getAttribute("user");
			if(user==null) {
				mapJson.put("result", "logout");
			}else {
				clubService.deleteFile(club_num);
				mapJson.put("result", "success");
			}
			
			return mapJson;
		}
		
		//==========부모글 좋아요============//
		//부모글 좋아요 등록
		@RequestMapping("/clubboard/writeFav.do")
		@ResponseBody
		public Map<String,Object> writeFav(
				             ClubFavVO fav, int club_num,
				             HttpSession session){
			logger.debug("<<부모글 좋아요 등록>> : " + fav);
			logger.debug("<좋아요 정보>..." + fav);
			Map<String,Object> mapJson= 
					new HashMap<String,Object>();
			
			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			if(user==null) {
				mapJson.put("result", "logout");
			}else {
				//로그인된 회원번호 셋팅
				fav.setMem_num(user.getMem_num());
				fav.setClub_num(club_num);
				//기존에 등록된 좋아요 확인
				ClubFavVO clubFav = 
						clubService.selectFav(fav);
				
				if(clubFav!=null) {//등록된 좋아요 정보가 있는 경우
					clubService.deleteFav(clubFav.getFav_num());
					
					mapJson.put("result", "success");
					mapJson.put("status", "noFav");
					mapJson.put("count", clubService.selectFavCount(
							                      fav.getClub_num()));
					
				}else {//등록된 좋아요 정보가 없는 경우
					clubService.insertFav(fav);
					logger.debug("<좋아요 정보2>..." + fav);
					mapJson.put("result", "success");
					mapJson.put("status", "yesFav");
					mapJson.put("count", clubService.selectFavCount(
							                    fav.getClub_num()));
				}
			}
			return mapJson;
		}
		
		//부모글 좋아요 읽기
		@RequestMapping("/clubboard/getFav.do")
		@ResponseBody
		public Map<String,Object> getFav(
				         ClubFavVO fav,
				         HttpSession session){
			
			logger.debug("<<게시판 좋아요 읽기>> : " + fav);
			
			Map<String,Object> mapJson = 
					new HashMap<String,Object>();
			
			MemberVO user = 
				  (MemberVO)session.getAttribute("user");
			if(user==null) {//로그인이 되지 않은 경우
				mapJson.put("status", "noFav");
				mapJson.put("count", clubService.selectFavCount(
						                     fav.getClub_num()));
			}else {//로그인 된 경우
				fav.setMem_num(user.getMem_num());
				
				//등록된 좋아요 정보 읽기
				ClubFavVO clubFav = clubService.selectFav(fav);
				
				if(clubFav!=null) {//좋아요 등록
					mapJson.put("status", "yesFav");
					mapJson.put("count", clubService.selectFavCount(
							                     fav.getClub_num()));
				}else {//좋아요 미등록
					mapJson.put("status", "noFav");
					mapJson.put("count", clubService.selectFavCount(
							                     fav.getClub_num()));
				}
			}
			return mapJson;		
		}
		
		//========댓글 등록=========//
		@RequestMapping("/clubboard/writeReply.do")
		@ResponseBody
		public Map<String,String> writeReply(
				  ClubReplyVO clubReplyVO,
				  HttpSession session,
				  HttpServletRequest request){
			
			logger.debug("<<댓글 등록>> : " + clubReplyVO);
			
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO user = 
				(MemberVO)session.getAttribute("user");
			if(user==null) {//로그인 안 됨
				mapAjax.put("result", "logout");
			}else {//로그인 됨
				//회원번호 등록
				clubReplyVO.setMem_num(
						             user.getMem_num());
			
				//댓글 등록
				clubService.insertReply(clubReplyVO);
				mapAjax.put("result","success");
			}
			return mapAjax;
		}
		
		//==========댓글 목록==========//
		@RequestMapping("/clubboard/listReply.do")
		@ResponseBody
		public Map<String,Object> getList(
				 @RequestParam(value="pageNum",defaultValue="1") 
				  int currentPage,
				  @RequestParam int club_num,
				  HttpSession session){
			
			logger.debug("<<currentPage>> : " + currentPage);
			logger.debug("<<club_num>> : " + club_num);
			
			Map<String,Object> map = 
					new HashMap<String,Object>();
			map.put("club_num", club_num);
			
			//총 글의 개수
			int count = 
				clubService.selectRowCountReply(map);
			
			PagingUtil page = 
					new PagingUtil(currentPage,count,
							rowCount,pageCount,null);
			
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			List<ClubReplyVO> list = null;
			if(count > 0) {
				list = clubService.selectListReply(map);
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
		@RequestMapping("/clubboard/updateReply.do")
		@ResponseBody
		public Map<String,String> modifyReply(
				      ClubReplyVO clubReplyVO,
				      HttpSession session,
				      HttpServletRequest request){
			
			logger.debug("<<댓글 수정>> : " + clubReplyVO);
			
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO user = 
					(MemberVO)session.getAttribute("user");
			ClubReplyVO db_reply = 
					clubService.selectReply(
							     clubReplyVO.getReply_num());
			if(user==null) {//로그인이 되지 않는 경우
				mapAjax.put("result", "logout");
			}else if(user!=null && 
				  user.getMem_num()==db_reply.getMem_num()) {
				//로그인 회원번호와 작성자 회원번호 일치
				
				
				//댓글 수정
				clubService.updateReply(clubReplyVO);
				mapAjax.put("result", "success");
			}else {
				//로그인 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result", "wrongAccess");
			}
			return mapAjax;
		}
		//==========댓글 삭제==========//
		@RequestMapping("/clubboard/deleteReply.do")
		@ResponseBody
		public Map<String,String> deleteReply(
				            @RequestParam int reply_num,
				            HttpSession session){
			
			logger.debug("<<reply_num>> : " + reply_num);
			
			Map<String,String> mapAjax =
					new HashMap<String,String>();
			
			MemberVO user = 
				(MemberVO)session.getAttribute("user");
			ClubReplyVO db_reply = 
					clubService.selectReply(reply_num);
			if(user==null) {
				//로그인이 되지 않은 경우
				mapAjax.put("result", "logout");
			}else if(user!=null && 
			  user.getMem_num()==db_reply.getMem_num()) {
				//로그인이 되어 있고 
				//로그인한 회원번호와 작성자 회원번호 일치
				
				//댓글 샂게
				clubService.deleteReply(reply_num);
				
				mapAjax.put("result", "success");
			}else {
				//로그인한 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result", "wrongAccess");
			}
			return mapAjax;
		}
		
		//======================================대댓글================================================//

		//========대댓글 등록=========//
		@RequestMapping("/clubboard/writeRereply.do")
		@ResponseBody
		public Map<String,String> writeRereply(
				  ClubRereplyVO clubRereplyVO,
				  HttpSession session,
				  HttpServletRequest request){
			
			logger.debug("<<대댓글 등록>> : " + clubRereplyVO);
			
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO user = 
				(MemberVO)session.getAttribute("user");
			if(user==null) {//로그인 안 됨
				mapAjax.put("result", "logout");
			}else {//로그인 됨
				//회원번호 등록
				clubRereplyVO.setMem_num(
						             user.getMem_num());
			
				//댓글 등록
				clubService.insertRereply(clubRereplyVO);
				mapAjax.put("result","success");
			}
			return mapAjax;
		}
		//==========대댓글 목록==========//
				@RequestMapping("/clubboard/listRereply.do")
				@ResponseBody
				public Map<String,Object> getList2(
						 @RequestParam(value="pageNum",defaultValue="1") 
						  int currentPage,
						  @RequestParam int club_num,
						  HttpSession session){
					
					logger.debug("<<currentPage>> : " + currentPage);
					logger.debug("<<club_num>> : " + club_num);
					
					Map<String,Object> map = 
							new HashMap<String,Object>();
					map.put("club_num", club_num);
					
					//총 글의 개수
					int count = 
						clubService.selectRowCountRereply(map);
					
					PagingUtil page = 
							new PagingUtil(currentPage,count,
									rowCount,pageCount,null);
					
					map.put("start", page.getStartRow());
					map.put("end", page.getEndRow());
					
					List<ClubRereplyVO> list = null;
					if(count > 0) {
						list = clubService.selectListRereply(map);
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
				
				//==========대댓글 수정==========//
				@RequestMapping("/clubboard/updateRereply.do")
				@ResponseBody
				public Map<String,String> modifyRereply(
						      ClubRereplyVO clubRereplyVO,
						      HttpSession session,
						      HttpServletRequest request){
					
					logger.debug("<<댓글 수정>> : " + clubRereplyVO);
					
					Map<String,String> mapAjax = 
							new HashMap<String,String>();
					
					MemberVO user = 
							(MemberVO)session.getAttribute("user");
					ClubRereplyVO db_rereply = 
							clubService.selectRereply(
									     clubRereplyVO.getRereply_num());
					if(user==null) {//로그인이 되지 않는 경우
						mapAjax.put("result", "logout");
					}else if(user!=null && 
						  user.getMem_num()==db_rereply.getMem_num()) {
						//로그인 회원번호와 작성자 회원번호 일치
						
						
						//댓글 수정
						clubService.updateRereply(clubRereplyVO);
						mapAjax.put("result", "success");
					}else {
						//로그인 회원번호와 작성자 회원번호 불일치
						mapAjax.put("result", "wrongAccess");
					}
					return mapAjax;
				}
				//==========대댓글 삭제==========//
				@RequestMapping("/clubboard/deleteRereply.do")
				@ResponseBody
				public Map<String,String> deleteRereply(
						            @RequestParam int rereply_num,
						            HttpSession session){
					
					logger.debug("<<reply_num>> : " + rereply_num);
					
					Map<String,String> mapAjax =
							new HashMap<String,String>();
					
					MemberVO user = 
						(MemberVO)session.getAttribute("user");
					ClubRereplyVO db_rereply = 
							clubService.selectRereply(rereply_num);
					if(user==null) {
						//로그인이 되지 않은 경우
						mapAjax.put("result", "logout");
					}else if(user!=null && 
					  user.getMem_num()==db_rereply.getMem_num()) {
						//로그인이 되어 있고 
						//로그인한 회원번호와 작성자 회원번호 일치
						
						//댓글 샂게
						clubService.deleteRereply(rereply_num);
						
						mapAjax.put("result", "success");
					}else {
						//로그인한 회원번호와 작성자 회원번호 불일치
						mapAjax.put("result", "wrongAccess");
					}
					return mapAjax;
				}
				
}
