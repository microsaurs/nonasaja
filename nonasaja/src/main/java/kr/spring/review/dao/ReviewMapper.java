package kr.spring.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.review.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	//상품별 리뷰 목록 수 
	@Select("select count(*) from product_review where product_num=#{product_num}")
	public int selectReviewCount(int product_num);
	
	//상품별 리뷰 목록
	public List<ReviewVO> selectReviewList(Map<String,Object> map);
	
	//리뷰 쓰기
	public void insertReview(ReviewVO review);
	
	//상품별 별점 평균 구하기
	@Select("select ROUND(AVG(NVL(score, 0))) from product_review where product_num=#{product_num}")
	public int selectScore(int product_num);
	
	//회원 번호로 리뷰 목록 구해오기
	@Select("select * from product_review where mem_num=#{mem_num}")
	public List<ReviewVO> selectReviewListByMem(int mem_num);
	
	//회원번호와 상품번호로 리뷰 상세
	@Select("select * from product_review where mem_num=#{mem_num} and product_num=${product_num}")
	public ReviewVO selectReview(int mem_num, int product_num);
	
	//리뷰 수정
	public void updateReview(ReviewVO review);
	
	//리뷰 삭제
	@Delete("delete from product_review where review_num=${review_num}")
	public void deleteReview(int review_num);
}
