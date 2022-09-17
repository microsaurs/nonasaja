package kr.spring.cart.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.cart.vo.CartVO;

public interface CartService {
	// 장바구니에 상품 담기
	public void insertCart(CartVO cart);

	// 회원별(mem_num) 총구매액
	public int selectTotalByMem_num(int mem_num);

	// 장바구니 목록
	public List<CartVO> selectListCart(Map<String, Object> map);

	// 장바구니 상세
	public CartVO selectCart(CartVO cart);
	
	//장바구니 상세(카트번호로)
	public CartVO selectCartByCartNum(int cart_num);

	// 장바구니 수정 (개별 상품 수량 수정)
	public void updateCart(CartVO cart);

	// 장바구니 삭제
	public void deleteCart(int cart_num);
}
