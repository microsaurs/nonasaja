package kr.spring.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubReplyVO;
import kr.spring.club.vo.ClubVO;

@Mapper
public interface ClubMapper {
	//부모글
		public List<ClubVO> selectList(Map<String,Object> map);
		public int selectRowCount(Map<String,Object> map);
		@Insert("INSERT INTO club_board (club_num,club_title,club_name,"
				+ "club_leader,club_content,"
				+ "club_code,club_limit,club_pre,club_hit,"
				+ "club_img,club_img_name,club_date,club_age,club_gender,club_recruit,club_region) "
				+ "VALUES (club_board_seq.nextval,#{club_title},#{club_name},"
				+ "#{club_leader},#{club_content},#{club_code},#{club_limit},"
				+ "#{club_pre},#{club_hit},#{club_img},#{club_img_name},#{club_date},#{club_age},#{club_gender},#{club_recruit},#{club_region})")
		public void insertBoard(ClubVO club);
		@Select("SELECT * FROM club_board c JOIN member m ON c.club_leader = m.mem_num JOIN member_detail d ON m.mem_num = d.mem_num WHERE c.club_num=#{club_num}")
		public ClubVO selectBoard(Integer club_num);
		@Update("UPDATE club_board SET club_hit=club_hit+1 WHERE club_num=#{club_num}")
		public void updateHit(Integer club_num);
		public void updateBoard(ClubVO club);
		@Delete("DELETE FROM club_board WHERE club_num=#{club_num}")
		public void deleteBoard(Integer club_num);
		@Update("UPDATE club_board SET club_img='',"
				+ "club_img_name='' WHERE club_num=#{club_num}")
		public void deleteFile(Integer club_num);
		
		//부모글 좋아요
		@Select("SELECT * FROM fav "
				+ "WHERE club_num=#{club_num} AND mem_num=#{mem_num}")
		public ClubFavVO selectFav(ClubFavVO fav);
		@Select("SELECT COUNT(*) FROM fav "
				+ "WHERE club_num=#{club_num}")
		public int selectFavCount(Integer club_num);
		@Insert("INSERT INTO fav (fav_num,club_num,mem_num) "
				+ "VALUES (fav_seq.nextval,#{club_num},#{mem_num})")
		public void insertFav(ClubFavVO clubFav);
		@Delete("DELETE FROM fav WHERE fav_num=#{fav_num}")
		public void deleteFav(Integer fav_num);
		@Delete("DELETE FROM fav WHERE club_num=#{club_num}")
		public void deleteFavByBoardNum(Integer club_num);
		
		//댓글
		public List<ClubReplyVO> selectListReply(
				                  Map<String,Object> map);
		@Select("SELECT COUNT(*) FROM club_reply b "
				+ "JOIN member m ON b.mem_num=m.mem_num "
				+ "WHERE club_num=#{club_num}")
		public int selectRowCountReply(Map<String,Object> map);
		@Select("SELECT * FROM club_reply WHERE reply_num=#{reply_num}")
		public ClubReplyVO selectReply(Integer reply_num);
		@Insert("INSERT INTO club_reply (reply_num,"
				+ "reply_content,club_num,mem_num) "
				+ "VALUES (club_reply_seq.nextval,#{reply_content},"
				+ "#{club_num},#{mem_num})")
		public void insertReply(ClubReplyVO boardReply);
		@Update("UPDATE club_reply SET "
				+ "reply_content=#{reply_content}"
				+ " WHERE reply_num=#{reply_num}")
		public void updateReply(ClubReplyVO boardReply);
		@Delete("DELETE FROM club_reply WHERE reply_num=#{reply_num}")
		public void deleteReply(Integer reply_num);
		//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
		@Delete("DELETE FROM club_reply "
				+ "WHERE club_num=#{club_num}")
		public void deleteReplyByBoardNum(
				                       Integer club_num);
		
		/*
		 * //내가 가입한 동호회 개수
		 * 
		 * @Select("SELECT COUNT(*) FROM join j JOIN club_board c USING(club_num) WHERE j.mem_num=#{mem_num}"
		 * ) public int selectClubCount(Integer mem_num);
		 */
}
