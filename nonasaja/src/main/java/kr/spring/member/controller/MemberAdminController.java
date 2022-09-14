package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class MemberAdminController {
	private static final Logger logger = LoggerFactory.getLogger(MemberAdminController.class);
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private MemberService memberService;
	
	//========회원 목록=========//
	@RequestMapping("/member/admin_list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="keyfield",defaultValue="") String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		//총 글의 개수 또는 검색된 글의 개수
		int count = memberService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "admin_list.do");
		
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		List<MemberVO> list = null;
		if(count > 0) {
			list = memberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList");
		mav.addObject("list", list);
		mav.addObject("count", count);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	//=========회원정보수정============//
	//수정 폼
	@GetMapping("/member/admin_update.do")
	public String form(@RequestParam int mem_num, Model model) {
		MemberVO memberVO = memberService.selectMember(mem_num);
		model.addAttribute("memberVO", memberVO);
		
		return "admin_memberModify";
	}
	//수정 폼에서 전송한 데이터 처리
	@PostMapping("/member/admin_update.do")
	public String submit(MemberVO memberVO, Model model, HttpServletRequest request) {
		logger.debug("<<관리자 회원권한 수정>> : "+memberVO);
		
		//회원정보 수정
		memberService.updateByAdmin(memberVO);
		
		//view에 표시할 메시지
		model.addAttribute("message", "회원권한 수정 완료!");
		model.addAttribute("url", request.getContextPath()+"/member/admin_update.do?mem_num="+memberVO.getMem_num());
		
		
		return "common/resultView";
	}
	
}
