package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();//회원번호 추출
	@Insert("INSERT INTO member (mem_num,id,nickname) VALUES (#{mem_num},#{id},#{nickname})")
	public void insertMember(MemberVO member);//자체 회원가입
	//xml
	public void insertMember_detail(MemberVO member);//상세회원가입 xml
	
	//네이버 회원 가입 xml
	public void insertNaverMember(MemberVO member);
	//카카오 회원 가입 xml
	public void insertKakaoMember(MemberVO member);
	
	@Select("SELECT m.mem_num,m.id,m.auth,m.nickname,d.passwd,d.name,d.photo,d.photo_name,d.root,d.cash FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.id=#{id}")
	public MemberVO selectCheckMember(String id);//아이디 중복 체크
	@Select("SELECT * FROM member JOIN member_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);//하나의 회원 조회
	@Update("UPDATE member SET nickname=#{nickname} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);//회원정보 수정 !interest 추가하기
	@Update("UPDATE member_detail SET email=#{email},phone=#{phone},zipcode=#{zipcode},addr1=#{addr1},addr2=#{addr2},interest=#{interest},modify_date=sysdate WHERE mem_num=#{mem_num}")
	public void updateMember_detail(MemberVO member);//회원정보 수정
	@Update("UPDATE member_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);//비밀번호 수정
	@Update("UPDATE member SET auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);//회원 탈퇴 처리
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);//회원 정보 삭제
	
	@Update("UPDATE member_detail SET photo=#{photo},photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);//프로필 이미지 업데이트
	
	//회원관리 - 관리자
	public List<MemberVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Update("UPDATE spmember SET auth=#{auth} WHERE mem_num=#{mem_num}")
	public void updateByAdmin(MemberVO member);
}
