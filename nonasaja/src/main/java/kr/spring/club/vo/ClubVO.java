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
	private Date club_modify_date;
	private int club_code;
	private int club_limit;
	private int club_pre;
	private int club_hit;
	private Date club_reg_date;
	private byte[] club_img;
	private String club_img_name;
	private int club_age;
	private int club_gender;
	private int club_recruit;
	private int region_num;
	
	
	private String id; //회원 아이디
	private String nickname; // 회원별명
	private byte[] photo; //프로필 사진
	private String photo_name; // 프로필 사진명
	
	
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
	public Date getClub_modify_date() {
		return club_modify_date;
	}
	public void setClub_modify_date(Date club_modify_date) {
		this.club_modify_date = club_modify_date;
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
	public Date getClub_reg_date() {
		return club_reg_date;
	}
	public void setClub_reg_date(Date club_reg_date) {
		this.club_reg_date = club_reg_date;
	}
	public byte[] getClub_img() {
		return club_img;
	}
	public void setClub_img(byte[] club_img) {
		this.club_img = club_img;
	}
	public String getClub_img_name() {
		return club_img_name;
	}
	public void setClub_img_name(String club_img_name) {
		this.club_img_name = club_img_name;
	}
	public int getClub_age() {
		return club_age;
	}
	public void setClub_age(int club_age) {
		this.club_age = club_age;
	}
	public int getClub_gender() {
		return club_gender;
	}
	public void setClub_gender(int club_gender) {
		this.club_gender = club_gender;
	}
	public int getClub_recruit() {
		return club_recruit;
	}
	public void setClub_recruit(int club_recruit) {
		this.club_recruit = club_recruit;
	}
	public int getRegion_num() {
		return region_num;
	}
	public void setRegion_num(int region_num) {
		this.region_num = region_num;
	}
	
	
}
