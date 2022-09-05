package kr.spring.community.vo;

import java.sql.Date;

public class CommunityFavVO {
	private int fav_num;
//	private Date fav_date;
	private int commu_num;
	private int mem_num;
	private int fav_code;
	
	
	public int getFav_code() {
		return fav_code;
	}
	public void setFav_code(int fav_code) {
		this.fav_code = fav_code;
	}
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}

	/*
	 * public Date getFav_date() { return fav_date; } public void setFav_date(Date
	 * fav_date) { this.fav_date = fav_date; }
	 */
	
	public int getCommu_num() {
		return commu_num;
	}
	public void setCommu_num(int commu_num) {
		this.commu_num = commu_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
}
