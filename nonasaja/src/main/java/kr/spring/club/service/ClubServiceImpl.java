package kr.spring.club.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.club.dao.ClubMapper;
import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubReplyVO;
import kr.spring.club.vo.ClubRereplyVO;
import kr.spring.club.vo.ClubVO;
import kr.spring.join.dao.JoinMapper;

@Service
@Transactional
public class ClubServiceImpl implements ClubService{

	@Autowired
	private ClubMapper clubMapper;
	
	@Autowired
	private JoinMapper joinMapper;
	
	@Override
	public List<ClubVO> selectList(Map<String, Object> map) {
		return clubMapper.selectList(map);
	}
	
	@Override
	public int selectRowCount(Map<String, Object> map) {
		return clubMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(ClubVO club) {
		clubMapper.insertBoard(club);
	}

	@Override
	public ClubVO selectBoard(Integer club_num) {
		return clubMapper.selectBoard(club_num);
	}

	@Override
	public void updateHit(Integer club_num) {
		clubMapper.updateHit(club_num);
	}

	@Override
	public void updateBoard(ClubVO club) {
		clubMapper.updateBoard(club);
	}
	
	@Override
	public void deleteBoard(Integer club_num) {
		//부모글 좋아요 삭제
		clubMapper.deleteFavByBoardNum(club_num);
		//대댓글이 존재하면 댓글을 먼저 삭제하고 부모글 삭제
		clubMapper.deleteRereplyByBoardNum(club_num);
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		clubMapper.deleteReplyByBoardNum(club_num);
		//가입한 동호회 먼저 삭제하고 부모글삭제
		joinMapper.deleteByJoinNum(club_num);
		//부모글 삭제
		clubMapper.deleteBoard(club_num);
		
	}


	 @Override 
	 public void deleteFile(Integer club_num) {
	 clubMapper.deleteFile(club_num); }


	@Override
	public ClubFavVO selectFav(ClubFavVO fav) {
		return clubMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer club_num) {
		return clubMapper.selectFavCount(club_num);
	}

	@Override
	public void insertFav(ClubFavVO boardFav) {
		clubMapper.insertFav(boardFav);
	}

	@Override
	public void deleteFav(Integer fav_num) {
		clubMapper.deleteFav(fav_num);
	}

	@Override
	public List<ClubReplyVO> selectListReply(Map<String, Object> map) {
		return clubMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return clubMapper.selectRowCountReply(map);
	}

	@Override
	public ClubReplyVO selectReply(Integer reply_num) {
		return clubMapper.selectReply(reply_num);
	}

	@Override
	public void insertReply(ClubReplyVO boardReply) {
		clubMapper.insertReply(boardReply);
		
	}

	@Override
	public void updateReply(ClubReplyVO boardReply) {
		clubMapper.updateReply(boardReply);

		
	}

	@Override
	public void deleteReply(Integer reply_num) {
		//대댓글 먼저 삭제하고
		clubMapper.deleteRereplyByBoardNum2(reply_num);
		//부모글 삭제
		clubMapper.deleteReply(reply_num);
		
	}

	@Override
	public List<ClubRereplyVO> selectListRereply(Map<String, Object> map) {
		return clubMapper.selectListRereply(map);
	}

	@Override
	public int selectRowCountRereply(Map<String, Object> map) {
		return clubMapper.selectRowCountRereply(map);
	}

	@Override
	public ClubRereplyVO selectRereply(Integer rereply_num) {
		return clubMapper.selectRereply(rereply_num);
	
	}

	@Override
	public void insertRereply(ClubRereplyVO boardRereply) {
		clubMapper.insertRereply(boardRereply);
		
	}

	@Override
	public void updateRereply(ClubRereplyVO boardRereply) {
		clubMapper.updateRereply(boardRereply);
		
	}

	@Override
	public void deleteRereply(Integer rereply_num) {
		clubMapper.deleteRereply(rereply_num);
		
	}

	@Override
	public void deleteRereplyByBoardNum2(Integer reply_num) {
		clubMapper.deleteRereplyByBoardNum2(reply_num);
		
	}
	
	/*
		 * 
		 * @Override public int selectClubCount(Integer mem_num) { return
		 * clubMapper.selectClubCount(mem_num); }
		 */

}
