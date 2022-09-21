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
			joinVO.setMem_num(user.getMem_num());
			
			//기존에 등록된 동일 상품이 있는지 체크
			JoinVO db_join = joinService.selectJoin(joinVO);
			if(db_join==null) {//이미 가입한 동호회 없음
				joinService.insertJoin(joinVO);
				mapAjax.put("result", "success");
			}else {//이미 가입한 동호회 있음 
				//인원수를 구하기 위해서 정보 호출
				ClubVO club = clubService.selectBoard( db_join.getClub_num());
				
				//총인원수가 이미 다 찼을 경우
				if(club.getClub_pre() == club.getClub_limit()) {
					mapAjax.put("result","overmember");
				}else {
					mapAjax.put("result", "success");
				}
			}
		}
		
		return mapAjax;
	}
	
	//동호회 목록보기
	@RequestMapping("/join/list.do")
	public String list(HttpSession session,
			           Model model) {
		MemberVO user = 
			  (MemberVO)session.getAttribute("user");
	
		List<JoinVO> list = null;
		list = joinService.selectListJoin(user.getMem_num());

		model.addAttribute("list", list);
		
		return "joinList";
	}
}
	

