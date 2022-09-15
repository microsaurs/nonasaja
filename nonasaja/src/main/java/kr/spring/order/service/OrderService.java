package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import kr.spring.order.vo.OrderVO;

public interface OrderService {
	public void insertOrder(OrderVO order);

	// 주문 상세 등록
	public void insertOrder_datail(OrderVO order);

	// 주문 수정

	// 회원별 주문 목록의 수
	public int selectOrderCount(Map<String, Object> map);

	// 회원별 주문 목록
	public List<OrderVO> selectOrderList(Map<String, Object> map);

	// 주문 상세
}
