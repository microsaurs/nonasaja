package kr.spring.community.service;

import java.util.List;
import java.util.Map;


import kr.spring.community.vo.CommunityVO;

public interface CommuBoardService {
	//유머글
	public List<CommunityVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(CommunityVO board);
	public CommunityVO selectBoard(Integer commu_num);
	public void updateHit(Integer commu_num);
	public void updateBoard(CommunityVO board);
	public void deleteBoard(Integer commu_num);
}
