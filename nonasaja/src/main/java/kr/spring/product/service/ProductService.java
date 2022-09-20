package kr.spring.product.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.product.vo.ProductVO;

public interface ProductService {
	public void insertProduct(ProductVO productVO);
	//상품 총 개수 또는 검색 개수
	public int selectProductCount(Map<String,Object> map);
	//상품 목록 또는 검색 목록
	public List<ProductVO> selectProductList(Map<String, Object> map);
	//상품 상세
	public ProductVO selectProduct(Integer product_num);
	//상품 수량 수정 
	public void updateProductQuantity(int product_num, int minus_quantity);
	//상품 수정 
	public void updateProduct(ProductVO productVO); 
}
