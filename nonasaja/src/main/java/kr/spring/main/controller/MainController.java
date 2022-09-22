package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.order.service.OrderService;
import kr.spring.product.controller.ProductController;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.service.ReviewService;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/")
	public String page() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView(); 
		Map<String,Object> map = new HashMap<String,Object>();
		
		//사용자 페이지에서 status가 2(1이상)인 상품만 노출
		map.put("status", 1);
		
		int count = productService.selectProductCount(map);
		logger.debug("<<메인 count>> : " +count);
		
		List<ProductVO> list  = null;
		if(count > 0 ) {
			map.put("start", 1);
			map.put("end", 8);
			list = productService.selectProductList(map);
			logger.debug("<<메인 list>> : " +list);
			for(ProductVO product : list) {
				product.setScoreAvg(reviewService.selectScore(product.getProduct_num()));
				product.setReviewCount(reviewService.selectReviewCount(product.getProduct_num()));
				product.setWaitCount(orderService.selectWaitCount(product.getProduct_num()));
			}
		}

		mav.setViewName("main");
		mav.addObject("count", count);
		mav.addObject("list", list);
		
		return mav;
	}
	
}




