package kr.spring.cart.service;

import java.util.List;

import kr.spring.cart.vo.CartVO;

public interface CartService {
	// 장바구니에 상품 담기
	public void insertCart(CartVO cart);

	// 회원별(mem_num) 총구매액
	public int selectTotalByMem_num(int mem_num);

	//장바구니 주문 수
	public int selectRowCount(int mem_num);
	
	// 장바구니 목록
	public List<CartVO> selectListCart(int mem_num);

	// 장바구니 상세
	public CartVO selectCart(CartVO cart);

	// 장바구니 수정 (개별 상품 수량 수정)
	public void updateCart(CartVO cart);

	// 장바구니 수정(상품번호와 회원번호별 상품 수량 수정)
	public void updateCartByItem_num(CartVO cart);

	// 장바구니 삭제
	public void deleteCart(int cart_num);
}
