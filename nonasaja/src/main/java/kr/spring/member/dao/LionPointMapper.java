package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface LionPointMapper {
	@Select("SELECT point_seq.nextval FROM dual")
	public int selectPointNum();//포인트 번호
	public void insertPoint(LionPointVO point);//포인트 내역 저장
	public int selectPointCnt(Map<String, Object> map);//포인트 내역 건수
	public List<LionPointVO> selectPointList(Map<String, Object> map);//포인트 내역 목록
	public LionPointVO selectPointbyMem(MemberVO member);//회원번호로 포인트내역 상세
	
}
