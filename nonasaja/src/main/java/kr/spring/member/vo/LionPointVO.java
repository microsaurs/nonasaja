package kr.spring.member.vo;

import java.sql.Date;

public class LionPointVO {
	private int point_num;
	private int mem_num;
	private int lionpoint;//충전 금액
	private int cash;//거래 전 잔액
	private int remain;//거래 후 잔액
	private int order_num;//주문 번호
	private Date reg_date;
	
	public int getPoint_num() {
		return point_num;
	}
	public void setPoint_num(int point_num) {
		this.point_num = point_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getLionpoint() {
		return lionpoint;
	}
	public void setLionpoint(int lionpoint) {
		this.lionpoint = lionpoint;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "LionPointVO [point_num=" + point_num + ", mem_num=" + mem_num + ", lionpoint=" + lionpoint + ", cash="
				+ cash + ", remain=" + remain + ", order_num=" + order_num + ", reg_date=" + reg_date + "]";
	}
	
	
}
