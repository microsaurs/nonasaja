package kr.spring.product.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.product.vo.ProductVO;

@Mapper
public interface ProductMapper {
	//상품 등록
	public void insertProduct(ProductVO productVO);
	
	//상품 검색 (카테고리별, 재고별, 판매량별 정렬/제품이름 검색)
	
	//상품 수정 
	
	//상품 삭제(리뷰 삭제 먼저 할 것)
	
	
	
} 























