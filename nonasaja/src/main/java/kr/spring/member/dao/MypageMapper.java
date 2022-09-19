package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.community.vo.CommunityFavVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;

@Mapper
public interface MypageMapper {
	//=====레시피=====//
	//레시피 - 내가 쓴 글
	@Select("SELECT COUNT(*) FROM community_recipe_board where mem_num=#{mem_num}")
	public int selectRecipeCount(Integer mem_num);
	public List<RecipeVO> selectRecipeList(Map<String, Object> map);
	//레시피 - 내가 쓴 댓글
	@Select("SELECT COUNT(*) FROM community_recipe_reply where mem_num=#{mem_num}")
	public int selectRecipeReplyCount(Integer mem_num);
	public List<RecipeReplyVO> selectRecipeReplyList(Map<String, Object> map);
	//=====커뮤니티=====//
	//내가 쓴 글
	@Select("SELECT COUNT(*) FROM community_board where mem_num=#{mem_num}")
	public int selectCommuCount(Integer mem_num);
	public List<CommunityReplyVO> selectCommuList(Map<String, Object> map);
	//내가 쓴 댓글
	@Select("SELECT COUNT(*) FROM community_reply where mem_num=#{mem_num}")
	public int selectCommuReplyCount(Integer mem_num);
	public List<CommunityReplyVO> selectCommuReplyList(Map<String, Object> map);
	//좋아요 누른 글 (커뮤니티+레시피)
	@Select("SELECT COUNT(*) FROM fav where mem_num=#{mem_num} and commu_num IS NOT null")
	public int selectRecipeFavCount(Integer mem_num);
	public List<RecipeFavVO> selectRecipeFavList(Map<String, Object> map);

	//=====중고거래=====//
	//내가 올린 거래 (내가 쓴글)
	
	//내가 쓴 댓글
	
	//찜한물건 (좋아요)
	
	//1대1 채팅
}
