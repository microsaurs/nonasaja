package kr.spring.cart.service;

import java.util.List;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartVO> selectListCart(int mem_num) {
		// TODO Auto-generated method stub
		return null;
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
	public void updateCartByItem_num(CartVO cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCart(int cart_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectRowCount(int mem_num) {
		return cartMapper.selectRowCount(mem_num);
	}

}
