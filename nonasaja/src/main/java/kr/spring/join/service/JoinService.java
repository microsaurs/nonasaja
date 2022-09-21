package kr.spring.join.service;

import java.util.List;

import kr.spring.join.vo.JoinVO;

public interface JoinService {
	//동호회 가입하기
	public void insertJoin(JoinVO join);
	//동호회 목록
	public List<JoinVO> selectListJoin(int mem_num);
	//동호회 상세
	public JoinVO selectJoin(JoinVO join);
	//동호회 삭제
	public void deleteJoin(int join_num);
}
