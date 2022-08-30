package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	//member 테이블
	private int mem_num;
	@Pattern(regexp="^[A-Za-z0-9]{4,20}$")
	private String id;
	private int auth;
	@NotEmpty
	private String nickname;
	
	//member_detail 테이블
	@NotEmpty
	private String name;
	@Pattern(regexp="^[A-Za-z0-9]{4,15}$")
	private String passwd;
	@NotEmpty
	private String phone;
	@Email
	@NotEmpty
	private String email;
	@Size(min=5,max=5)
	private String zipcode;
	@NotEmpty
	private String addr1;
	@NotEmpty
	private String addr2;
	private Date reg_date;
	private Date modify_date;
	private byte[] photo;
	private String photo_name;
	private String interest;
	private int cash;
	
	//비밀번호 변경시 현재 비밀번호를 저장하는 용도
	@Pattern(regexp="^[A-Za-z0-9]{4,15}$")
	private String now_passwd;
	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd) {
		if(auth > 1 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//이미지 blob 처리
	//(주의)폼에서 파일업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setPhoto(upload.getBytes());
		//파일 이름
		setPhoto_name(upload.getOriginalFilename());
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

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
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

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public String getNow_passwd() {
		return now_passwd;
	}

	public void setNow_passwd(String now_passwd) {
		this.now_passwd = now_passwd;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", id=" + id + ", auth=" + auth + ", nickname=" + nickname + ", name="
				+ name + ", passwd=" + passwd + ", phone=" + phone + ", email=" + email + ", zipcode=" + zipcode
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", reg_date=" + reg_date + ", modify_date=" + modify_date
				+ ", photo_name=" + photo_name + ", interest=" + interest + ", cash=" + cash + ", now_passwd="
				+ now_passwd + "]";
	}
	
}
