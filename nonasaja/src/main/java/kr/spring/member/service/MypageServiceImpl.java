package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.community.vo.CommunityReplyVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeFavVO;
import kr.spring.community.vo.RecipeReplyVO;
import kr.spring.community.vo.RecipeVO;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RecipeReplyVO> selectRecipeReplyList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CommunityReplyVO> selectCommuReplyList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UsedVO> selectUsedList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectUsedReplyCount(Integer mem_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UsedReplyVO> selectUsedReplyList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectUsedFavCount(Integer mem_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UsedFavVO> selectUsedFavList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
