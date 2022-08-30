package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class MemberAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(MemberAjaxController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/confirmId.do")
	@ResponseBody //List, Map, VO인 경우만 키-값 형태로 json 문자화
	public Map<String,String> process(@RequestParam String id){
		logger.debug("<<id>> : " + id);
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		MemberVO member = memberService.selectCheckMember(id);

		if(member!=null) {
			//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			/*
			 * ^ : 표현식 시작
			 * $ : 표현식 끝
			 */
								// 패턴
			if(!Pattern.matches("^[A-Za-z0-9]{4,20}$", id)) {
				//패턴이 불일치하는 경우
				mapAjax.put("result", "notMatchPattern");
			}else {
				//패턴 일치하면서 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}
		
		return mapAjax;
	}
	
	//프로필 사진 수정
//	@RequestMapping("/member/updateMyPhoto.do")
//	@ResponseBody
//	public Map<String,String> processProfile(MemberVO memberVO, HttpSession session){
//		Map<String,String> mapAjax = new HashMap<String,String>();
//		
//		MemberVO user = (MemberVO)session.getAttribute("user");
//		if(user==null) {
//			mapAjax.put("result", "logout");
//		}else {
//			memberVO.setMem_num(user.getMem_num());
//			memberService.updateProfile(memberVO);
//			
//			mapAjax.put("result", "success");
//		}
//		
//		return mapAjax;
//	}
}
