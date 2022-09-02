package kr.spring.review.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class ReviewVO {

	@NotEmpty
	private int review_num;
	@NotEmpty
	private int product_num;
	@NotEmpty
	private int mem_num;
	@NotEmpty
	private int score;
	private String content;
	@NotEmpty
	private Date reg_date;
	
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [review_num=" + review_num + ", product_num=" + product_num + ", mem_num=" + mem_num
				+ ", score=" + score + ", content=" + content + ", reg_date=" + reg_date + "]";
	}
}
