package kr.spring.used.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.used.vo.UsedVO;

@Mapper
public interface UsedMapper {
	//부모글
	public List<UsedVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Insert("INSERT INTO used_board (used_num,title,"
			+ "content,uploadfile,filename,uploadfile2,filename2,uploadfile3,filename3,"
			+ "kind,price,status,category,trade,reg_date,hit,region_num,mem_num) "
			+ "VALUES (used_board_seq.nextval,#{title},#{content},#{uploadfile},#{filename},"
			+ "#{uploadfile2},#{filename2},#{uploadfile3},#{filename3},"
			+ "#{kind},#{price},#{status},#{category},#{trade},SYSDATE,#{hit},#{region_num},#{mem_num})")
	public void insertUsed(UsedVO used);
	@Select("SELECT * FROM used_board b JOIN member m "
			+ "USING(mem_num) JOIN member_detail d "
			+ "USING(mem_num) WHERE b.used_num=#{used_num}")
	public UsedVO selectUsed(Integer used_num);
	@Update("UPDATE used_board SET hit=hit+1 WHERE used_num=#{used_num}")
	public void updateHit(Integer used_num);
	public void updateUsed(UsedVO used);
	@Delete("DELETE FROM used_board WHERE used_num=#{used_num}")
	public void deleteUsed(Integer used_num);
	@Update("UPDATE used_board SET uploadfile='',"
			+ "filename='', uploadfile2='',filename2='',uploadfile3='',"
			+ "filename3='' WHERE used_num=#{used_num}")
	public void deleteFile(Integer used_num);
	
	//댓글
	public List<UsedReplyVO> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM used_reply b "
			+ "JOIN member m ON b.mem_num=m.mem_num "
			+ "WHERE used_num=#{used_num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Select("SELECT * FROM used_reply WHERE re_num=#{re_num}")
	public UsedReplyVO selectReply(Integer re_num);
	@Insert("INSERT INTO used_reply (re_num,"
			+ "re_content,used_num,mem_num) "
			+ "VALUES (reply_seq.nextval,#{re_content},"
			+ "#{used_num},#{mem_num})")
	public void insertReply(UsedReplyVO usedReply);
	public void updateReply(UsedReplyVO usedReply);
	public void deleteReply(Integer re_num);
	
	
	//부모글 좋아요
	@Select("SELECT * FROM fav "
			+ "WHERE used_num=#{used_num} AND mem_num=#{mem_num}")
	public UsedFavVO selectFav(UsedFavVO fav);
	@Select("SELECT COUNT(*) FROM fav "
			+ "WHERE used_num=#{used_num}")
	public int selectFavCount(Integer used_num);
	@Insert("INSERT INTO fav (fav_num,used_num,mem_num) "
			+ "VALUES (fav_seq.nextval,#{used_num},#{mem_num})")
	public void insertFav(UsedFavVO usedFav);
	@Delete("DELETE FROM fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM fav WHERE used_num=#{used_num}")
	public void deleteFavByUsedNum(Integer used_num);
}