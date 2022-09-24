package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	//주문하기
	@Override
	public void insertOrder(OrderVO order, List<OrderDetailVO> orderDetailList) {
		int order_num = orderMapper.selectOrder_num();
		order.setOrder_num(order_num);
		orderMapper.insertOrder(order);
		for(OrderDetailVO orderDetail : orderDetailList) {
			orderDetail.setOrder_num(order_num);
			orderMapper.insertOrder_datail(orderDetail);
		}
	}

	@Override
	public int selectOrderCount(Map<String, Object> map) {
		return orderMapper.selectOrderCount(map);
	}

	@Override
	public List<OrderVO> selectOrderList(Map<String, Object> map) {
		return orderMapper.selectOrderList(map);
	}
	
	@Override
	public List<OrderDetailVO> selectOrderDetailList(Map<String, Object> map) {
		return orderMapper.selectOrderDetailList(map);
	}

	@Override
	public int selectWaitCount(int product_num) {
		return orderMapper.selectWaitCount(product_num);
	}

	@Override
	public int selectSumWait(int mem_num) {
		return orderMapper.selectSumWait(mem_num);
	}

	@Override
	public List<OrderDetailVO> selectWaitList(int product_num) {
		return orderMapper.selectWaitList(product_num);
	}

	@Override
	public OrderVO selectOrder(int order_num) {
		return orderMapper.selectOrder(order_num);
	}

	@Override
	public void updateWaitStatus(int detail_num, int wait_status) {
		orderMapper.updateWaitStatus(detail_num, wait_status);
	}

	@Override
	public void updateOrder(OrderVO order) {
		orderMapper.updateOrder(order);
	}

	@Override
	public void deleteWait(int detail_num) {
		orderMapper.deleteWait(detail_num);
	}

	@Override
	public void deleteOrder(int order_num) {
		orderMapper.deleteOrder(order_num);
	}

	@Override
	public int selectOrderNumByDetailNum(int detail_num) {
		return orderMapper.selectOrderNumByDetailNum(detail_num);
	}

	@Override
	public List<OrderDetailVO> selectOrderDetailByOrderNum(int order_num) {
		return orderMapper.selectOrderDetailByOrderNum(order_num);
	}

}
