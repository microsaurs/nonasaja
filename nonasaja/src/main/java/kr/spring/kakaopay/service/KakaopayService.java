package kr.spring.kakaopay.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;

public interface KakaopayService {
	public void insertPoint(LionPointVO point);//포인트 충전
	public int selectPointCnt(Integer mem_num);//포인트 내역 건수
	public List<LionPointVO> selectPointList(Map<String, Object> map);//포인트 내역 목록
	public LionPointVO selectPointbyMem(Integer mem_num);//회원번호로 포인트내역 상세
	public void updateMemberCash(MemberVO member);//멤버디테일에 있는 캐시 컬럼 업데이트
}
