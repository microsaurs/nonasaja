package kr.spring.club.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubVO;

@Mapper
public interface ClubMapper {
	//부모글
		public List<ClubVO> selectList(Map<String,Object> map);
		public int selectRowCount(Map<String,Object> map);
		@Insert("INSERT INTO club_board (club_num,club_title,club_name,"
				+ "club_leader,club_content,"
				+ "club_code,club_limit,club_pre,club_hit,"
				+ "club_img,club_img_name,club_date,club_age,club_gender,club_recruit,region_num) "
				+ "VALUES (club_board_seq.nextval,#{club_title},#{club_name},"
				+ "#{club_leader},#{club_content},#{club_code},#{club_limit},"
				+ "#{club_pre},#{club_hit},#{club_img},#{club_img_name},#{club_date},#{club_age},#{club_gender},#{club_recruit},#{region_num})")
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
		public void deleteFavByBoardNum(Integer board_num);

		
}
