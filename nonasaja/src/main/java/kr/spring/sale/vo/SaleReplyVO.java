package kr.spring.sale.vo;

import kr.spring.util.DurationFromNow;

public class SaleReplyVO {
	private int reply_num; //댓글 번호
	private int board_num; //부모글 번호
	private int mem_num; //회원번호
	private String reply_content; //내용
	private String reply_date; //등록일
	private String reply_mdate; //수정일
	private int parent_num; //부모 댓글 번호
 
	
	private String id; //아이디
	private String nickname;//별명
	  
	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
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

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = DurationFromNow.getTimeDiffLabel(reply_date);
	}

	public String getReply_mdate() {
		return reply_mdate;
	}

	public void setReply_mdate(String reply_mdate) {
		this.reply_mdate = DurationFromNow.getTimeDiffLabel(reply_mdate);
	}

	public int getParent_num() {
		return parent_num;
	}

	public void setParent_num(int parent_num) {
		this.parent_num = parent_num;
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
		return "BoardReplyVO [reply_num=" + reply_num + ", reply_content=" + reply_content + ", reply_date=" + reply_date + ", reply_mdate="
				+ reply_mdate + ", board_num=" + board_num + ", mem_num=" + mem_num + ", parent_num" + parent_num + ", id=" + id
				+ ", nickname=" + nickname + "]";
	}
}
