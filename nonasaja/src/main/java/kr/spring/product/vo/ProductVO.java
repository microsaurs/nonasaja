package kr.spring.product.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	private int product_num;
	@NotEmpty
	private String name;
	//식품0, 생활용품1
	private int kind;
	@NotEmpty
	private String sub_category;
	@NotEmpty
	private String title;
	//원가
	@Min(0)
	private int price1; 
	//판매가
	@Min(0)
	private int price2;
	@Min(0)
	private int quantity;
	//상품상세설명
	@NotEmpty
	private String detail;
	//구매요구수량
	@Min(0)
	private int req_quantity;
	//유효성 체크를 위해서 만듦
	private MultipartFile upload1;
	//대표이미지 (나머지 둘은 슬라이드)
	private byte[] photo1;
	private byte[] photo2;
	private byte[] photo3;
	//파일 이름
	private String photo1_name;
	private String photo2_name;
	private String photo3_name;
	@NotEmpty
	private String company;
	@NotEmpty
	private String origin;
	//0:판매중 1:마감
	private int status;
	@NotEmpty
	private String deadline;
	private Date reg_date;
	private Date modify_date;
	@NotEmpty
	private String quantity_detail;
	@NotEmpty
	private String exp_date;
	@NotEmpty
	private String storage;
	@NotEmpty
	private String cus_phone;
	
	//유효성 체크를 위한 getUpload1
	public MultipartFile getUpload1() {
		return upload1;
	}
	
	//이미지 blob 처리
	//주의! 사진 업로드 폼에서 파일 업로드 파라미터네임은 반드시 upload1,2,3이라고 지정해야 함
	public void setUpload1(MultipartFile upload) throws IOException{
		//MultipartFile -> byte[]로 형변환
		setPhoto1(upload.getBytes());
		//파일 이름 처리
		setPhoto1_name(upload.getOriginalFilename());
	}
	
	public void setUpload2(MultipartFile upload) throws IOException{
		setPhoto2(upload.getBytes());
		setPhoto2_name(upload.getOriginalFilename());
	}
	
	public void setUpload3(MultipartFile upload) throws IOException{
		setPhoto3(upload.getBytes());
		setPhoto3_name(upload.getOriginalFilename());
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice1() {
		return price1;
	}
	public void setPrice1(int price1) {
		this.price1 = price1;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getReq_quantity() {
		return req_quantity;
	}
	public void setReq_quantity(int req_quantity) {
		this.req_quantity = req_quantity;
	}
	public byte[] getPhoto1() {
		return photo1;
	}
	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}
	public byte[] getPhoto2() {
		return photo2;
	}
	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}
	public byte[] getPhoto3() {
		return photo3;
	}
	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}
	public String getPhoto1_name() {
		return photo1_name;
	}
	public void setPhoto1_name(String photo1_name) {
		this.photo1_name = photo1_name;
	}
	public String getPhoto2_name() {
		return photo2_name;
	}
	public void setPhoto2_name(String photo2_name) {
		this.photo2_name = photo2_name;
	}
	public String getPhoto3_name() {
		return photo3_name;
	}
	public void setPhoto3_name(String photo3_name) {
		this.photo3_name = photo3_name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
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
	public String getQuantity_detail() {
		return quantity_detail;
	}
	public void setQuantity_detail(String quantity_detail) {
		this.quantity_detail = quantity_detail;
	}
	
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "ProductVO [product_num=" + product_num + ", name=" + name + ", kind=" + kind + ", sub_category="
				+ sub_category + ", title=" + title + ", price1=" + price1 + ", price2=" + price2 + ", quantity="
				+ quantity + ", detail=" + detail + ", req_quantity=" + req_quantity + ", photo1_name=" + photo1_name
				+ ", photo2_name=" + photo2_name + ", photo3_name=" + photo3_name + ", company=" + company + ", origin="
				+ origin + ", status=" + status + ", deadline=" + deadline + ", reg_date=" + reg_date + ", modify_date="
				+ modify_date + ", quantity_detail=" + quantity_detail + ", exp_date=" + exp_date + ", storage="
				+ storage + ", cus_phone=" + cus_phone + "]";
	}
	
}
