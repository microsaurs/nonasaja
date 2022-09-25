package kr.spring.club.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;


public class ClubVO {
	private int club_num;
	@NotEmpty
	private String club_title;
	@NotEmpty
	private String club_name;
	private int club_leader;
	@NotEmpty
	private String club_content;
	@NotEmpty
	private String club_date;
	private Date club_modify_date;
	private int club_code;
	private int club_limit;
	private int club_pre;
	private int club_hit;
	private Date club_reg_date;
	private byte[] club_img;
	private String club_img_name;
	private String club_age;
	private int club_gender;
	private int club_recruit;

	private int mem_num;
	private String club_region;
	
	private int join_num;
	
	//파일 업로드 처리
	public void setUpload(MultipartFile upload)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setClub_img(upload.getBytes());
		//파일명 구하기
		setClub_img_name(upload.getOriginalFilename());
	}

	//===================checkbox===========================//
			//form:checkbox에서 사용할 수 있도록 String -> String[]로 변환 
	public String[] getF_club_age() {
		String[] f_club_age = null;
		if(club_age!=null) f_club_age = club_age.split(",");
		return f_club_age;
	}
	//String[] -> String
	public void setF_club_age(String[] f_club_age) {
		if(f_club_age!=null) {
			this.club_age = "";
			for(int i=0;i<f_club_age.length;i++) {
				if(i>0) this.club_age += ",";
				this.club_age += f_club_age[i];
			}
		}
	}
	//===================checkbox===========================//

	private String id; //회원 아이디
	private String nickname; // 회원별명

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

	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
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

	public String getClub_date() {
		return club_date;
	}
	public void setClub_date(String club_date) {
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

	public String getClub_age() {
		return club_age;
	}
	public void setClub_age(String club_age) {
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

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getClub_region() {
		return club_region;
	}

	public void setClub_region(String club_region) {
		this.club_region = club_region;
	}

	public int getJoin_num() {
		return join_num;
	}

	public void setJoin_num(int join_num) {
		this.join_num = join_num;
	}

	@Override
	public String toString() {
		return "ClubVO [club_num=" + club_num + ", club_title=" + club_title + ", club_name=" + club_name
				+ ", club_leader=" + club_leader + ", club_content=" + club_content + ", club_date=" + club_date
				+ ", club_modify_date=" + club_modify_date + ", club_code=" + club_code + ", club_limit=" + club_limit
				+ ", club_pre=" + club_pre + ", club_hit=" + club_hit + ", club_reg_date=" + club_reg_date
				+ ", club_img_name=" + club_img_name + ", club_age=" + club_age + ", club_gender=" + club_gender
				+ ", club_recruit=" + club_recruit + ", mem_num=" + mem_num + ", club_region=" + club_region
				+ ", join_num=" + join_num + ", id=" + id + ", nickname=" + nickname + "]";
	}
	
	
	
}
