package kr.spring.used.vo;

public class UsedReplyVO {
	private int reply_num;
	private String reply_content;
	private String reply_date;
	private String reply_mdate;
	private int used_num;
	private int mem_num;
	
	private String id;
	private String nickname;
	
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getReply_mdate() {
		return reply_mdate;
	}
	public void setReply_mdate(String reply_mdate) {
		this.reply_mdate = reply_mdate;
	}
	public int getUsed_num() {
		return used_num;
	}
	public void setUsed_num(int used_num) {
		this.used_num = used_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "UsedReplyVO [reply_num=" + reply_num + ", reply_content=" + reply_content + ", reply_date=" + reply_date
				+ ", reply_mdate=" + reply_mdate + ", used_num=" + used_num + ", mem_num=" + mem_num + ", id=" + id
				+ ", nickname=" + nickname + "]";
	}
	
	
}
