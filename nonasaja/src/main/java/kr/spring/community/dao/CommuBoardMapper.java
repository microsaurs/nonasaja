package kr.spring.community.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.community.vo.CommunityVO;

@Mapper
public interface CommuBoardMapper {
	//유머글
	public List<CommunityVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO community_board (commu_num,commu_title,commu_content,commu_code,mem_num) VALUES (community_seq.nextval,#{commu_title},#{commu_content},#{commu_code},#{mem_num})")
	public void insertBoard(CommunityVO board);
	@Select("SELECT * FROM community_board b JOIN member m USING(mem_num) JOIN member_detail d USING(mem_num) WHERE b.commu_num=#{commu_num}")
	public CommunityVO selectBoard(Integer commu_num);
	@Update("UPDATE community_board SET commu_hit=commu_hit+1 WHERE commu_num=#{commu_num}")
	public void updateHit(Integer commu_num);
	public void updateBoard(CommunityVO board);
	@Delete("DELETE FROM community_board WHERE commu_num=#{commu_num}")
	public void deleteBoard(Integer commu_num);
}
