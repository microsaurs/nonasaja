package kr.spring.club.service;

import java.util.List;
import java.util.Map;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubVO;

public interface ClubService {
	//부모글
	public List<ClubVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(ClubVO club);
	public ClubVO selectBoard(Integer club_num);
	public void updateHit(Integer club_num);
	public void updateBoard(ClubVO club);
	public void deleteBoard(Integer club_num);
	public void deleteFile(Integer club_num);
	
	//부모글 좋아요
	public ClubFavVO selectFav(ClubFavVO fav);
	public int selectFavCount(Integer club_num);
	public void insertFav(ClubFavVO ClubFav);
	public void deleteFav(Integer club_num);

}
