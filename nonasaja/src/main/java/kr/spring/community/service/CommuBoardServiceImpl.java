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
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;

@Service
@Transactional
public class CommuBoardServiceImpl implements CommuBoardService{

	@Autowired
	private CommuBoardMapper boardMapper;
	
	//=====================유머 게시판==========================//
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
		return boardMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return boardMapper.selectRowCountReply(map);
	}

	@Override
	public CommunityReplyVO selectReply(Integer reply_num) {
		return boardMapper.selectReply(reply_num);
	}

	@Override
	public void insertReply(CommunityReplyVO boardReply) {
		boardMapper.insertReply(boardReply);
	}

	@Override
	public void updateReply(CommunityReplyVO boardReply) {
		boardMapper.updateReply(boardReply);
	}

	@Override
	public void deleteReply(Integer reply_num) {
		boardMapper.deleteReply(reply_num);
	}

	//=====================레시피 게시판==========================//
	@Override
	public List<RecipeVO> selectList2(Map<String, Object> map) {
		return boardMapper.selectList2(map);
	}

	@Override
	public int selectRowCount2(Map<String, Object> map) {
		return boardMapper.selectRowCount2(map);
	}

	@Override
	public void insertBoard2(RecipeVO board) {
		boardMapper.insertBoard2(board);
	}

	@Override
	public RecipeVO selectBoard2(Integer commu_num) {
		return boardMapper.selectBoard2(commu_num);
	}

	@Override
	public void updateHit2(Integer commu_num) {
		boardMapper.updateHit2(commu_num);
	}

	@Override
	public void updateBoard2(RecipeVO board) {
		boardMapper.updateBoard2(board);
	}

	@Override
	public void deleteBoard2(Integer commu_num) {
		boardMapper.deleteBoard2(commu_num);
	}

	@Override
	public void deleteFile2(Integer commu_num) {
		boardMapper.deleteFile2(commu_num);
	}

	@Override
	public RecipeFavVO selectFav2(RecipeFavVO fav) {
		return boardMapper.selectFav2(fav);
	}

	@Override
	public int selectFavCount2(Integer commu_num) {
		return boardMapper.selectFavCount2(commu_num);
	}

	@Override
	public void insertFav2(RecipeFavVO boardFav) {
		boardMapper.insertFav2(boardFav);
	}

	@Override
	public void deleteFav2(Integer fav_num) {
		boardMapper.deleteFav2(fav_num);
	}

	@Override
	public List<RecipeReplyVO> selectListReply2(Map<String, Object> map) {
		return boardMapper.selectListReply2(map);
	}

	@Override
	public int selectRowCountReply2(Map<String, Object> map) {
		return boardMapper.selectRowCountReply2(map);
	}

	@Override
	public RecipeReplyVO selectReply2(Integer reply_num) {
		return boardMapper.selectReply2(reply_num);
	}

	@Override
	public void insertReply2(RecipeReplyVO boardReply) {
		boardMapper.insertReply2(boardReply);
	}

	@Override
	public void updateReply2(RecipeReplyVO boardReply) {
		boardMapper.updateReply2(boardReply);
	}

	@Override
	public void deleteReply2(Integer reply_num) {
		boardMapper.deleteReply2(reply_num);
	}


	
	
	
	
	
	
	
	
}
