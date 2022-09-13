package kr.spring.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderVO;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;

@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@ModelAttribute
	public OrderVO initCommand() {
		return new OrderVO();
	}
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/cart/cart_order.do")
	public String insertOrder(HttpServletRequest request ,HttpSession session) {

		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//체크박스 값(cart_num) 받아서 int 배열로 바꾸기
		String[] cartNum_arr=request.getParameterValues("cart_num");
		int[] cart_num= new int[cartNum_arr.length];

		//주문 신청한 카트 번호로 반복문 시작
		for(int i=0;i<cartNum_arr.length;i++) {
			cart_num[i]=Integer.parseInt(cartNum_arr[i]);
			
			CartVO cart_order = cartService.selectCartByCartNum(cart_num[i]);
			
			//상품의 주문 대기량 구하기
			ProductVO product = productService.selectProduct(cart_order.getProduct_num());
			int count = cartService.selectWait(product.getProduct_num());
			
			logger.debug("<<주문 대기로 이동>> count: " +count);
			logger.debug("<<주문 대기로 이동>> product.req_quantity: " +product.getReq_quantity());
			
			// 해당 주문 상품의 주문 대기량이 req_quantity 충족인지/넘기는지 확인
			if(count < product.getReq_quantity()) {
				// 모자라는 경우 : cart에서 status 2 로 변경
				cartService.updateCartWait(cart_num[i],2);
			} else if(count > product.getReq_quantity()){		
				// 넘기는 경우 : 경고문 보내고 주문 수량 수정하게 하기
			} else if(count == product.getReq_quantity()) {
				// 딱 충족하는 경우 : 바로 결제 진행 > 결제 완료로 보내기
			}
		}

		return "";
	}

}
