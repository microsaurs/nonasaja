package kr.spring.join.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.cart.vo.CartVO;
import kr.spring.club.service.ClubService;
import kr.spring.club.vo.ClubVO;
import kr.spring.join.controller.JoinController;
import kr.spring.join.service.JoinService;
import kr.spring.join.vo.JoinVO;
import kr.spring.member.vo.MemberVO;
@Controller
public class JoinController {
	private static final Logger logger = 
			LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private ClubService clubService;
	
	//동호회 가입
	@RequestMapping("/join/write.do")
	@ResponseBody
	public Map<String,String> submit(JoinVO joinVO,
			                 HttpSession session){
		logger.debug("<<JoinVO>> : " + joinVO);
		
		Map<String,String> mapAjax = 
				      new HashMap<String,String>();
		MemberVO user = 
			(MemberVO)session.getAttribute("user");
		if(user==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else { //로그인 된 경우
			//가입신청한 동호회의 정보 호출
			ClubVO club = clubService.selectBoard(joinVO.getClub_num());
			logger.debug("<<club>> : " + club);
			
			//동호회가 모집 마감인지 확인
			if(club.getClub_recruit() == 1) {
				//모집 마감인경우 overmember로 반환
				mapAjax.put("result","overmember");
				return mapAjax;
			}
			
			joinVO.setMem_num(user.getMem_num());
			
			//기존에 가입한 동호회 확인
			List<JoinVO> joinList = joinService.selectListJoin(user.getMem_num());
			if(joinList.size() > 0) {//이미 가입한 동호회 있음
				//해당 동호회 회원인지 확인 
				for(JoinVO join:joinList) {
					if(join.getClub_num() == joinVO.getClub_num()) {
						//이미 회원이면 already 반환
						mapAjax.put("result","already");
						return mapAjax;
					}
				}
			}
			
			//해당 동호회에 신청중인 사람 수 구하기
			int joinCount = joinService.selectJoinCount(joinVO.getClub_num());
			
			//총인원수가 이미 다 찼을 경우 overmember 반환
			if(joinCount == club.getClub_limit()) {
				mapAjax.put("result","overmember");
			}else { //인원수가 다 차지 않은 경우 가입 처리 후 success 반환
				joinService.insertJoin(joinVO);
				mapAjax.put("result", "success");
				
				//모집인원을 다 채운경우 recruit를 모집마감(1)로 변경
				if((joinCount + 1) == club.getClub_limit()) {
					club.setClub_recruit(1);
					clubService.updateBoard(club);
				}
			}
		}
		return mapAjax;
	}
	
	//동호회 삭제
		@RequestMapping("/join/deleteJoin.do")
		@ResponseBody
		public Map<String,String> delete(
				           @RequestParam int join_num,
				              HttpSession session){
			
			Map<String,String> mapAjax = 
					new HashMap<String,String>();
			
			MemberVO user = 
				 (MemberVO)session.getAttribute("user");
			if(user==null) {
				mapAjax.put("result","logout");
			}else {
				joinService.deleteJoin(join_num);
				mapAjax.put("result", "success");
			}
			
			return mapAjax;
		}
		
}
	

