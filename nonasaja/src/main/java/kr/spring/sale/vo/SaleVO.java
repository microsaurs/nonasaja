package kr.spring.sale.vo;


import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;


public class SaleVO {

	private int board_num;//게시판 번호
	@NotEmpty
	private String title;//제목
	@NotEmpty
	private String content;//내용
	private Date reg_date; //등록일
	private Date modify_date; //수정일
	private Date deadline; //수정일
	private int hit;//조회수
	private int mem_num; //회원번호
	
	private String id; //회원아이디
	private String nick_name;//회원별명
	private byte[] photo;//프로필 사진
	private String photo_name;//프로필 사진명
	
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Date getDeadline(Date deadline) {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
	@Override
	public String toString() {
		return "SaleVO [board_num=" + board_num + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", deadline=" + deadline
				+ ", mem_num=" + mem_num + ", id=" + id + ", nick_name=" + nick_name + ", photo_name=" + photo_name
				+ "]";
	}
}

