package kr.spring.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();//회원번호 추출
	@Insert("INSERT INTO member (mem_num,id,nickname) VALUES (#{mem_num},#{id},#{nickname})")
	public void insertMember(MemberVO member);//회원가입
	@Insert("INSERT INTO member_detail (mem_num,name,passwd,phone,email,zipcode,addr1,addr2,interest) VALUES (#{mem_num},#{name},#{passwd},#{phone},#{email},#{zipcode},#{addr1},#{addr2},#{interest})")
	public void insertMember_detail(MemberVO member);//회원가입
	public MemberVO selectCheckMember(String id);//아이디 중복 체크
	public MemberVO selectMember(Integer mem_num);//하나의 회원 조회
	public void updateMember(MemberVO member);//회원정보 수정
	public void updateMember_detail(MemberVO member);//회원정보 수정
	public void updatePassword(MemberVO member);//비밀번호 수정
	public void deleteMember(Integer mem_num);//회원 삭제
	public void deleteMember_detail(Integer mem_num);//회원 삭제
}
