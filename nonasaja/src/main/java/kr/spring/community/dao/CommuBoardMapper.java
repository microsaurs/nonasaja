package kr.spring.community.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.community.vo.CommunityFavVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;

@Mapper
public interface CommuBoardMapper {
	//유머글
	public List<CommunityVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO community_board (commu_num,commu_title,commu_content,uploadfile,filename,uploadfile2,filename2,uploadfile3,filename3,commu_code,mem_num) VALUES (community_seq.nextval,#{commu_title},#{commu_content},#{uploadfile},#{filename},#{uploadfile2},#{filename2},#{uploadfile3},#{filename3},#{commu_code},#{mem_num})")
	public void insertBoard(CommunityVO board);
	@Select("SELECT * FROM community_board b JOIN member m USING(mem_num) JOIN member_detail d USING(mem_num) WHERE b.commu_num=#{commu_num}")
	public CommunityVO selectBoard(Integer commu_num);
	@Update("UPDATE community_board SET commu_hit=commu_hit+1 WHERE commu_num=#{commu_num}")
	public void updateHit(Integer commu_num);
	public void updateBoard(CommunityVO board);
	@Delete("DELETE FROM community_board WHERE commu_num=#{commu_num}")
	public void deleteBoard(Integer commu_num);
	@Update("UPDATE community_board SET uploadfile='',"
			+ "filename='' WHERE commu_num=#{commu_num}")
	public void deleteFile(Integer commu_num);
	
	
	//부모글 좋아요
	@Select("SELECT * FROM fav "
			+ "WHERE commu_num=#{commu_num} AND mem_num=#{mem_num}")
	public CommunityFavVO selectFav(CommunityFavVO fav);
	@Select("SELECT COUNT(*) FROM fav "
			+ "WHERE commu_num=#{commu_num}")
	public int selectFavCount(Integer commu_num);
	@Insert("INSERT INTO fav (fav_num,commu_num,mem_num) "
			+ "VALUES (fav_seq.nextval,#{commu_num},#{mem_num})")
	public void insertFav(CommunityFavVO boardFav);
	@Delete("DELETE FROM fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM fav WHERE commu_num=#{commu_num}")
	public void deleteFavByBoardNum(Integer commu_num);
	
	//댓글
	public List<CommunityReplyVO> selectListReply(
			                  Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM community_reply b "
			+ "JOIN member m ON b.mem_num=m.mem_num "
			+ "WHERE commu_num=#{commu_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Select("SELECT * FROM community_reply WHERE reply_num=#{reply_num}")
	public CommunityReplyVO selectReply(Integer reply_num);
	@Insert("INSERT INTO community_reply (reply_num,"
			+ "reply_content,commu_num,mem_num) "
			+ "VALUES (commureply_seq.nextval,#{reply_content},"
			+ "#{commu_num},#{mem_num})")
	public void insertReply(CommunityReplyVO boardReply);
	@Update("UPDATE community_reply SET "
			+ "reply_content=#{reply_content} WHERE reply_num=#{reply_num}")
	public void updateReply(CommunityReplyVO boardReply);
	@Delete("DELETE FROM community_reply WHERE reply_num=#{reply_num}")
	public void deleteReply(Integer re_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM community_reply "
			+ "WHERE commu_num=#{commu_num}")
	public void deleteReplyByBoardNum(
			                       Integer board_num);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
