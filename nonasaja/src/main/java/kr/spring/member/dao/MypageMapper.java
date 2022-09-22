package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubVO;
import kr.spring.community.vo.CommunityFavVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;
import kr.spring.join.vo.JoinVO;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.used.vo.UsedVO;

@Mapper
public interface MypageMapper {
	/*
	 * map에 넣어야 하는 값 : startRow, EndRow, Mem_num
	 */
	
	//=====레시피=====//
	//레시피 - 내가 쓴 글
	@Select("SELECT COUNT(*) FROM community_recipe_board WHERE mem_num=#{mem_num}")
	public int selectRecipeCount(Integer mem_num);
	//xml
	public List<RecipeVO> selectRecipeList(Map<String, Object> map);
	//레시피 - 내가 쓴 댓글
	@Select("SELECT COUNT(*) FROM community_recipe_reply WHERE mem_num=#{mem_num}")
	public int selectRecipeReplyCount(Integer mem_num);
	public List<RecipeReplyVO> selectRecipeReplyList(Map<String, Object> map);
	//=====커뮤니티=====//
	//내가 쓴 글
	@Select("SELECT COUNT(*) FROM community_board WHERE mem_num=#{mem_num}")
	public int selectCommuCount(Integer mem_num);
	public List<CommunityVO> selectCommuList(Map<String, Object> map);
	//내가 쓴 댓글
	@Select("SELECT COUNT(*) FROM community_reply WHERE mem_num=#{mem_num}")
	public int selectCommuReplyCount(Integer mem_num);
	public List<CommunityReplyVO> selectCommuReplyList(Map<String, Object> map);
	//좋아요 누른 글 (커뮤니티+레시피)
	@Select("SELECT COUNT(*) FROM fav WHERE mem_num=#{mem_num} and commu_num IS NOT NULL")
	public int selectRecipeFavCount(Integer mem_num);
	public List<RecipeFavVO> selectRecipeFavList(Map<String, Object> map);

	//=====중고거래=====//
	//내가 올린 거래 (내가 쓴글)
	@Select("SELECT COUNT(*) FROM used_board WHERE mem_num=#{mem_num}")
	public int selectUsedCount(Integer mem_num);
	public List<UsedVO> selectUsedList(Map<String,Object> map);
	//내가 쓴 댓글
	@Select("SELECT COUNT(*) FROM used_reply WHERE mem_num=#{mem_num}")
	public int selectUsedReplyCount(Integer mem_num);
	public List<UsedReplyVO> selectUsedReplyList(Map<String,Object> map);
	//찜한물건 (좋아요)
	@Select("SELECT COUNT(*) FROM fav WHERE mem_num=#{mem_num} and used_num IS NOT NULL")
	public int selectUsedFavCount(Integer mem_num);
	public List<UsedVO> selectUsedFavList(Map<String,Object> map);
	//1대1 채팅
	//미구현
	
	//=====동호회=====//
	//내가 가입한 동호회 개수
	@Select("SELECT COUNT(*) FROM club_board WHERE club_leader=#{club_leader}")
	public int selectClubCount(Integer club_leader);
	//내가 가입한 동호회 목록
	public List<ClubVO> selectListJoin(Map<String,Object> map);
	//내가 찜한 동호회 목록
	public List<ClubFavVO> selectClubFavList(Map<String,Object> map);
}
