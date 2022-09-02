package kr.spring.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.product.vo.ProductVO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Override
	public void insertProduct(ProductVO productVO) {
		// TODO Auto-generated method stub	
	}

}
