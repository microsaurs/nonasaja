package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.club.vo.ClubFavVO;
import kr.spring.club.vo.ClubVO;
import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;
import kr.spring.join.vo.JoinVO;
import kr.spring.member.dao.MypageMapper;
import kr.spring.used.vo.UsedFavVO;
import kr.spring.used.vo.UsedReplyVO;
import kr.spring.used.vo.UsedVO;

@Service
@Transactional
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageMapper mypageMapper;
	
	@Override
	public int selectRecipeCount(Integer mem_num) {
		return mypageMapper.selectRecipeCount(mem_num);
	}

	@Override
	public List<RecipeVO> selectRecipeList(Map<String, Object> map) {
		return mypageMapper.selectRecipeList(map);
	}

	@Override
	public int selectRecipeReplyCount(Integer mem_num) {
		return mypageMapper.selectRecipeCount(mem_num);
	}

	@Override
	public List<RecipeReplyVO> selectRecipeReplyList(Map<String, Object> map) {
		return mypageMapper.selectRecipeReplyList(map);
	}

	@Override
	public int selectCommuCount(Integer mem_num) {
		return mypageMapper.selectCommuCount(mem_num);
	}

	@Override
	public List<CommunityVO> selectCommuList(Map<String, Object> map) {
		return mypageMapper.selectCommuList(map);
	}

	@Override
	public int selectCommuReplyCount(Integer mem_num) {
		return mypageMapper.selectCommuReplyCount(mem_num);
	}

	@Override
	public List<CommunityReplyVO> selectCommuReplyList(Map<String, Object> map) {
		return mypageMapper.selectCommuReplyList(map);
	}

	@Override
	public int selectRecipeFavCount(Integer mem_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RecipeFavVO> selectRecipeFavList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectUsedCount(Integer mem_num) {
		return mypageMapper.selectUsedCount(mem_num);
	}

	@Override
	public List<UsedVO> selectUsedList(Map<String, Object> map) {
		return mypageMapper.selectUsedList(map);
	}

	@Override
	public int selectUsedReplyCount(Integer mem_num) {
		return mypageMapper.selectUsedReplyCount(mem_num);
	}

	@Override
	public List<UsedReplyVO> selectUsedReplyList(Map<String, Object> map) {
		return mypageMapper.selectUsedReplyList(map);
	}

	@Override
	public int selectUsedFavCount(Integer mem_num) {
		return mypageMapper.selectUsedFavCount(mem_num);
	}

	@Override
	public List<UsedVO> selectUsedFavList(Map<String, Object> map) {
		return mypageMapper.selectUsedFavList(map);
	}



	@Override
	public List<ClubFavVO> selectClubFavList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectClubCount(Integer club_leader) {
		return mypageMapper.selectClubCount(club_leader);
	}

	@Override
	public List<ClubVO> selectListJoin(Map<String, Object> map) {
		return mypageMapper.selectListJoin(map);
	}

}
