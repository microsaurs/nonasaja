package kr.spring.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;


@Controller
public class ProductController {
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductController.class);
	
	private int rowCount = 20;
	private int pageCount = 10;
	
	@ModelAttribute
	public ProductVO initCommand() {
		return new ProductVO();
	}
	
	@Autowired
	private ProductService productService;
	
	//상품 리스트
	@GetMapping("/product/list.do")
	public String list() {
		return "productList";
	}
	
	
	//================상품 글 등록======================
	//등록 폼
	@GetMapping("/product/register.do")
	public String form() {
		return "productRegister";
	}
		
	//상품 등록
	@PostMapping("/product/register.do")
	public String submit(@Valid ProductVO productVO, BindingResult result, 
						HttpServletRequest request, HttpSession session, Model model) {
		
		if(result.hasErrors()) {
			return form();
		}
		
		productService.insertProduct(productVO);
		
		model.addAttribute("message", "상품 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/product/list.do");
		
		//알림 페이지 만들기
		return "common/resultView";
	}

}
























