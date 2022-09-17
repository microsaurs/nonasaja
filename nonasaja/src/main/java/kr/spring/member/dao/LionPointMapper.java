package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface LionPointMapper {
	@Select("SELECT point_seq.nextval FROM dual")
	public int selectPointNum();//포인트 번호
	@Insert("INSERT INTO lionpoint (point_num,mem_num,lionpoint,cash,remain,order_num,reg_date) VALUES (#{point_num},#{mem_num},#{lionpoint},#{cash},#{remain},#{order_num},SYSDATE)")
	public void insertPoint(LionPointVO point);//포인트 충전
	@Select("SELECT COUNT(*) FROM lionpoint where mem_num=#{mem_num}")
	public int selectPointCnt(Integer mem_num);//포인트 내역 건수
	public List<LionPointVO> selectPointList(Map<String, Object> map);//포인트 내역 목록
	@Select("select * from (select a.*, rownum rnum from(select * from lionpoint order by point_num desc) a) where rnum=1 and mem_num=#{mem_num}")
	public LionPointVO selectPointbyMem(Integer mem_num);//회원번호로 포인트 직전 내역 조회
	@Update("UPDATE member_detail SET cash=#{cash} WHERE mem_num=#{mem_num}")
	public void updateMemberCash(MemberVO member);//멤버디테일에 있는 캐시 컬럼 업데이트
}
