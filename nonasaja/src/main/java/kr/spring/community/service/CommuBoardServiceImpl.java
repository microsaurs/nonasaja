package kr.spring.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.community.dao.CommuBoardMapper;
import kr.spring.community.vo.CommunityVO;

@Service
@Transactional
public class CommuBoardServiceImpl implements CommuBoardService{

	@Autowired
	private CommuBoardMapper boardMapper;
	
	@Override
	public List<CommunityVO> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return boardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(CommunityVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public CommunityVO selectBoard(Integer commu_num) {
		return boardMapper.selectBoard(commu_num);
	}

	@Override
	public void updateHit(Integer commu_num) {
		boardMapper.updateHit(commu_num);
	}

	@Override
	public void updateBoard(CommunityVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer commu_num) {
		//부모글 삭제
		boardMapper.deleteBoard(commu_num);
	}

	
	
	
	
	
	
	
	
}
