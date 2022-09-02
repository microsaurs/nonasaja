package kr.spring.sale.service;

import java.util.List;
import java.util.Map;

import kr.spring.sale.vo.SaleVO;

public interface SaleBoardService {
	//부모글
	public List<SaleVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(SaleVO board);
	public SaleVO selectBoard(Integer board_num);
	public void updateHit(Integer board_num);
	public void updateBoard(SaleVO board);
	public void deleteBoard(Integer board_num);
	public void deleteFile(Integer board_num);

	/*
	//부모글 좋아요
	public BoardFavVO selectFav(BoardFavVO fav);
	public int selectFavCount(Integer board_num);
	public void insertFav(BoardFavVO boardFav);
	public void deleteFav(Integer fav_num);

	//댓글
	public List<BoardReplyVO> selectListReply(
			                 Map<String,Object> map);
	public int selectRowCountReply(
			                 Map<String,Object> map);
	public BoardReplyVO selectReply(Integer re_num);
	public void insertReply(BoardReplyVO boardReply);
	public void updateReply(BoardReplyVO boardReply);
	public void deleteReply(Integer re_num);
	*/

}
