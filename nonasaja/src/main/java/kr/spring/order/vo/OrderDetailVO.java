package kr.spring.order.vo;

import javax.validation.constraints.NotEmpty;

public class OrderDetailVO {
	
	//order_detail 테이블
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
	@NotEmpty
	private int order_num;
	private int wait_status;
	private int wait_count;
	private int req_quantity;
	
	public int getWait_status() {
		return wait_status;
	}
	public void setWait_status(int wait_status) {
		this.wait_status = wait_status;
	}
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
	
	public int getWait_count() {
		return wait_count;
	}
	public void setWait_count(int wait_count) {
		this.wait_count = wait_count;
	}
	public int getReq_quantity() {
		return req_quantity;
	}
	public void setReq_quantity(int req_quantity) {
		this.req_quantity = req_quantity;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [detail_num=" + detail_num + ", product_num=" + product_num + ", product_name="
				+ product_name + ", product_price=" + product_price + ", product_total=" + product_total
				+ ", order_quantity=" + order_quantity + ", order_num=" + order_num + ", wait_status=" + wait_status
				+ ", wait_count=" + wait_count + ", req_quantity=" + req_quantity + "]";
	}
	
	
}
