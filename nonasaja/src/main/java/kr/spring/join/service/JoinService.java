package kr.spring.join.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.join.vo.JoinVO;

public interface JoinService {
	//동호회 가입하기
	public void insertJoin(JoinVO join);
	//동호회 목록
	public List<JoinVO> selectListJoin(int mem_num);
	//동호회 상세
	public JoinVO selectJoin(JoinVO join);
	//조인 번호로 join 정보 반환
	public JoinVO selectJoinByJoinNum(int join_num);
	//동호회 삭제
	public void deleteJoin(int join_num);
	//한 동호회에 가입중인 사람 수 구하기
	public int selectJoinCount(int club_num);
	public void deleteByJoinNum(Integer club_num);
}
