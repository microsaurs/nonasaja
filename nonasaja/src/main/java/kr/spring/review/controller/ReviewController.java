package kr.spring.review.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;

@Controller
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@ModelAttribute
	public ReviewVO initCommand() {
		return new ReviewVO();
	}

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ProductService productService;

	// ================리뷰 등록======================
	// 등록 폼
	@GetMapping("/review/write_review.do")
	public ModelAndView form(@RequestParam int product_num) {
		//로그인 인터셉터 넣기
		ModelAndView mav = new ModelAndView();
		ProductVO product = productService.selectProduct(product_num);
		logger.debug("<<리뷰 폼 호출>> : " +product);
		mav.addObject("product", product);
		mav.setViewName("review_form");
		return mav;
	}

	// 리뷰 등록
	@PostMapping("/review/write_review.do")
	public String submit(@Valid ReviewVO reviewVO, 
			BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		logger.debug("<<리뷰등록>> : " + reviewVO);
		
		if (result.hasErrors()) {
			return "/review/write_review.do?product_num=" + reviewVO.getProduct_num();
		}
		MemberVO user = (MemberVO)session.getAttribute("user");
		reviewVO.setMem_num(user.getMem_num());
		
		reviewService.insertReview(reviewVO);

		model.addAttribute("message", "리뷰 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/order/order_list.do");

		// 알림 페이지 만들기
		return "common/resultView";
	}
}
