package kr.spring.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.community.dao.CommuBoardMapper;
import kr.spring.community.vo.CommunityFavVO;
import kr.spring.community.vo.CommunityReplyVO;
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
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		boardMapper.deleteReplyByBoardNum(commu_num);
		//부모글 삭제
		boardMapper.deleteBoard(commu_num);
	}

	@Override
	public void deleteFile(Integer commu_num) {
		boardMapper.deleteFile(commu_num);
	}
	
	@Override
	public CommunityFavVO selectFav(CommunityFavVO fav) {
		return boardMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer commu_num) {
		return boardMapper.selectFavCount(commu_num);
	}

	@Override
	public void insertFav(CommunityFavVO boardFav) {
		boardMapper.insertFav(boardFav);
	}

	@Override
	public void deleteFav(Integer fav_num) {
		boardMapper.deleteFav(fav_num);
	}

	@Override
	public List<CommunityReplyVO> selectListReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CommunityReplyVO selectReply(Integer reply_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertReply(CommunityReplyVO boardReply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReply(CommunityReplyVO boardReply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReply(Integer reply_num) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
	
	
}
