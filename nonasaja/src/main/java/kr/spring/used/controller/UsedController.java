package kr.spring.used.controller;

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

import kr.spring.member.vo.MemberVO;
import kr.spring.used.service.UsedService;
import kr.spring.used.vo.UsedVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class UsedController {
	private static final Logger logger = LoggerFactory.getLogger(UsedController.class);
	private int rowCount = 20;
	private int pageCount = 10;
	
	@Autowired
	private UsedService usedService;
	
	//자바빈(VO)초기화
	@ModelAttribute
	public UsedVO initCommand() {
		return new UsedVO();
	}
	
	//========중고거래 글 등록=========//
	//등록 폼
	@GetMapping("/used/write.do")
	public String form() {
		return "usedWrite";
	}
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/used/write.do")
	public String submit(@Valid UsedVO usedVO, BindingResult result, 
						HttpServletRequest request, HttpSession session, Model model) {
		
		logger.debug("<<중고거래 글 저장>> : " + usedVO);
		
		//유효성 검사 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원번호 셋팅
		usedVO.setMem_num(user.getMem_num());
		
		//글쓰기
		usedService.insertUsed(usedVO);
		
		//View에 표시할 메시지
		model.addAttribute("message","글 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/used/list.do");
		
		return "common/resultView";
	}
	
	//========중고거래 글 목록=========//
	@RequestMapping("/used/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue = "1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue = "")
			String keyfield,
			@RequestParam(value="keyword",defaultValue = "")
			String keyword) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//글의 총 개수(검색된 글의 개수)
		int count = usedService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount,pageCount,"list.do");
		
		List<UsedVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = usedService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usedList");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	
	//========중고거래 글 상세=========//
	@RequestMapping("/used/detail.do")
	public ModelAndView detail(@RequestParam int used_num) {
		
		logger.debug("<<board_num>> : " + used_num);
		
		//해당 글의 조회수 증가
		usedService.updateHit(used_num);
		
		UsedVO used = usedService.selectUsed(used_num);
		
		//제목에 태그를 허용하지 않음
		used.setTitle(StringUtil.useNoHtml(used.getTitle()));
		
								//뷰 이름		속성명   속성값
		return new ModelAndView("usedView","used",used);
	}
	
	//========이미지 출력=========//
	@RequestMapping("used/imageView.do")
	public ModelAndView viewImage(@RequestParam int used_num,
								  @RequestParam int board_type) {
		
		UsedVO used= usedService.selectUsed(used_num);
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름
		mav.setViewName("imageView");
		
		if(board_type==1) {//프로필 사진
			mav.addObject("imageFile", used.getPhoto());
			mav.addObject("filename", used.getPhoto_name());
		}else if(board_type==2) {//업로드된 이미지
			mav.addObject("imageFile", used.getUploadfile());
			mav.addObject("filename", used.getFilename());
		}else if(board_type==3) {//업로드된 이미지
			mav.addObject("imageFile", used.getUploadfile2());
			mav.addObject("filename", used.getFilename2());
		}else if(board_type==4) {//업로드된 이미지
			mav.addObject("imageFile", used.getUploadfile3());
			mav.addObject("filename", used.getFilename3());
		}
		
		return mav;
	}
	
	//========중고거래 글 수정=========//
	//수정폼
	@GetMapping("/used/update.do")
	public String formUpdate(@RequestParam int used_num, Model model) {
		
		UsedVO usedVO = usedService.selectUsed(used_num);
		
		model.addAttribute("usedVO",usedVO);
		
		return "usedModify";
	}
	//수정 폼에서 전송된 데이터 처리
	@PostMapping("/used/update.do")
	public String submitUpdate(@Valid UsedVO usedVO,
							BindingResult result,
							HttpServletRequest request,
							Model model) {
		logger.debug("<<글수정>> : " + usedVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			//title 또는 content가 입력되지 않아 유효성 체크에 걸리면 파일 정보를 잃어버리기 때문에
			//폼을 호출할 때 다시 셋팅해주어야 함.
			UsedVO vo = usedService.selectUsed(usedVO.getUsed_num());
			usedVO.setFilename(vo.getFilename());
			return "usedModify";
		}
		
		//글 수정
		usedService.updateUsed(usedVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글수정 완료!");
		model.addAttribute("url",request.getContextPath()+"/used/detail.do?used_num="+usedVO.getUsed_num());
		
		return "common/resultView";
	}
	
	//========중고거래 글 삭제=========//
	@RequestMapping("/used/delete.do")
	public String submitDelete(@RequestParam int used_num,
							   Model model,
							   HttpServletRequest request) {
		logger.debug("<<글삭제>> : " + used_num);
		
		//글삭제
		usedService.deleteUsed(used_num);
		
		//View에 표시할 메시지
		model.addAttribute("message","글삭제 완료!!");
		model.addAttribute("url", request.getContextPath()+"/used/list.do");
		
		return "common/resultView";
	}
		
	
	
	
}
