package kr.spring.used.vo;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UsedVO {
	private int used_num; //게시글 번호
	@NotEmpty
	private String title; //제목
	@NotEmpty
	private String content; //내용
	@NotEmpty
	private int kind; //거래 종류 1중고,2무나,3교환
	@Min(0)
	private int price; //가격
	private int status; //거래 상태 1판매중,2판매완료
	@NotEmpty
	private String category; //상품 카테고리 
	private String trade; //물물교환 상품
	private Date reg_date; //작성일
	private Date modify_date; //수정일
	private int hit; //조회수
	
	private String id; //회원 아이디
	private String nickname; // 회원별명
	private byte[] photo; //프로필 사진
	private String photo_name; // 프로필 사진명
	
	
	private int mem_num; //작성자 번호
	private int region_num; //지역 식별번호
	
	
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
	public int getUsed_num() {
		return used_num;
	}
	public void setUsed_num(int used_num) {
		this.used_num = used_num;
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
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getRegion_num() {
		return region_num;
	}
	public void setRegion_num(int region_num) {
		this.region_num = region_num;
	}
	
	@Override
	public String toString() {
		return "UsedVO [used_num=" + used_num + ", title=" + title + ", content=" + content + ", kind=" + kind
				+ ", price=" + price + ", status=" + status + ", category=" + category + ", trade=" + trade
				+ ", reg_date=" + reg_date + ", modify_date=" + modify_date + ", hit=" + hit + ", mem_num=" + mem_num
				+ ", region_num=" + region_num + "]";
	}
	
	
}
