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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBoard(CommunityVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public CommunityVO selectBoard(Integer board_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Integer board_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(CommunityVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(Integer board_num) {
		// TODO Auto-generated method stub
		
	}

}
