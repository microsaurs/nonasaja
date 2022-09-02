package kr.spring.product.service;

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

}
