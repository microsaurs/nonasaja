package kr.spring.sale.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.sale.vo.SaleVO;
import kr.spring.sale.vo.SaleReplyVO;

@Mapper
public interface SaleBoardMapper {
	//부모글
	public List<SaleVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO sale_board (board_num, title, content, deadline, reg_date, mem_num) "
			+ "VALUES (sale_board_seq.nextval, #{title}, #{content}, #{deadline}, SYSDATE, #{mem_num})")
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

	public void deleteFile(Integer board_num);

	//댓글
	public List<SaleReplyVO> selectListReply(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM sale_reply b "
			+ "JOIN member m ON b.mem_num=m.mem_num "
			+ "WHERE board_num=#{board_num}")
	public int selectRowCountReply(Map<String,Object> map);
	public SaleReplyVO selectReply(Integer reply_num);
	@Insert("INSERT INTO sale_reply (reply_num,reply_content,board_num,mem_num) "
			+ "VALUES (sale_reply_seq.nextval,#{reply_content},#{board_num},#{mem_num})")
	public void insertReply(SaleReplyVO boardReply);
	public void updateReply(SaleReplyVO boardReply);
	public void deleteReply(Integer reply_num);
	public void deleteReplyByBoardNum(Integer board_num);

}





