package kr.spring.club.service;

import java.util.List;
import java.util.Map;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubReplyVO;
import kr.spring.club.vo.ClubRereplyVO;
import kr.spring.club.vo.ClubVO;


public interface ClubService {
	//부모글
	public List<ClubVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(ClubVO club);
	public ClubVO selectBoard(Integer club_num);
	public void updateHit(Integer club_num);
	public void updateBoard(ClubVO club);
	public void deleteBoard(Integer club_num);
	public void deleteFile(Integer club_num);
	
	//부모글 좋아요
	public ClubFavVO selectFav(ClubFavVO fav);
	public int selectFavCount(Integer club_num);
	public void insertFav(ClubFavVO ClubFav);
	public void deleteFav(Integer club_num);

	//댓글
	public List<ClubReplyVO> selectListReply(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public ClubReplyVO selectReply(Integer reply_num);
	public void insertReply(ClubReplyVO boardReply);
	public void updateReply(ClubReplyVO boardReply);
	public void deleteReply(Integer reply_num);
	
	/* public int selectClubCount(Integer mem_num); */
	
	//대댓글
	public List<ClubRereplyVO> selectListRereply(Map<String,Object> map);
	public int selectRowCountRereply(Map<String,Object> map);
	public ClubRereplyVO selectRereply(Integer rereply_num);
	public void insertRereply(ClubRereplyVO boardRereply);
	public void updateRereply(ClubRereplyVO boardRereply);
	public void deleteRereply(Integer rereply_num);
	public void deleteRereplyByBoardNum2(Integer reply_num);
	
}
