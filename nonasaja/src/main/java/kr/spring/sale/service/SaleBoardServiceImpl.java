package kr.spring.sale.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.sale.dao.SaleBoardMapper;
import kr.spring.sale.vo.SaleVO;

@Service
@Transactional
public class SaleBoardServiceImpl implements SaleBoardService{

	@Autowired
	private SaleBoardMapper boardMapper;
	
	@Override
	public List<SaleVO> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return boardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(SaleVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public SaleVO selectBoard(Integer board_num) {
		return boardMapper.selectBoard(board_num);
	}

	@Override
	public void updateHit(Integer board_num) {
		boardMapper.updateHit(board_num);
	}

	@Override
	public void updateBoard(SaleVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer board_num) {
		//부모글 좋아요 삭제
		//boardMapper.deleteFavByBoardNum(board_num);
		//부모글 삭제
		boardMapper.deleteBoard(board_num);
	}

	@Override
	public void deleteFile(Integer board_num) {
		boardMapper.deleteFile(board_num);
	}

	/*
	@Override
	public BoardFavVO selectFav(BoardFavVO fav) {
		return boardMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer board_num) {
		return boardMapper.selectFavCount(board_num);
	}

	@Override
	public void insertFav(BoardFavVO boardFav) {
		boardMapper.insertFav(boardFav);
	}

	@Override
	public void deleteFav(Integer fav_num) {
		boardMapper.deleteFav(fav_num);
	}

	@Override
	public List<BoardReplyVO> selectListReply(Map<String, Object> map) {
		return boardMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return boardMapper.selectRowCountReply(map);
	}

	@Override
	public BoardReplyVO selectReply(Integer re_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertReply(BoardReplyVO boardReply) {
		boardMapper.insertReply(boardReply);
	}

	@Override
	public void updateReply(BoardReplyVO boardReply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReply(Integer re_num) {
		// TODO Auto-generated method stub
		
	}
	*/

}
