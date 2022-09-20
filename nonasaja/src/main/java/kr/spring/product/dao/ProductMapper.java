package kr.spring.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.product.vo.ProductVO;

@Mapper
public interface ProductMapper {
	//상품 등록
	public void insertProduct(ProductVO productVO);
	
	//상품 총 개수 또는 검색 개수
	public int selectProductCount(Map<String,Object> map);
	//상품 목록 또는 검색 목록
	public List<ProductVO> selectProductList(Map<String, Object> map);
	
	//상품 상세
	@Select("select * from product where product_num=#{product_num}")
	public ProductVO selectProduct(Integer product_num);
	
	//상품 수정 
	public void updateProduct(ProductVO productVO); 
	
	//상품 수량 수정 
	@Update("update product set quantity=quantity-#{quantity} where product_num=#{product_num}")
	public void updateProductQuantity(int product_num, int quantity);
	
	//상품 삭제(리뷰 삭제 먼저 할 것)
	
	
	
} 























