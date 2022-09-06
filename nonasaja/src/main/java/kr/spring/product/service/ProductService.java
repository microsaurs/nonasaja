package kr.spring.product.service;

import java.util.List;
import java.util.Map;

import kr.spring.product.vo.ProductVO;

public interface ProductService {
	public void insertProduct(ProductVO productVO);
	//상품 총 개수 또는 검색 개수
	public int selectProductCount(Map<String,Object> map);
	//상품 목록 또는 검색 목록
	public List<ProductVO> selectProductList(Map<String, Object> map);
}
