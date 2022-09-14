package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	public void insertMember(MemberVO member);//회원가입
	public void insertNaverMember(MemberVO member);//네이버 회원가입
	public void insertKakaoMember(MemberVO member);//카카오 회원가입
	public MemberVO selectCheckMember(String id);//아이디 중복 체크
	public MemberVO selectMember(Integer mem_num);//하나의 회원 조회
	public void updateMember(MemberVO member);//회원정보 수정
	public void updatePassword(MemberVO member);//비밀번호 수정
	public void deleteMember(Integer mem_num);//회원 삭제
	public void updateProfile(MemberVO member);//프로필 이미지 업데이트
}
