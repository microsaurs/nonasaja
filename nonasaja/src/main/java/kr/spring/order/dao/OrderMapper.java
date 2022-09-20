package kr.spring.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;

@Mapper
public interface OrderMapper {
	//order_num 구하기
	@Select("select norder_seq.nextval from dual")
	public int selectOrder_num();
	
	//주문 등록
	@Insert("insert into norder "
			+ "(order_num,mem_num,total_name,order_total,"
			+ "status,receive_name,receive_post,"
			+ "receive_address1,receive_address2,"
			+ "receive_phone,notice,reg_date) "
			+ "values(#{order_num},#{mem_num},"
			+ "#{total_name},#{order_total},"
			+ "#{status},#{receive_name},#{receive_post},"
			+ "#{receive_address1},#{receive_address2},"
			+ "#{receive_phone},#{notice},sysdate)")
	public void insertOrder(OrderVO order); 
	
	//주문 상세 등록
	@Insert("insert into norder_detail "
			+ "(detail_num,product_num,product_name,"
			+ "product_price,product_total,"
			+ "order_quantity,order_num,wait_status)"
			+ "values(norder_detail_seq.nextval,"
			+ "#{product_num},#{product_name},"
			+ "#{product_price},#{product_total},"
			+ "#{order_quantity},#{order_num},#{wait_status})")
	public void insertOrder_datail(OrderDetailVO order);
	
	//상품별 주문 대기 수 구하기
	@Select("select  NVL(sum(order_quantity),0) from norder_detail where product_num=#{prododuct_num} and wait_status=1")
	public int selectWaitCount(int product_num);
	
	//주문의 status 수정하기
	@Update("update norder_detail set wait_status = #{wait_status} where detail_num=#{detail_num}")
	public void updateWaitStatus(int detail_num, int wait_status);
	
	//주문 수정 
	
	//회원별 주문 목록의 수
	public int selectOrderCount(Map<String,Object> map);
	
	//회원별 주문 목록 
	public List<OrderVO> selectOrderList(Map<String,Object> map);
	
	//회원별 주문 상세 목록 
	public List<OrderDetailVO> selectOrderDetailList(Map<String,Object> map);
	
	//회원별 주문 대기 상품의 총 가격 합 
	@Select("select NVL(sum(d.product_total),0) "
			+ "from norder_detail d join norder o using(order_num) "
			+ "where o.mem_num=285 and d.wait_status=1")
	public int selectSumWait(int mem_num);
	
	//상품별 orderDetail의 주문 대기 목록 
	@Select("select * from norder_detail where product_num=#{product_num} and wait_status=1")
	public List<OrderDetailVO> selectWaitList(int product_num);
	
	//주문 상세
	@Select("select * from norder where order_num=#{order_num}")
	public OrderVO selectOrder(int order_num);
}













