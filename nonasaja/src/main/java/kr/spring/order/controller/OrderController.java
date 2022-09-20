package kr.spring.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.kakaopay.service.KakaopayService;
import kr.spring.member.vo.LionPointVO;
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
	
	@Autowired
	private KakaopayService kakaopayService;
	
	//주문하기 폼으로
	@RequestMapping("/order/cart_order.do")
	public ModelAndView form(HttpServletRequest request ,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//한번 주문하는 cart 의 list
		List<CartVO> cartList = new ArrayList<CartVO>();
		//체크박스 값(cart_num) 받아서 int 배열로 바꾸기
		String[] cartNum_arr=request.getParameterValues("cart_num");
		int[] cart_num= new int[cartNum_arr.length];
		
		//주문의 전체 가격
		int all_total = 0; 
			
		//주문 신청한 카트 번호로 반복문 시작
		for(int i=0;i<cartNum_arr.length;i++) {
			cart_num[i]=Integer.parseInt(cartNum_arr[i]);
			
			CartVO cart_order = cartService.selectCartByCartNum(cart_num[i]);
			
			//상품의 주문 대기량 구하기
			ProductVO product = productService.selectProduct(cart_order.getProduct_num());
			logger.debug("<<product>> : " +product);
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
	public ModelAndView order(@RequestParam String receive_name, 
					@RequestParam String receive_post,@RequestParam String receive_address1,
					@RequestParam String receive_address2,@RequestParam String receive_phone,
					@RequestParam(value="notice", required=false) String notice,
					@RequestParam int all_total,
					HttpServletRequest request,
					HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		//체크박스 값(cart_num) 받아서 int 배열로 바꾸기
		String[] cartNum_arr=request.getParameterValues("cart_num");
		int[] cart_num= new int[cartNum_arr.length];
		
		
		
		//주문이 확정된 cartList만들기
		List<CartVO> cartList = new ArrayList<CartVO>();
		for(int i=0;i<cartNum_arr.length;i++) {
			logger.debug("<<카트번호 배열>> : " +cartNum_arr[i]);
			cart_num[i]=Integer.parseInt(cartNum_arr[i]);
			CartVO cart_order = cartService.selectCartByCartNum(cart_num[i]);
			cart_order.setProductVO(productService.selectProduct(cart_order.getProduct_num()));
			cartList.add(cart_order);
		}
		
		logger.debug("<<카트리스트>> : " + cartList);
		
		//orderVO에 넣을 orderName 만들기
		String order_name = null;

		if(cartList.size() == 1) {
			order_name = cartList.get(0).getProductVO().getName();
		} else { //피규어외 2건
			order_name= cartList.get(0).getProductVO().getName()+"외 "+(cartList.size()-1)+"건";
		}
		
		//orderVO 만들기
		OrderVO order = new OrderVO();
		MemberVO user = (MemberVO)session.getAttribute("user");
		order.setMem_num(user.getMem_num());
		order.setReceive_address1(receive_address1);
		order.setReceive_address2(receive_address2);
		order.setReceive_name(receive_name);
		order.setReceive_phone(receive_phone);
		order.setReceive_post(receive_post);
		order.setTotal_name(order_name);
		order.setOrder_total(all_total);
		if(notice != null) {
			order.setNotice(notice);
		}
		//배송대기상태
		order.setStatus(1);
		//cartList의 내용을 orderDetailList로 만들기
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetail = null;
		
		for(CartVO cart : cartList) {
			orderDetail = new OrderDetailVO();
			orderDetail.setOrder_quantity(cart.getQuantity());
			orderDetail.setProduct_name(cart.getProductVO().getName());
			orderDetail.setProduct_num(cart.getProduct_num());
			orderDetail.setProduct_price(cart.getProductVO().getPrice2());
			orderDetail.setProduct_total(cart.getSub_total());
			//!!--공동구매 확인하고 결정할 것 --!!
			//1. 현 상품의 waitCount 확인하기
			int waitCount = orderService.selectWaitCount(cart.getProduct_num());
			logger.debug("<<cart>> : " + cart.getProduct_num());
			logger.debug("<<waitCount>> : " + waitCount);
			
			//2. 주문량이 req-waitCount보다 많은 경우 > 구매 가능 수량 안내 후 카트로 이동 
			if(orderDetail.getOrder_quantity() > (cart.getProductVO().getReq_quantity()-waitCount)) {
				mav.addObject("message", 
						"현재 구매대기 수량보다 주문량이 많습니다. [" + cart.getProductVO().getName() +"] 상품을 " 
						+(cart.getProductVO().getReq_quantity()-waitCount) +"개 이하로 주문해주세요.");
				mav.addObject("url", request.getContextPath() + "/cart/cart_list.do");
				mav.setViewName(request.getContextPath() +"/common/resultView");
				return mav;
			} else if(orderDetail.getOrder_quantity() < (cart.getProductVO().getReq_quantity()-waitCount)) {
				//3. 주문량이 req-waitCount보다 적은 경우 > wait_status 1(구매대기) 상태로 설정
				orderDetail.setWait_status(1);
				mav.addObject("message", "주문이 완료되었습니다. MyPage에서 구매 대기 상태를 확인하세요.");
			} else if(orderDetail.getOrder_quantity() == (cart.getProductVO().getReq_quantity()-waitCount)) {
				//4. 주문량이 주문량이 req-waitCount와 딱 맞을 때 > 일괄 주문 메소드 실행 // status는 2로
				orderPayment(cart.getProductVO().getProduct_num(), cart.getMem_num(), orderDetail.getProduct_total());
				orderDetail.setWait_status(2);
				mav.addObject("message", "주문이 완료되었습니다. 구매대기 수량이 충족되어 바로 결제되었습니다.");
			}
			
			orderDetailList.add(orderDetail);
			//cart에서 삭제 
			cartService.deleteCart(cart.getCart_num());
			//product 재고 수정
			productService.updateProductQuantity(cart.getProduct_num(), cart.getQuantity());
		}

		//order와 orderDetail insert
		orderService.insertOrder(order, orderDetailList);

		logger.debug("<<주문등록>> : " + order);
	
		mav.addObject("url", request.getContextPath() + "/product/list.do");
		mav.setViewName(request.getContextPath() +"/common/resultView");
		return mav;
	}
	
	//일괄 결제 진행
	public void orderPayment(int product_num, int current_mem, int product_total) {
		//대기 진행중인 목록 orderDetail 목록 불러오기
		List<OrderDetailVO> waitList = orderService.selectWaitList(product_num);
		for(OrderDetailVO orderDetail : waitList) {
			//주문 진행중인 상품을 구매한 mem_num 구하기
			OrderVO order = orderService.selectOrder(orderDetail.getOrder_num());
			int mem_num = order.getMem_num();
			int price = orderDetail.getProduct_total();
			//#{mem_num},#{lionpoint}충전금액,#{cash}거래전,#{remain}거래후,#{order_num}
			LionPointVO lionPoint = new LionPointVO();
			lionPoint.setLionpoint(-price);
			LionPointVO lionPoint_db = kakaopayService.selectPointbyMem(mem_num);
			logger.debug("<<lionPoint_db>> : " + lionPoint_db);
			int cash = lionPoint_db.getRemain();
			lionPoint.setCash(cash);
			lionPoint.setRemain(cash-price);
			lionPoint.setMem_num(lionPoint_db.getMem_num());
			lionPoint.setOrder_num(orderDetail.getOrder_num());
			
			logger.debug("<<lionPoint>> : " + lionPoint);
			
			//결제 대상인 member의 포인트 삭감
			kakaopayService.insertPoint(lionPoint);
			
			//결제된 멤버 member_detail 업데이트
			MemberVO member = new MemberVO();
			member.setCash(lionPoint.getRemain());
			member.setMem_num(mem_num);
			kakaopayService.updateMemberCash(member);
			
			//orderDetail wait_status 2로 수정
			orderService.updateWaitStatus(orderDetail.getDetail_num(), 2);
		}
		//현재 orderForm을 쓴 회원 정보 처리
		//포인트 삭감
		LionPointVO lionPoint = new LionPointVO();
		lionPoint.setLionpoint(product_total);
		int cash = kakaopayService.selectPointbyMem(current_mem).getRemain();
		lionPoint.setCash(cash);
		lionPoint.setRemain(cash-product_total);
		lionPoint.setMem_num(current_mem);
		kakaopayService.insertPoint(lionPoint);
		
		// member_detail 업데이트
		MemberVO member = new MemberVO();
		member.setCash(lionPoint.getRemain());
		member.setMem_num(current_mem);
		kakaopayService.updateMemberCash(member);
	}
}



















