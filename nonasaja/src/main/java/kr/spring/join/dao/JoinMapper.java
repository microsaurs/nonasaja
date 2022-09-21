package kr.spring.join.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.join.vo.JoinVO;


@Mapper
public interface JoinMapper {
	//동호회 가입하기
	@Insert("INSERT INTO join (join_num,club_num,"
			+ "join_reg_date,mem_num) VALUES ("
			+ "join_seq.nextval,#{club_num},sysdate,"
			+ "#{mem_num})")
	public void insertJoin(JoinVO join);
	//동호회 목록
	public List<JoinVO> selectListJoin(int mem_num);
	//동호회 상세
	@Select("SELECT * FROM join WHERE "
			+ "club_num=#{club_num} AND mem_num = #{mem_num}")
	public JoinVO selectJoin(JoinVO join);
	//동호회 삭제
	@Delete("DELETE FROM join WHERE join_num=#{join_num}")
	public void deleteJoin(int join_num);
}







