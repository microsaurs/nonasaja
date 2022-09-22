package kr.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberServiceCenter {
	
	@RequestMapping("/member/serviceCenter.do")
	public String showServiceCenter(@RequestParam(defaultValue="1")int type) {
		
		return "serviceCenter";
	}
}
