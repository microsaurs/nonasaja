package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;

public interface LionPointService {
	public int selectPointNum();//포인트 번호
	public void insertPoint(LionPointVO point);//포인트 내역 저장(충전/결제)
	public int selectPointCnt(Integer mem_num);//포인트 내역 건수
	public List<LionPointVO> selectPointList(Map<String, Object> map);//포인트 내역 목록
	public LionPointVO selectPointbyMem(MemberVO member);//회원번호로 포인트내역 상세
}
