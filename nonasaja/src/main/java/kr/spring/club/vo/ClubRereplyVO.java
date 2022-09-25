package kr.spring.club.vo;

public class ClubRereplyVO {
	private int rereply_num;
	private String rereply_content;
	private String rereply_date;
	private int club_num;
	private int mem_num;
	private int reply_num;
	
	public int getRereply_num() {
		return rereply_num;
	}
	public void setRereply_num(int rereply_num) {
		this.rereply_num = rereply_num;
	}
	public String getRereply_content() {
		return rereply_content;
	}
	public void setRereply_content(String rereply_content) {
		this.rereply_content = rereply_content;
	}
	public String getRereply_date() {
		return rereply_date;
	}
	public void setRereply_date(String rereply_date) {
		this.rereply_date = rereply_date;
	}
	public int getClub_num() {
		return club_num;
	}
	public void setClub_num(int club_num) {
		this.club_num = club_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	@Override
	public String toString() {
		return "ClubRereplyVO [rereply_num=" + rereply_num + ", rereply_content=" + rereply_content + ", rereply_date="
				+ rereply_date + ", club_num=" + club_num + ", mem_num=" + mem_num + ", reply_num=" + reply_num + "]";
	}
	
	
}
