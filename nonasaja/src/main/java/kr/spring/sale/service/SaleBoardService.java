package kr.spring.sale.service;

import java.util.List;
import java.util.Map;

import kr.spring.sale.vo.SaleVO;
import kr.spring.sale.vo.SaleReplyVO;

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

	//댓글
	public List<SaleReplyVO> selectListReply(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public SaleReplyVO selectReply(Integer reply_num);
	public void insertReply(SaleReplyVO boardReply);
	public void updateReply(SaleReplyVO boardReply);
	public void deleteReply(Integer reply_num);


}
