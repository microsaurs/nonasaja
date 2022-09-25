package kr.spring.used.vo;

public class UsedRereplyVO {
	private int rereply_num;
	private String rereply_content;
	private String rereply_date;
	private int used_num;
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
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	
	@Override
	public String toString() {
		return "UsedRereplyVO [rereply_num=" + rereply_num + ", rereply_content=" + rereply_content + ", rereply_date="
				+ rereply_date + ", used_num=" + used_num + ", mem_num=" + mem_num + ", reply_num=" + reply_num + "]";
	}
	
	
}
