package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CartVO;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void insertCart(CartVO cart) {
		cartMapper.insertCart(cart);
	}

	@Override
	public int selectTotalByMem_num(int mem_num) {
		return cartMapper.selectTotalByMem_num(mem_num);
	}

	@Override
	public List<CartVO> selectListCart(Map<String, Object> map) {
		return cartMapper.selectListCart(map);
	}

	@Override
	public CartVO selectCart(CartVO cart) {
		return cartMapper.selectCart(cart);
	}

	@Override
	public void updateCart(CartVO cart) {
		cartMapper.updateCart(cart);
	}

	@Override
	public void deleteCart(int cart_num) {
		cartMapper.deleteCart(cart_num);
	}
	@Override
	public int selectRowCount(Map <String, Object> map) {
		return cartMapper.selectRowCount(map);
	}

	@Override
	public CartVO selectCartByCartNum(int cart_num) {
		return cartMapper.selectCartByCartNum(cart_num);
	}

}
