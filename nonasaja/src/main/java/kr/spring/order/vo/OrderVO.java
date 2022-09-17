package kr.spring.order.vo;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class OrderVO {
	// order 테이블

	private int order_num;

	private int mem_num;
	//주문상품 전체 이름

	private String total_name;

	private int order_total;
	//1-배송대기,2-배송준비,3-배송중,4-배송완료,5-주문취소

	private int status;
	@NotEmpty
	private String receive_name;
	@NotEmpty
	private String receive_post;
	@NotEmpty
	private String receive_address1;
	@NotEmpty
	private String receive_address2;
	@NotEmpty
	private String receive_phone;
	private String notice;
	private Date reg_date;
	private Date modify_date;
	private List cartList;
	
	//detail 
	/*@NotEmpty
	private int detail_num;
	@NotEmpty 
	private int product_num;
	@NotEmpty
	private String product_name;
	@NotEmpty
	private int product_price;
	@NotEmpty
	private int product_total;
	@NotEmpty
	private int order_quantity;
	*/
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getTotal_name() {
		return total_name;
	}
	public void setTotal_name(String total_name) {
		this.total_name = total_name;
	}
	public int getOrder_total() {
		return order_total;
	}
	public void setOrder_total(int order_total) {
		this.order_total = order_total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getReceive_post() {
		return receive_post;
	}
	public void setReceive_post(String receive_post) {
		this.receive_post = receive_post;
	}
	public String getReceive_address1() {
		return receive_address1;
	}
	public void setReceive_address1(String receive_address1) {
		this.receive_address1 = receive_address1;
	}
	public String getReceive_address2() {
		return receive_address2;
	}
	public void setReceive_address2(String receive_address2) {
		this.receive_address2 = receive_address2;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public List getCartList() {
		return cartList;
	}
	public void setCartList(List cartList) {
		this.cartList = cartList;
	}
	@Override
	public String toString() {
		return "OrderVO [order_num=" + order_num + ", mem_num=" + mem_num + ", total_name=" + total_name
				+ ", order_total=" + order_total + ", status=" + status + ", receive_name=" + receive_name
				+ ", receive_post=" + receive_post + ", receive_address1=" + receive_address1 + ", receive_address2="
				+ receive_address2 + ", receive_phone=" + receive_phone + ", notice=" + notice + ", reg_date="
				+ reg_date + ", modify_date=" + modify_date + ", cartList=" + cartList + "]";
	}
	
}
