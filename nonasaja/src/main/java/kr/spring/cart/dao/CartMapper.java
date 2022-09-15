package kr.spring.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.cart.vo.CartVO;

@Mapper
public interface CartMapper {
	//장바구니에 상품 담기
	@Insert("insert into cart (cart_num,mem_num,product_num,"
			+ "quantity,reg_date,status)"
			+ "values(cart_seq.nextval,#{mem_num},#{product_num},"
			+ "#{quantity},sysdate,#{status})")
	public void insertCart(CartVO cart);
	
	//회원별(mem_num) 총구매액
	@Select("select NVL(SUM(sub_total), 0) FROM "
			+ "(SELECT c.mem_num,c.quantity*p.price2 sub_total "
			+ "FROM cart c JOIN product p ON c.product_num=p.product_num "
			+ "WHERE mem_num = #{mem_num})")
	public int selectTotalByMem_num(int mem_num);
	//장바구니 주문 수 
	public int selectRowCount(Map<String, Object> map);
	//장바구니 목록
	public List<CartVO> selectListCart(Map<String, Object> map);
	//장바구니 상세(회원번호와 상품번호로)
	@Select("select * from cart where mem_num=#{mem_num} and product_num=#{product_num}")
	public CartVO selectCart(CartVO cart);
	//장바구니 상세(카트번호로)
	@Select("select * from cart where cart_num=#{cart_num}")
	public CartVO selectCartByCartNum(int cart_num);
	//장바구니 수정 (개별 상품 수량 수정)
	@Update("update cart set quantity=#{quantity} "
			+ "where mem_num=#{mem_num} and product_num=#{product_num}")
	public void updateCart(CartVO cart);
	//장바구니 수정(상품번호와 회원번호별 상품 수량 || status 수정)
	@Update("update cart set status=#{status} where cart_num=#{cart_num}")
	public void updateCartWait(int cart_num, int status);
	//상품의 주문 대기 수 확인 
	@Select("select count(*) from cart "
			+ "where product_num=#{product_num} "
			+ "and status = 2")
	public int selectWait(int product_num);
	//장바구니 삭제
	@Delete("delete from cart where cart_num=#{cart_num}")
	public void deleteCart(int cart_num);	
}
