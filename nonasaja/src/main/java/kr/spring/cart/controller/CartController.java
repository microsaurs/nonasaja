package kr.spring.cart.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.product.controller.ProductController;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.util.PagingUtil;

@Controller
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@ModelAttribute
	public CartVO initCommand() {
		return new CartVO();
	}

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@RequestMapping("/cart/cart_insert.do")
	public String submit(@RequestParam int product_num, @RequestParam int product_price2,
			@RequestParam int product_quantity, @RequestParam int product_req_quantity,
			@RequestParam int order_quantity,
			HttpServletRequest request, HttpSession session, Model model) {
		//로그인 인터셉터에 넣기
		
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		CartVO cartVO = new CartVO();
		//상품 정보 담기
		ProductVO productVO = productService.selectProduct(product_num);
		
		//카트 정보 담기
		cartVO.setMem_num(user.getMem_num());
		cartVO.setProduct_num(product_num);
		cartVO.setQuantity(order_quantity); //주문 수량
		cartVO.setStatus(1); // 장바구니에 담는 status
		int total = order_quantity * product_price2; //이번 주문의 총 금액
		cartVO.setSub_total(total);
		cartVO.setProductVO(productVO);
		logger.debug("<<product_num>> : " + product_num);
		logger.debug("<<mem_num>> : " + user.getMem_num());
		
		//해당 고객이 이미 주문한 상품인지 확인
		CartVO cartProduct = cartService.selectCart(cartVO);
		if(cartProduct == null) { //주문한 적 없는 경우
			cartService.insertCart(cartVO);
			model.addAttribute("message", "상품을 장바구니에 추가했습니다.");
			model.addAttribute("url", request.getContextPath() + "/product/list.do");
		} else { //주문한 적 있는 경우
			if((cartProduct.getQuantity()+order_quantity) > product_quantity) {//이전주문 + 새 주문이 재고수보다 많은 경우 
				model.addAttribute("message", "상품의 재고가 부족합니다.");
				model.addAttribute("url", request.getContextPath() + "/product/detail.do?product_num=" +product_num);
			} else {
				//이전에 주문한 수량+새로 주문한 수량을 cartVO에 넣기
				cartVO.setQuantity(cartVO.getQuantity()+cartProduct.getQuantity());
				cartService.updateCart(cartVO);
				model.addAttribute("message", "상품을 장바구니에 추가했습니다.");
				model.addAttribute("url", request.getContextPath() + "/product/list.do");
			}
		}
		logger.debug("<<장바구니 추가 cartVO>> : " + cartVO);
		logger.debug("<<기존 장바구니 확인 cartProduct>> : " + cartProduct);
		logger.debug("<<장바구니 추가 product>> : " + productVO);

		return "common/resultView";
	}
	
	@RequestMapping("/cart/cart_list.do")
	public ModelAndView cartList( @RequestParam int mem_num,
			@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield", defaultValue="") String keyfield, 
			@RequestParam(value="keyword", defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//주문의 총 개수(장바구니에 담긴 주문 개수)
		int count = cartService.selectRowCount(mem_num);
		logger.debug("<<count>> : " +count);
		
		//rowCount, pageCount는 맨 위에 설정함
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"list.do");
		
		List<CartVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = cartService.selectListCart(mem_num);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cartList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
}























