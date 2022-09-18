package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.review.vo.ReviewVO;

public interface ReviewService {
	//상품별 리뷰 목록 수 
		public int selectReviewCount(int product_num);
		
		//상품별 리뷰 목록
		public List<ReviewVO> selectReviewList(Map<String,Object> map);
		
		//리뷰 쓰기
		public void insertReview(ReviewVO review);
		
		//상품별 별점 평균 구하기
		public int selectScore(int product_num);
		
		//회원 번호로 리뷰 목록 구해오기
		public List<ReviewVO> selectReviewListByMem(int mem_num);
		
		//회원번호와 상품번호로 리뷰 상세
		public ReviewVO selectReview(int mem_num, int product_num);
		
		//리뷰 수정
		public void updateReview(ReviewVO review);
		
		//리뷰 삭제
		public void deleteReview(int review_num);
}
