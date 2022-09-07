package kr.spring.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.util.PagingUtil;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private int rowCount = 20;
	private int pageCount = 10;

	@ModelAttribute
	public ProductVO initCommand() {
		return new ProductVO();
	}

	@Autowired
	private ProductService productService;

	// 상품 리스트
	@GetMapping("/product/list.do")
	public String list() {
		return "productList";
	}

	// ==========상품 목록(관리자용)==============
	@RequestMapping("/product/admin_list.do")
	public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyfield", defaultValue = "") String keyfield,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		// status가 0이면 미표시(1) 표시(2) 모두 체크
		map.put("status", 0);

		// 상품 총 개수(혹은 검색 개수)
		int count = productService.selectProductCount(map);

		logger.debug("<<count>> : " + count);

		// 페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "list.do");

		List<ProductVO> list = null;
		if (count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = productService.selectProductList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		mav.setViewName("adminList");

		return mav;
	}

	// ================상품 글 등록======================
	// 등록 폼
	@GetMapping("/product/register.do")
	public String form() {
		logger.debug("<<등록 폼 호출>>");
		return "productRegister";
	}

	// 상품 등록
	@PostMapping("/product/register.do")
	public String submit(@Valid ProductVO productVO, BindingResult result, HttpServletRequest request,
			HttpSession session, Model model) {
		logger.debug("<<상품등록>> : " + productVO);
		// 상품 사진에 대한 유효성 체크(어노테이션으로 못함)
		if (productVO.getUpload1() == null || productVO.getUpload1().isEmpty()) {
			result.rejectValue("upload1", "required");
		}

		if (result.hasErrors()) {
			return form();
		}

		productService.insertProduct(productVO);

		model.addAttribute("message", "상품 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/product/list.do");

		// 알림 페이지 만들기
		return "common/resultView";
	}

	// ======================상품 수정==========================
	// 수정 폼
	@GetMapping("/product/admin_modify.do")
	public String formUpdate(@RequestParam int product_num, Model model) {
		ProductVO productVO = productService.selectProduct(product_num);

		model.addAttribute("productVO", productVO);

		return "productAdminModify";
	}

	// 폼에서 전송된 데이터 처리
	@PostMapping("/product/admin_modify.do")
	public String submitUpdate(@Valid ProductVO vo, 
								BindingResult result, 
								Model model, 
								HttpServletRequest request) {
		logger.debug("<<상품수정>> : " + vo);

		// 유효성 체크
		if (result.hasErrors()) {
			return "productAdminModify";
		}

		productService.updateProduct(vo);
		// View에 표시할 메시지
		model.addAttribute("message", "상품 수정 완료");
		model.addAttribute("url", request.getContextPath() + "/product/admin_modify.do?product_num=" + vo.getProduct_num());

		return "common/resultView";
	}
}
