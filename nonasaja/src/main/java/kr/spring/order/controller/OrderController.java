package kr.spring.order.controller;

import java.util.ArrayList;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.util.PagingUtil;

@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@ModelAttribute
	public OrderVO initCommand() {
		return new OrderVO();
	}
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	//주문하기 폼으로
	@RequestMapping("/order/cart_order.do")
	public ModelAndView form(HttpServletRequest request ,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//체크박스 값(cart_num) 받아서 int 배열로 바꾸기
		String[] cartNum_arr=request.getParameterValues("cart_num");
		int[] cart_num= new int[cartNum_arr.length];
		
		//주문의 전체 가격
		int all_total = 0; 
			
		
		//한번 주문하는 cart 의 list
		List<CartVO> cartList = new ArrayList<CartVO>();
		
		//주문 신청한 카트 번호로 반복문 시작
		for(int i=0;i<cartNum_arr.length;i++) {
			cart_num[i]=Integer.parseInt(cartNum_arr[i]);
			
			CartVO cart_order = cartService.selectCartByCartNum(cart_num[i]);
			
			//상품의 주문 대기량 구하기
			ProductVO product = productService.selectProduct(cart_order.getProduct_num());
			int waitCount = orderService.selectWaitCount(product.getProduct_num());
			
			logger.debug("<<주문 요청>> waitCount: " +waitCount);
			logger.debug("<<주문 요청>> product.req_quantity: " +product.getReq_quantity());
			logger.debug("<<sub_total>> : " +cart_order.getSub_total());
			cart_order.setProductVO(product);
			all_total += cart_order.getSub_total();
			// 남은 req_quantity보다 많은 주문인 경우 || 판매 중지된 상품인 경우 안내문과 함께 돌려보내기
			if(product.getReq_quantity() < cart_order.getQuantity() +waitCount){		
				mav.addObject("message","현재 구매대기 수량보다 주문량이 많습니다. [" +product.getName() +"] 상품을 " +(product.getReq_quantity()-waitCount) +"개 이하로 주문해주세요.");
				mav.addObject("url", request.getContextPath() +"/cart/cart_list.do");
				mav.setViewName(request.getContextPath() +"/common/resultView");
				return mav;
			} else if(product.getStatus() == 1) {
				// 판매 중지된 상품인 경우
				mav.addObject("message","[" +product.getName() +"] 상품이 판매중지되었습니다.");
				mav.addObject("url", request.getContextPath() +"/cart/cart_list.do");
				mav.setViewName(request.getContextPath() +"/common/resultView");
				return mav;
			} else if(user.getCash() < (all_total+orderService.selectSumWait(user.getMem_num()))) {
				// 주문 대기 상태의 총 가격의 합이 현재 포인트보다 많을때(지불능력이 없는 경우)
				mav.addObject("message","현재 보유하신 포인트 이상으로 구매신청을 하실 수 없습니다. 현재 포인트 :" 
						+user.getCash() +" 현재 구매대기 중인 주문 가격 : " 
						+orderService.selectSumWait(user.getMem_num()));
				mav.addObject("url", request.getContextPath() +"/cart/cart_list.do");
				mav.setViewName(request.getContextPath() +"/common/resultView");
				return mav;
			}
			cartList.add(cart_order);
		}
		
		logger.debug("<<주문 폼으로 이동>> cartList: " +cartList);
		mav.addObject("all_total", all_total);
		mav.addObject("cartList", cartList);
		mav.setViewName("order_form");
		return mav;
	}
	
	@RequestMapping("/order/order_list.do")
	public ModelAndView orderList(HttpSession session,
			@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield", defaultValue="") String keyfield, 
			@RequestParam(value="keyword", defaultValue="") String keyword){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		MemberVO user = (MemberVO) session.getAttribute("user");
		map.put("mem_num", user.getMem_num());
		
		//주문의 총 개수
		int count = orderService.selectOrderCount(map);
		logger.debug("<<count>> : " +count);
		
		//rowCount, pageCount는 맨 위에 설정함
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"list.do");
		List<OrderVO> orderList = null;
		List<OrderDetailVO> orderDetailList = null;
		
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			orderList = orderService.selectOrderList(map);
			orderDetailList = orderService.selectOrderDetailList(map);
		}
		ModelAndView mav = new ModelAndView();
		logger.debug("<<orderList>> : " +orderList);
		logger.debug("<<orderDetailList>> : " +orderDetailList);
		mav.setViewName("orderList");
		mav.addObject("count", count);
		mav.addObject("orderList", orderList);
		mav.addObject("orderDetailList", orderDetailList);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	@PostMapping("/order/order.do")
	public ModelAndView order(@Valid OrderVO orderVO,
					BindingResult result, 
					HttpServletRequest request,
					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		logger.debug("<<주문등록>> : " + orderVO);
		if (result.hasErrors()) {
			mav.setViewName("order_form");
			return mav;
		}
		
		mav.addObject("message", "상품 등록이 완료되었습니다.");
		mav.addObject("url", request.getContextPath() + "/product/list.do");
		
		return mav;
	}
	
}



















