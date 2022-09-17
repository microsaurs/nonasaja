package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	//주문하기
	@Override
	public void insertOrder(OrderVO order) {
		orderMapper.insertOrder(order);
		orderMapper.insertOrder_datail(order);
	}

	@Override
	public void insertOrder_datail(OrderVO order) {
		orderMapper.insertOrder_datail(order);
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
	public int selectWaitCount(int product_num) {
		return orderMapper.selectWaitCount(product_num);
	}

	@Override
	public int selectSumWait(int mem_num) {
		return orderMapper.selectSumWait(mem_num);
	}
	
}
