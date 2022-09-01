package kr.spring.community.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class CommunityVO {
	private int commu_num; //게시글 번호
	@NotEmpty
	private String commu_title; //게시글 제목
	private int mem_num; //게시글 작성자 식별
	@NotEmpty
	private String commu_content; //게시글 내용
	private Date commu_date; //게시글 작성일
	private Date commu_modify_date; //최근 수정일
	private int commu_code; //게시판코드 1-레시피 2-유머 3-자취정보
	private int commu_recommend; //추천수
	private int commu_hit; //조회수
	private int region_num; //지역번호
	
	private String id; //회원 아이디
	private String nickname; // 회원별명
	private byte[] photo; //프로필 사진
	private String photo_name; // 프로필 사진명
	
	public int getCommu_num() {
		return commu_num;
	}
	
	public void setCommu_num(int commu_num) {
		this.commu_num = commu_num;
	}
	public String getCommu_title() {
		return commu_title;
	}
	public void setCommu_title(String commu_title) {
		this.commu_title = commu_title;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getCommu_content() {
		return commu_content;
	}
	public void setCommu_content(String commu_content) {
		this.commu_content = commu_content;
	}
	public Date getCommu_date() {
		return commu_date;
	}
	public void setCommu_date(Date commu_date) {
		this.commu_date = commu_date;
	}
	public Date getCommu_modify_date() {
		return commu_modify_date;
	}
	public void setCommu_modify_date(Date commu_modify_date) {
		this.commu_modify_date = commu_modify_date;
	}
	public int getCommu_code() {
		return commu_code;
	}
	public void setCommu_code(int commu_code) {
		this.commu_code = commu_code;
	}
	public int getCommu_recommend() {
		return commu_recommend;
	}
	public void setCommu_recommend(int commu_recommend) {
		this.commu_recommend = commu_recommend;
	}
	public int getCommu_hit() {
		return commu_hit;
	}
	public void setCommu_hit(int commu_hit) {
		this.commu_hit = commu_hit;
	}
	public int getRegion_num() {
		return region_num;
	}
	public void setRegion_num(int region_num) {
		this.region_num = region_num;
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
		return "CommunityVO [commu_num=" + commu_num + ", commu_title=" + commu_title + ", mem_num=" + mem_num
				+ ", commu_content=" + commu_content + ", commu_date=" + commu_date + ", commu_modify_date="
				+ commu_modify_date + ", commu_code=" + commu_code + ", commu_recommend=" + commu_recommend
				+ ", commu_hit=" + commu_hit + ", region_num=" + region_num + ", id=" + id + ", nickname=" + nickname
				+ ", photo_name=" + photo_name + "]";
	}
	
	
	
}
