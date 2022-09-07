package kr.spring.used.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;


public class UsedVO {
	private int used_num; //게시글 번호
	@NotEmpty
	private String title; //제목
	@NotEmpty
	private String content; //내용
	private int kind; //거래 종류 1중고,2무나,3교환
	@Range(min=1,max=99999999)
	private int price; //가격
	private int status; //거래 상태 1판매중,2판매완료
	private String category; //상품 카테고리 
	private String trade; //물물교환 상품
	private byte[] uploadfile;
	private String filename;
	private byte[] uploadfile2;
	private String filename2;
	private byte[] uploadfile3;
	private String filename3;


	private Date reg_date; //작성일
	private Date modify_date; //수정일
	private int hit; //조회수
	
	private String id; //회원 아이디
	private String nickname; // 회원별명
	private byte[] photo; //프로필 사진
	private String photo_name; // 프로필 사진명
	
	
	private int mem_num; //작성자 번호
	private int region_num; //지역 식별번호
	
	
	//파일 업로드 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 변환
		setUploadfile(upload.getBytes());
		//파일명 구하기
		setFilename(upload.getOriginalFilename());
	}
	
	//파일 업로드 처리
	public void setUpload2(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 변환
		setUploadfile2(upload.getBytes());
		//파일명 구하기
		setFilename2(upload.getOriginalFilename());
	}
	
	//파일 업로드 처리
	public void setUpload3(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 변환
		setUploadfile3(upload.getBytes());
		//파일명 구하기
		setFilename3(upload.getOriginalFilename());
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
	public byte[] getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getUploadfile2() {
		return uploadfile2;
	}
	public void setUploadfile2(byte[] uploadfile2) {
		this.uploadfile2 = uploadfile2;
	}
	public String getFilename2() {
		return filename2;
	}
	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}
	public byte[] getUploadfile3() {
		return uploadfile3;
	}
	public void setUploadfile3(byte[] uploadfile3) {
		this.uploadfile3 = uploadfile3;
	}
	public String getFilename3() {
		return filename3;
	}
	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}
	
	@Override
	public String toString() {
		return "UsedVO [used_num=" + used_num + ", title=" + title + ", content=" + content + ", kind=" + kind
				+ ", price=" + price + ", status=" + status + ", category=" + category + ", trade=" + trade
				+ ", filename=" + filename + ", filename2=" + filename2 + ", filename3=" + filename3 + ", reg_date="
				+ reg_date + ", modify_date=" + modify_date + ", hit=" + hit + ", id=" + id + ", nickname=" + nickname
				+ ", photo_name=" + photo_name + ", mem_num=" + mem_num + ", region_num=" + region_num + "]";
	}
	
	
	
	
}
