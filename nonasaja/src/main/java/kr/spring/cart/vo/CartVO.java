package kr.spring.cart.vo;

import kr.spring.product.vo.ProductVO;

public class CartVO {
	private int cart_num;
	private int mem_num;
	private int product_num;
	private int quantity;
	private String reg_date;
	//한건의 상품의 총 구매금액
	private int sub_total;
		
	//상품 정보
	private ProductVO productVO;	
	
	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public int getProduct_num() {
		return product_num;
	}

	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getSub_total() {
		return sub_total;
	}

	public void setSub_total(int sub_total) {
		this.sub_total = sub_total;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
		this.sub_total = productVO.getPrice2()*this.quantity;
	}

	@Override
	public String toString() {
		return "CartVO [cart_num=" + cart_num + ", mem_num=" + mem_num + ", product_num=" + product_num + ", quantity="
				+ quantity + ", reg_date=" + reg_date + ", sub_total=" + sub_total
				+ ", productVO=" + productVO + "]";
	}
	
	
}
