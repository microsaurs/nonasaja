package kr.spring.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.product.vo.ProductVO;

@Mapper
public interface ProductMapper {
	//상품 등록
	public void insertProduct(ProductVO productVO);
	
	//상품 총 개수 또는 검색 개수
	public int selectProductCount(Map<String,Object> map);
	//상품 목록 또는 검색 목록
	public List<ProductVO> selectProductList(Map<String, Object> map);
	
	//상품 수정 
	
	//상품 삭제(리뷰 삭제 먼저 할 것)
	
	
	
} 























