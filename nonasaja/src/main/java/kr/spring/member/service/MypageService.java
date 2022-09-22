package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.used.vo.UsedVO;

public interface MypageService {
		//=====레시피=====//
		//레시피 - 내가 쓴 글
		public int selectRecipeCount(Integer mem_num);
		public List<RecipeVO> selectRecipeList(Map<String, Object> map);
		//레시피 - 내가 쓴 댓글
		public int selectRecipeReplyCount(Integer mem_num);
		public List<RecipeReplyVO> selectRecipeReplyList(Map<String, Object> map);
		//=====커뮤니티=====//
		//내가 쓴 글
		public int selectCommuCount(Integer mem_num);
		public List<CommunityVO> selectCommuList(Map<String, Object> map);
		//내가 쓴 댓글
		public int selectCommuReplyCount(Integer mem_num);
		public List<CommunityReplyVO> selectCommuReplyList(Map<String, Object> map);
		//좋아요 누른 글 (커뮤니티+레시피)
		public int selectRecipeFavCount(Integer mem_num);
		public List<RecipeFavVO> selectRecipeFavList(Map<String, Object> map);

		//=====중고거래=====//
		//내가 올린 거래 (내가 쓴글)
		public int selectUsedCount(Integer mem_num);
		public List<UsedVO> selectUsedList(Map<String,Object> map);
		//내가 쓴 댓글
		public int selectUsedReplyCount(Integer mem_num);
		public List<UsedReplyVO> selectUsedReplyList(Map<String,Object> map);
		//찜한물건 (좋아요)
		public int selectUsedFavCount(Integer mem_num);
		public List<UsedVO> selectUsedFavList(Map<String,Object> map);
		
		//=====동호회=====// 
		public int selectClubCount(Integer club_leader);
		//내가 가입한 동호회 목록
		public List<ClubVO> selectListJoin(Map<String,Object> map);
		//내가 찜한 동호회 목록
		public List<ClubFavVO> selectClubFavList(Map<String,Object> map);
}
