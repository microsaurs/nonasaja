package kr.spring.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.order.vo.OrderVO;

@Mapper
public interface OrderMapper {
	//주문 등록
	@Insert("insert into norder "
			+ "(order_num,mem_num,total_name,order_total,"
			+ "status,receive_name,receive_post,"
			+ "receive_address1,receive_address2,"
			+ "receive_phone,notice,reg_date) "
			+ "values(norder_seq.nextval,#{mem_num}"
			+ "#{total_name},#{order_total},"
			+ "#{status},#{receive_name},#{receive_post},"
			+ "#{receive_address1},#{receive_address2},"
			+ "#{receive_phone},#{notice},sysdate)")
	public void insertOrder(OrderVO order); 
	
	//주문 상세 등록
	@Insert("insert into norder_detail "
			+ "(detail_num,product_num,product_name,"
			+ "product_price,product_total,"
			+ "order_quantity,order_num)"
			+ "values(norder_detail.seq,"
			+ "#{product_num},#{product_name},"
			+ "#{product_price},#{product_total},"
			+ "#{order_quantity},#{order_num})")
	public void insertOrder_datail(OrderVO order);
	
	//주문 수정 
	
	
	//회원별 주문 목록의 수
	
	//회원별 주문 목록 
	
	//주문 상세
	
}
