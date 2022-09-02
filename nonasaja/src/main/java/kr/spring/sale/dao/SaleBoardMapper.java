package kr.spring.sale.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.sale.vo.SaleVO;

@Mapper
public interface SaleBoardMapper {
	//부모글
	public List<SaleVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO sale_board (board_num, title, content, deadline, reg_date, mem_num) "
			+ "VALUES (sale_board_seq.nextval, #{title}, #{content}, #{deadline}, SYSDATE, #{mem_num}")
	public void insertBoard(SaleVO board);
	@Select("SELECT * FROM sale_board b JOIN member m "
			+ "USING(mem_num) JOIN member_detail d "
			+ "USING(mem_num) WHERE b.board_num=#{board_num}")
	public SaleVO selectBoard(Integer board_num);
	@Update("UPDATE sale_board SET hit=hit+1 WHERE board_num=#{board_num}")
	public void updateHit(Integer board_num);
	public void updateBoard(SaleVO board);
	@Delete("DELETE FROM sale_board WHERE board_num=#{board_num}")
	public void deleteBoard(Integer board_num);
	@Update("UPDATE sale_board SET uploadfile='',"
			+ "filename='' WHERE board_num=#{board_num}")
	public void deleteFile(Integer board_num);
	/*
	//부모글 좋아요
	@Select("SELECT * FROM spboard_fav "
			+ "WHERE board_num=#{board_num} AND mem_num=#{mem_num}")
	public BoardFavVO selectFav(BoardFavVO fav);
	@Select("SELECT COUNT(*) FROM spboard_fav "
			+ "WHERE board_num=#{board_num}")
	public int selectFavCount(Integer board_num);
	@Insert("INSERT INTO spboard_fav (fav_num,board_num,mem_num) "
			+ "VALUES (fav_seq.nextval,#{board_num},#{mem_num})")
	public void insertFav(BoardFavVO boardFav);
	@Delete("DELETE FROM spboard_fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM spboard_fav WHERE board_num=#{board_num}")
	public void deleteFavByBoardNum(Integer board_num);
	
	//댓글
	public List<BoardReplyVO> selectListReply(
			                  Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM spboard_reply b "
			+ "JOIN spmember m ON b.mem_num=m.mem_num "
			+ "WHERE board_num=#{board_num}")
	public int selectRowCountReply(Map<String,Object> map);
	public BoardReplyVO selectReply(Integer re_num);
	@Insert("INSERT INTO spboard_reply (re_num,"
			+ "re_content,re_ip,board_num,mem_num) "
			+ "VALUES (reply_seq.nextval,#{re_content},"
			+ "#{re_ip},#{board_num},#{mem_num})")
	public void insertReply(BoardReplyVO boardReply);
	public void updateReply(BoardReplyVO boardReply);
	public void deleteReply(Integer re_num);
	public void deleteReplyByBoardNum(
			                       Integer board_num);
			                       */
}





