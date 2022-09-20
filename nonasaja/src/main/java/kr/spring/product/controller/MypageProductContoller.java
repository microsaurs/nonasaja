package kr.spring.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.PagingUtil;

@Controller
public class MypageProductContoller {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("/member/myPageProduct.do")
	public ModelAndView listProductPage(HttpSession session, 
			@RequestParam(value="type",defaultValue="1") int type,
			@RequestParam(value="pageNum",defaultValue = "1")int currentPage,
			@RequestParam(value="keyfield", defaultValue="") String keyfield, 
			@RequestParam(value="keyword", defaultValue="") String keyword) {
		
		ModelAndView mav = new ModelAndView();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//장바구니
		if(type == 1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);;
			map.put("mem_num", user.getMem_num());
			
			//회원번호별 총구매액
			int all_total = cartService.selectTotalByMem_num(
									           user.getMem_num());
			List<CartVO> list = null;
			if(all_total > 0) {
				list = cartService.selectListCart(map);
				for(CartVO cart : list) {
					cart.setProductVO(productService.selectProduct(cart.getProduct_num()));
				}
			}
			mav.addObject("list", list);
			mav.addObject("all_total", all_total);
			logger.debug("<<장바구니 목록>> list: " +list);
		}
		
		//주문
		if(type==2 || type==3) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			map.put("mem_num", user.getMem_num());
			if(type==2) {
				map.put("wait_status", 1);
			} else {
				map.put("wait_status", 2);
			}
			//주문의 총 개수
			int count = orderService.selectOrderCount(map);
			logger.debug("<<count>> : " +count);
			
			PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,10,10,"/member/myPageProduct.do");
			List<OrderVO> orderList = null;
			List<OrderDetailVO> orderDetailList = null;
			
			if(count > 0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				orderList = orderService.selectOrderList(map);
				orderDetailList = orderService.selectOrderDetailList(map);
				ProductVO product = null;
				if(type==2) {
					for(OrderDetailVO detail : orderDetailList) {
						product = productService.selectProduct(detail.getProduct_num());
						detail.setWait_count(orderService.selectWaitCount(product.getProduct_num()));
						detail.setReq_quantity(product.getReq_quantity());
					}
				}
			}
			logger.debug("<<orderList>> : " +orderList);
			logger.debug("<<orderDetailList>> : " +orderDetailList);
			mav.addObject("count", count);
			mav.addObject("orderList", orderList);
			mav.addObject("orderDetailList", orderDetailList);
			mav.addObject("page", page.getPage());
		}
		
		//리뷰
		if(type==4) {
			List<ReviewVO> reviewList = reviewService.selectReviewListByMem(user.getMem_num());
			for(ReviewVO review:reviewList) {
				logger.debug("<<review>> : " +review);
				review.setProduct_name(productService.selectProduct(review.getProduct_num()).getName());
			}
			mav.addObject("reviewList", reviewList);
		}
		
		mav.setViewName("myPageProduct");
		mav.addObject("member", user);
		mav.addObject("type", type);
		
		return mav;
	}
}















