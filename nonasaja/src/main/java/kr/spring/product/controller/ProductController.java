package kr.spring.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.order.service.OrderService;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.PagingUtil;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private int rowCount = 20;
	private int pageCount = 10;

	@ModelAttribute
	public ProductVO initCommand() {
		return new ProductVO();
	}

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private OrderService orderService;

	//  =============이미지 뷰==================
	@RequestMapping("/product/imageView.do")
	public ModelAndView viewImage(@RequestParam int product_num,
								@RequestParam int photo_type) {
		ProductVO productVO = productService.selectProduct(product_num);

		ModelAndView mav = new ModelAndView();

		// 이렇게 설정하면 1.tiles설정에서 찾고 없으면 2.bean 객체를 찾음
		// kr.spring.view에 ImageView.jsp에 @Component를 달아놔서 객체로 등록 됐음
		mav.setViewName("imageView");

		if (photo_type == 1) {
			mav.addObject("imageFile", productVO.getPhoto1());
			mav.addObject("filename", productVO.getPhoto1_name());
		} else if (photo_type == 2) {
			mav.addObject("imageFile", productVO.getPhoto2());
			mav.addObject("filename", productVO.getPhoto2_name());
		}else if (photo_type == 3) {
			mav.addObject("imageFile", productVO.getPhoto3());
			mav.addObject("filename", productVO.getPhoto3_name());
		}

		return mav;
	}
	
	// 상품 리스트 //
	@GetMapping("/product/list.do")
	public ModelAndView list(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
						@RequestParam(value="keyfield", defaultValue="") String keyfield,
						@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//사용자 페이지에서 status가 2인 상품만 노출
		map.put("status", 1);
		
		int count = productService.selectProductCount(map);
		logger.debug("<<count>> : " +count);
		
		PagingUtil page= new PagingUtil(keyfield, keyword,currentPage,count,rowCount,pageCount,"list.do");
		
		List<ProductVO> list  = null;
		if(count > 0 ) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = productService.selectProductList(map);
			for(ProductVO product : list) {
				product.setScoreAvg(reviewService.selectScore(product.getProduct_num()));
				product.setReviewCount(reviewService.selectReviewCount(product.getProduct_num()));
				product.setWaitCount(orderService.selectWaitCount(product.getProduct_num()));
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}

	
	//===============상품상세==================
		@RequestMapping("/product/detail.do")
		public String detail(@RequestParam int product_num,
							Model model,
							@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {
			logger.debug("<<product_num>> : " +product_num);
			ProductVO productVO = productService.selectProduct(product_num);
			int count = reviewService.selectReviewCount(product_num);
			PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "detail.do", null);
			List<ReviewVO> reviewList = null;
			int score = 0;
			if(count > 0) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				map.put("product_num", product_num);
				reviewList = reviewService.selectReviewList(map);
				score = reviewService.selectScore(product_num);
			}
			
			logger.debug("<<reviewList>> : " +reviewList);
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("page", page.getPage());
			model.addAttribute("count", count);
			model.addAttribute("product", productVO);
			model.addAttribute("score", score);
			return "productView";
		}
		
		//===============상품 삭제====================
		@RequestMapping("/product/delete.do")
		public String deleteProduct(int product_num, Model model) {
			logger.debug("<<상품삭제>> : " +product_num);
			//리뷰 삭제하기
			reviewService.deleteReviewByProduct_num(product_num);
			//상품 삭제하기 
			productService.deleteProduct(product_num);
			
			model.addAttribute("message", "상품 삭제 완료");
			model.addAttribute("url", "/product/admin_list.do");

			return "common/resultView";
		}
}






















