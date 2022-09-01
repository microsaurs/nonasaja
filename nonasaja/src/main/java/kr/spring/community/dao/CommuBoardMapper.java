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
	@Insert("INSERT INTO community_board (commu_num,commu_title,commu_content,commu_code,region_num,mem_num) VALUES (community_seq.nextval,#{commu_title},#{commu_content},#{commu_code},#{region_num},#{mem_num})")
	public void insertBoard(CommunityVO board);
	public CommunityVO selectBoard(Integer board_num);
	public void updateHit(Integer board_num);
	public void updateBoard(CommunityVO board);
	public void deleteBoard(Integer board_num);
}
