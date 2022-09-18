package kr.spring.community.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class RecipeVO {
	private int commu_num; //게시글 번호
	@NotEmpty
	private String commu_title; //게시글 제목
	private int mem_num; //게시글 작성자 식별
	@NotEmpty
	private String commu_content; //레시피 과정1
	@NotEmpty
	private String commu_content2; //레시피 과정2
	@NotEmpty
	private String commu_content3; //레시피 과정3
	private String commu_content4; //레시피 과정4
	private String commu_content5; //레시피 과정5
	private Date commu_date; //게시글 작성일
	private Date commu_modify_date; //최근 수정일
	private int commu_recommend; //추천수
	private int commu_hit; //조회수
	@NotEmpty
	private String commu_food; //음식 이름
	@NotEmpty
	private String commu_ingredient; //필수 재료
	@NotEmpty
	private String commu_level; //요리 난이도
	@NotEmpty
	private String commu_time; //요리 시간
	
	
	private byte[] uploadfile;//파일
	private String filename;//파일명
	private byte[] uploadfile2;//레시피 파일
	private String filename2;//레시피 파일명
	private byte[] uploadfile3;//레시피 파일
	private String filename3;//레시피 파일명
	private byte[] uploadfile4;//레시피 파일
	private String filename4;//레시피 파일명
	private byte[] uploadfile5;//레시피 파일
	private String filename5;//레시피 파일명
	private byte[] uploadfile6;//레시피 파일
	private String filename6;//레시피 파일명
	
	private String id; //회원 아이디
	private String nickname; // 회원별명
	private byte[] photo; //프로필 사진
	private String photo_name; // 프로필 사진명
	
	
	//파일 업로드 처리
		public void setUpload(MultipartFile upload) throws IOException{
			//MultipartFile -> byte[] 변환
			setUploadfile(upload.getBytes());
			//파일명 구하기
			setFilename(upload.getOriginalFilename());
		}

		//파일 업로드 처리
		public void setUpload2(MultipartFile upload) throws IOException{
			//MultipartFile -> byte[] 변환
			setUploadfile2(upload.getBytes());
			//파일명 구하기
			setFilename2(upload.getOriginalFilename());
		}

		//파일 업로드 처리
		public void setUpload3(MultipartFile upload) throws IOException{
			//MultipartFile -> byte[] 변환
			setUploadfile3(upload.getBytes());
			//파일명 구하기
			setFilename3(upload.getOriginalFilename());
		}
		//파일 업로드 처리
		public void setUpload4(MultipartFile upload) throws IOException{
			//MultipartFile -> byte[] 변환
			setUploadfile4(upload.getBytes());
			//파일명 구하기
			setFilename4(upload.getOriginalFilename());
		}
		//파일 업로드 처리
		public void setUpload5(MultipartFile upload) throws IOException{
			//MultipartFile -> byte[] 변환
			setUploadfile5(upload.getBytes());
			//파일명 구하기
			setFilename5(upload.getOriginalFilename());
		}
		//파일 업로드 처리
		public void setUpload6(MultipartFile upload) throws IOException{
			//MultipartFile -> byte[] 변환
			setUploadfile6(upload.getBytes());
			//파일명 구하기
			setFilename6(upload.getOriginalFilename());
		}
		
	public String getCommu_content2() {
			return commu_content2;
		}

		public void setCommu_content2(String commu_content2) {
			this.commu_content2 = commu_content2;
		}

		public String getCommu_content3() {
			return commu_content3;
		}

		public void setCommu_content3(String commu_content3) {
			this.commu_content3 = commu_content3;
		}

		public String getCommu_content4() {
			return commu_content4;
		}

		public void setCommu_content4(String commu_content4) {
			this.commu_content4 = commu_content4;
		}

		public String getCommu_content5() {
			return commu_content5;
		}

		public void setCommu_content5(String commu_content5) {
			this.commu_content5 = commu_content5;
		}

		public byte[] getUploadfile4() {
			return uploadfile4;
		}

		public void setUploadfile4(byte[] uploadfile4) {
			this.uploadfile4 = uploadfile4;
		}

		public String getFilename4() {
			return filename4;
		}

		public void setFilename4(String filename4) {
			this.filename4 = filename4;
		}

		public byte[] getUploadfile5() {
			return uploadfile5;
		}

		public void setUploadfile5(byte[] uploadfile5) {
			this.uploadfile5 = uploadfile5;
		}

		public String getFilename5() {
			return filename5;
		}

		public void setFilename5(String filename5) {
			this.filename5 = filename5;
		}

		public byte[] getUploadfile6() {
			return uploadfile6;
		}

		public void setUploadfile6(byte[] uploadfile6) {
			this.uploadfile6 = uploadfile6;
		}

		public String getFilename6() {
			return filename6;
		}

		public void setFilename6(String filename6) {
			this.filename6 = filename6;
		}

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
	public String getCommu_food() {
		return commu_food;
	}
	public void setCommu_food(String commu_food) {
		this.commu_food = commu_food;
	}
	public String getCommu_ingredient() {
		return commu_ingredient;
	}
	public void setCommu_ingredient(String commu_ingredient) {
		this.commu_ingredient = commu_ingredient;
	}
	public String getCommu_level() {
		return commu_level;
	}

	public void setCommu_level(String commu_level) {
		this.commu_level = commu_level;
	}
	public String getCommu_time() {
		return commu_time;
	}
	public void setCommu_time(String commu_time) {
		this.commu_time = commu_time;
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
		return "RecipeVO [commu_num=" + commu_num + ", commu_title=" + commu_title + ", mem_num=" + mem_num
				+ ", commu_content=" + commu_content + ", commu_content2=" + commu_content2 + ", commu_content3="
				+ commu_content3 + ", commu_content4=" + commu_content4 + ", commu_content5=" + commu_content5
				+ ", commu_date=" + commu_date + ", commu_modify_date=" + commu_modify_date + ", commu_recommend="
				+ commu_recommend + ", commu_hit=" + commu_hit + ", commu_food=" + commu_food + ", commu_ingredient="
				+ commu_ingredient + ", commu_level=" + commu_level + ", commu_time=" + commu_time + ", filename="
				+ filename + ", filename2=" + filename2 + ", filename3=" + filename3 + ", filename4=" + filename4
				+ ", filename5=" + filename5 + ", filename6=" + filename6 + ", id=" + id + ", nickname=" + nickname
				+ ", photo_name=" + photo_name + "]";
	}
	

	
	
	
	
	
	
}

