package kr.spring.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.club.vo.ClubVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeVO;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.vo.ReviewVO;
import kr.spring.sale.vo.SaleVO;
import kr.spring.used.vo.UsedVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void insertMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
	}
	
	@Override
	public void insertNaverMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertNaverMember(member);
	}
	
	@Override
	public void insertKakaoMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertKakaoMember(member);
	}
	
	@Override
	public MemberVO selectCheckMember(String id) {
		return memberMapper.selectCheckMember(id);
	}

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
		memberMapper.updateMember_detail(member);
	}

	@Override
	public void updatePassword(MemberVO member) {
		memberMapper.updatePassword(member);
	}

	@Override
	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMember(mem_num);
		memberMapper.deleteMember_detail(mem_num);
	}

	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
	}

	@Override
	public List<MemberVO> selectList(Map<String, Object> map) {
		return memberMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return memberMapper.selectRowCount(map);
	}

	@Override
	public void updateByAdmin(MemberVO member) {
		memberMapper.updateByAdmin(member);
	}

	
	//통합검색
	@Override
	public List<ClubVO> searchClubList(String keyword) {
		return memberMapper.searchClubList(keyword);
	}
	@Override
	public List<CommunityVO> searchCommuList(String keyword) {
		return memberMapper.searchCommuList(keyword);
	}
	@Override
	public List<RecipeVO> searchRecipeList(String keyword) {
		return memberMapper.searchRecipeList(keyword);
	}
	@Override
	public List<ProductVO> searchProductList(String keyword) {
		return memberMapper.searchProductList(keyword);
	}
	@Override
	public List<SaleVO> searchSaleList(String keyword) {
		return memberMapper.searchSaleList(keyword);
	}
	@Override
	public List<UsedVO> searchUsedList(String keyword) {
		return memberMapper.searchUsedList(keyword);
	}

	@Override
	public String findId(String name, String phone) {
		return memberMapper.findId(name, phone);
	}

}
