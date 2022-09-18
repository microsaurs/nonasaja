package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	public void insertOrder(OrderVO order);

	// 주문 상세 등록
	public void insertOrder_datail(OrderVO order);

	//상품별 결제 대기 수
	public int selectWaitCount(int product_num);
	
	// 주문 수정

	// 회원별 주문 목록의 수
	public int selectOrderCount(Map<String, Object> map);

	//회원별 주문 대기 주문 가격 합
	public int selectSumWait(int mem_num);
	
	// 회원별 주문 목록
	public List<OrderVO> selectOrderList(Map<String, Object> map);
	
	// 회원별 주문 목록
	public List<OrderDetailVO> selectOrderDetailList(Map<String, Object> map);

	// 주문 상세
}
