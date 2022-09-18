package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.vo.ReviewVO;

@Service
@Transactional
public class ReviewserviceImpl implements ReviewService{
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public void insertReview(ReviewVO review) {
		reviewMapper.insertReview(review);
	}

	@Override
	public List<ReviewVO> selectReviewListByMem(int mem_num) {
		return reviewMapper.selectReviewListByMem(mem_num);
	}

	@Override
	public ReviewVO selectReview(int mem_num, int product_num) {
		return reviewMapper.selectReview(mem_num, product_num);
	}

	@Override
	public void updateReview(ReviewVO review) {
		reviewMapper.updateReview(review);
	}

	@Override
	public int selectReviewCount(int product_num) {
		return reviewMapper.selectReviewCount(product_num);
	}

	@Override
	public List<ReviewVO> selectReviewList(Map<String, Object> map) {
		return reviewMapper.selectReviewList(map);
	}

	@Override
	public int selectScore(int product_num) {
		return reviewMapper.selectScore(product_num);
	}

	@Override
	public void deleteReview(int review_num) {
		reviewMapper.deleteReview(review_num);
	}

}
