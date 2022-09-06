package kr.spring.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/")
	public String page() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do")
	public String main() {
		       //타일스 설정 식별자
		return "main";
	}
	
}




