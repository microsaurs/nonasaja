package kr.spring.join.vo;

import java.sql.Date;

import kr.spring.club.vo.ClubVO;

public class JoinVO {
	private int join_num;//동호회가입번호
	private int club_num;//동호회번호
	private int mem_num;//동호회가입한 멤버번호
	private Date join_reg_date;//동호회가입한날짜
	private ClubVO clubVO;
	
	public int getJoin_num() {
		return join_num;
	}
	public void setJoin_num(int join_num) {
		this.join_num = join_num; 
	}
	public int getClub_num() {
		return club_num;
	}
	public void setClub_num(int club_num) {
		this.club_num = club_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public Date getJoin_reg_date() {
		return join_reg_date;
	}
	public void setJoin_reg_date(Date join_reg_date) {
		this.join_reg_date = join_reg_date;
	}
	public ClubVO getClubVO() {
		return clubVO;
	}
	public void setClubVO(ClubVO clubVO) {
		this.clubVO = clubVO;
	}

	
	
}
