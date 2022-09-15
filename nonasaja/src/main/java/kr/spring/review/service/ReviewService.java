package kr.spring.review.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.review.vo.ReviewVO;

public interface ReviewService {
	// 상품별 리뷰 목록 수

	// 리뷰 목록 상세

	// 리뷰 쓰기
	public void insertReview(ReviewVO review);

	// 상품별 별점 평균 구하기

	// 회원 번호로 리뷰 목록 구해오기
	public List<ReviewVO> selectReviewListByMem(int mem_num);

	// 리뷰 수정

	// 리뷰 삭제
}
