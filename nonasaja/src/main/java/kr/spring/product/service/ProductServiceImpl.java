package kr.spring.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.product.dao.ProductMapper;
import kr.spring.product.vo.ProductVO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public void insertProduct(ProductVO productVO) {
		productMapper.insertProduct(productVO);	
	}

	@Override
	public int selectProductCount(Map<String, Object> map) {
		return productMapper.selectProductCount(map);
	}

	@Override
	public List<ProductVO> selectProductList(Map<String, Object> map) {
		return productMapper.selectProductList(map);
	}

	@Override
	public ProductVO selectProduct(Integer product_num) {
		return productMapper.selectProduct(product_num);
	}

	@Override
	public void updateProduct(ProductVO productVO) {
		productMapper.updateProduct(productVO);	
	}

}
