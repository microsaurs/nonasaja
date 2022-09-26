package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.club.vo.ClubVO;
import kr.spring.community.vo.CommunityVO;
import kr.spring.community.vo.RecipeVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.vo.ReviewVO;
import kr.spring.sale.vo.SaleVO;
import kr.spring.used.vo.UsedVO;

public interface MemberService {
	public void insertMember(MemberVO member);//회원가입
	public void insertNaverMember(MemberVO member);//네이버 회원가입
	public void insertKakaoMember(MemberVO member);//카카오 회원가입
	public MemberVO selectCheckMember(String id);//아이디 중복 체크
	public MemberVO selectMember(Integer mem_num);//하나의 회원 조회
	public void updateMember(MemberVO member);//회원정보 수정
	public void updatePassword(MemberVO member);//비밀번호 수정
	public void deleteMember(Integer mem_num);//회원 삭제
	public void updateProfile(MemberVO member);//프로필 이미지 업데이트
	
	//회원관리 - 관리자
	public List<MemberVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void updateByAdmin(MemberVO member);
	
	//통합검색
	public List<ClubVO> searchClubList(String keyword);
	public List<CommunityVO> searchCommuList(String keyword);
	public List<RecipeVO> searchRecipeList(String keyword);
	public List<ProductVO> searchProductList(String keyword);
	public List<SaleVO> searchSaleList(String keyword);
	public List<UsedVO> searchUsedList(String keyword);
}
