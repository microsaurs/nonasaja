package kr.spring.community.vo;

import java.sql.Date;

public class CommunityReplyVO {
	private int reply_num; //댓글 식별 번호
	private int commu_num; //부모글번호
	private int mem_num; //작성자 식별번호
	private String reply_content; //댓글 내용
	private Date reply_date; //댓글 작성일
	private int parent_num; //대댓글 부모댓글번호
	
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
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
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	public int getParent_num() {
		return parent_num;
	}
	public void setParent_num(int parent_num) {
		this.parent_num = parent_num;
	}
	
	@Override
	public String toString() {
		return "CommunityReplyVO [reply_num=" + reply_num + ", commu_num=" + commu_num + ", mem_num=" + mem_num
				+ ", reply_content=" + reply_content + ", reply_date=" + reply_date + ", parent_num=" + parent_num
				+ "]";
	}
	
	
}
