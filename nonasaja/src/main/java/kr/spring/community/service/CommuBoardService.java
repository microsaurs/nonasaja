package kr.spring.community.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.community.vo.CommunityFavVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;

public interface CommuBoardService {
	//유머글
	public List<CommunityVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(CommunityVO board);
	public CommunityVO selectBoard(Integer commu_num);
	public void updateHit(Integer commu_num);
	public void updateBoard(CommunityVO board);
	public void deleteBoard(Integer commu_num);
	public void deleteFile(Integer commu_num);
	
	//유머 부모글 좋아요
	public CommunityFavVO selectFav(CommunityFavVO fav);
	public int selectFavCount(Integer commu_num);
	public void insertFav(CommunityFavVO boardFav);
	public void deleteFav(Integer fav_num);
	
	//유머글 댓글
	public List<CommunityReplyVO> selectListReply(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public CommunityReplyVO selectReply(Integer reply_num);
	public void insertReply(CommunityReplyVO boardReply);
	public void updateReply(CommunityReplyVO boardReply);
	public void deleteReply(Integer reply_num);
	
	//레시피
	public List<RecipeVO> selectList2(Map<String,Object> map);
	public int selectRowCount2(Map<String,Object> map);
	public void insertBoard2(RecipeVO board);
	public RecipeVO selectBoard2(Integer commu_num);
	public void updateHit2(Integer commu_num);
	public void updateBoard2(RecipeVO board);
	public void deleteBoard2(Integer commu_num);
	public void deleteFile2(Integer commu_num);
	
	//레시피 부모글 좋아요
	public RecipeFavVO selectFav2(RecipeFavVO fav);
	public int selectFavCount2(Integer commu_num);
	public void insertFav2(RecipeFavVO boardFav);
	public void deleteFav2(Integer fav_num);
	
	//레시피글 댓글
	public List<RecipeReplyVO> selectListReply2(Map<String,Object> map);
	public int selectRowCountReply2(Map<String,Object> map);
	public RecipeReplyVO selectReply2(Integer reply_num);
	public void insertReply2(RecipeReplyVO boardReply);
	public void updateReply2(RecipeReplyVO boardReply);
	public void deleteReply2(Integer reply_num);	
	
	
	
	
}
