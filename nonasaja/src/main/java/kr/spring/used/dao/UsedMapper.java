package kr.spring.used.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.used.vo.UsedFavVO;
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
	public void updateHit(Integer used_num);
	public void updateUsed(UsedVO used);
	public void deleteUsed(Integer used_num);
	public void deleteFile(Integer used_num);
	
	//부모글 좋아요
	public UsedFavVO selectFav(UsedFavVO fav);
	public int selectFavCount(Integer used_num);
	public void insertFav(UsedFavVO usedFav);
	public void deleteFav(Integer fav_num);
}