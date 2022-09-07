package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.member.vo.MemberVO;
import kr.spring.product.service.ProductService;

public class AdminCheckInterceptor 
                      implements HandlerInterceptor{
	
	private static final Logger logger =
			   LoggerFactory.getLogger(
					    AdminCheckInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, 
						HttpServletResponse response, Object handler)throws Exception {
		
		logger.debug("<<관리자 확인>>");
		
		//로그인 회원번호 구하기 
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//로그인 회원번호와 작성자 회원번호 일치 여부 확인
		if(user == null || user.getAuth() != 3) {
			logger.debug("<<관리자가 아닌 접근>>");
			
			//UI에 보일 정보 저장
			request.setAttribute("accessMsg", "잘못된 접근입니다");
			request.setAttribute("accessBtn", "상품 목록");
			request.setAttribute("accessUrl", request.getContextPath() +"/product/list.do");
			
			//포워드 방식으로 화면 호출
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			
			return false;
		}
		logger.debug("<<관리자 접근>>");
		return true;
	}
}





