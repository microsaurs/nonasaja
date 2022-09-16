package kr.spring.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.review.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	//상품별 리뷰 목록 수 
	
	//상품별 리뷰 목록
	
	//리뷰 쓰기
	public void insertReview(ReviewVO review);
	
	//상품별 별점 평균 구하기
	
	//회원 번호로 리뷰 목록 구해오기
	@Select("select * from product_review where mem_num=#{mem_num}")
	public List<ReviewVO> selectReviewListByMem(int mem_num);
	
	//회원번호와 상품번호로 리뷰 상세
	@Select("select * from product_review where mem_num=#{mem_num} and product_num=${product_num}")
	public ReviewVO selectReview(int mem_num, int product_num);
	
	//리뷰 수정
	public void updateReview(ReviewVO review);
	
	//리뷰 삭제
}
