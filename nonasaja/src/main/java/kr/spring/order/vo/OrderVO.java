package kr.spring.order.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class OrderVO {
	// order 테이블
	@NotEmpty
	private int order_num;
	@NotEmpty
	private int mem_num;
	//주문상품 전체 이름
	@NotEmpty
	private String total_name;
	@NotEmpty
	private int order_total;
	//1-배송대기,2-배송준비,3-배송중,4-배송완료,5-주문취소
	@NotEmpty
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
	@NotEmpty
	private Date reg_date;
	private Date modify_date;
	
	//detail 
	@NotEmpty
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
	
	public int getDetail_num() {
		return detail_num;
	}
	public void setDetail_num(int detail_num) {
		this.detail_num = detail_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_total() {
		return product_total;
	}
	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
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
	
}
