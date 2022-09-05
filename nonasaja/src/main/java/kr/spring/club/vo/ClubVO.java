package kr.spring.club.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;


public class ClubVO {
	private int club_num;
	@NotEmpty
	private String club_title;
	private int club_leader;
	@NotEmpty
	private String club_content;
	private Date club_date;
	private int club_code;
	private int club_limit;
	private int club_pre;
	private int club_hit;
	
	
	public int getClub_num() {
		return club_num;
	}
	public void setClub_num(int club_num) {
		this.club_num = club_num;
	}
	public String getClub_title() {
		return club_title;
	}
	public void setClub_title(String club_title) {
		this.club_title = club_title;
	}
	public int getClub_leader() {
		return club_leader;
	}
	public void setClub_leader(int club_leader) {
		this.club_leader = club_leader;
	}
	public String getClub_content() {
		return club_content;
	}
	public void setClub_content(String club_content) {
		this.club_content = club_content;
	}
	public Date getClub_date() {
		return club_date;
	}
	public void setClub_date(Date club_date) {
		this.club_date = club_date;
	}
	
	public int getClub_code() {
		return club_code;
	}
	public void setClub_code(int club_code) {
		this.club_code = club_code;
	}
	public int getClub_limit() {
		return club_limit;
	}
	public void setClub_limit(int club_limit) {
		this.club_limit = club_limit;
	}
	public int getClub_pre() {
		return club_pre;
	}
	public void setClub_pre(int club_pre) {
		this.club_pre = club_pre;
	}
	public int getClub_hit() {
		return club_hit;
	}
	public void setClub_hit(int club_hit) {
		this.club_hit = club_hit;
	}
	
	
}
