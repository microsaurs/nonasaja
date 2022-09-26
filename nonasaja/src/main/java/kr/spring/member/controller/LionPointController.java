package kr.spring.member.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.kakaopay.service.KakaopayService;
import kr.spring.kakaopay.vo.ApproveResponse;
import kr.spring.kakaopay.vo.ReadyResponse;
import kr.spring.member.vo.LionPointVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class LionPointController {
	private static final Logger logger = LoggerFactory.getLogger(LionPointController.class);
	
	@Autowired
	private KakaopayService kakaoService;
	
	private int rowCount = 15;
	private int pageCount = 10;
	
	//결제 요청
	@RequestMapping("/member/payReady.do")
	@ResponseBody
	public String kakaopayReady(String money, HttpSession session) {
		//BufferedReader change = null;
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) address.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK 4827aa887dd23fd8be0f3158d9eb8bad");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);//서버에 전달할 정보가 있음을 명시 기본값 false
			
			String parameter = "cid=TC0ONETIME" // 가맹점 코드
					+ "&partner_order_id=partner_order_id" // 가맹점 주문번호: order_id,user_id는 postman 라이브러리 사용
					+ "&partner_user_id=partner_user_id" // 가맹점 회원 id 
					+ "&item_name=nonasaja point" // 상품명
					+ "&quantity=1" // 상품 수량
					+ "&total_amount="+money // 총 금액
					+ "&vat_amount=200" // 부가세
					+ "&tax_free_amount=0" // 상품 비과세 금액
					+ "&approval_url=http://localhost:8080/member/payApproval.do" // 결제 성공 시
					+ "&fail_url=http://localhost:8080/member/myPage.do" // 결제 실패 시
					+ "&cancel_url=http://localhost:8080/member/myPage.do"; // 결제 취소 시
			OutputStream send = conn.getOutputStream(); // 이제 뭔가를 를 줄 수 있다.
			DataOutputStream dataSend = new DataOutputStream(send); // 이제 데이터를 줄 수 있다.
			dataSend.writeBytes(parameter); // OutputStream은 데이터를 바이트 형식으로 주고 받기로 약속되어 있다. (형변환)
			dataSend.close(); // flush가 자동으로 호출이 되고 닫는다. (보내고 비우고 닫다)
			
			int result = conn.getResponseCode(); // 전송 잘 됐나 안됐나 번호를 받는다.
			InputStream receive; // 받다

			if(result == 200) {
				receive = conn.getInputStream();
			}else {
				receive = conn.getErrorStream(); 
			}
			// 읽는 부분
			InputStreamReader read = new InputStreamReader(receive); // 받은걸 읽는다.
			BufferedReader change = new BufferedReader(read); // 바이트를 읽기 위해 형변환 버퍼리더는 실제로 형변환을 위해 존제하는 클레스는 아니다.
			
			String resp = change.readLine(); 
			logger.debug("<응답>... "+ resp);
			
			if(change!=null) {
				//응답 내용 ReadyResponse 객체에 저장
				ObjectMapper objectMapper = new ObjectMapper();
				ReadyResponse ready = null;
				try {
					ready = objectMapper.readValue(resp, ReadyResponse.class);
					logger.debug("<준비응답>..."+ready);
					//세션에 저장
					session.setAttribute("ready", ready);
				}catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			//받는 부분
			return resp;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//결제 승인
	@RequestMapping("/member/payApproval.do")
	public String kakaopayApprove(String pg_token, HttpSession session) {
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/approve");
			HttpURLConnection conn = (HttpURLConnection) address.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK 4827aa887dd23fd8be0f3158d9eb8bad");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);//서버에 전달할 정보가 있음을 명시 기본값 false
			
			ReadyResponse ready = (ReadyResponse)session.getAttribute("ready");
			String tid = ready.getTid();
			logger.debug("<tid>..."+tid);
			
			String parameter = "cid=TC0ONETIME" // 가맹점 코드
					+ "&tid="+ tid // 결제 고유번호, 결제 준비 API 응답에 포함
					+ "&partner_order_id=partner_order_id" // 가맹점 주문번호, 결제 준비 API요청과 일치해야함
					+ "&partner_user_id=partner_user_id" // 가맹점 회원 id, 결제 준비 API요청과 일치해야함
					+ "&pg_token="+ pg_token; // 결제승인 요청을 인증하는 토큰, approval_url로 리다이렉션시 쿼리스트링으로 담김
					
			OutputStream send = conn.getOutputStream(); // 이제 뭔가를 를 줄 수 있다.
			DataOutputStream dataSend = new DataOutputStream(send); // 이제 데이터를 줄 수 있다.
			dataSend.writeBytes(parameter); // OutputStream은 데이터를 바이트 형식으로 주고 받기로 약속되어 있다. (형변환)
			dataSend.close(); // flush가 자동으로 호출이 되고 닫는다. (보내고 비우고 닫다)
			
			int result = conn.getResponseCode(); // 전송 잘 됐나 안됐나 번호를 받는다.
			InputStream receive; // 받다

			if(result == 200) {
				receive = conn.getInputStream();
			}else {
				receive = conn.getErrorStream(); 
			}
			// 읽는 부분
			InputStreamReader read = new InputStreamReader(receive); // 받은걸 읽는다.
			BufferedReader change = new BufferedReader(read); // 바이트를 읽기 위해 형변환 버퍼리더는 실제로 형변환을 위해 존제하는 클레스는 아니다.
			
			String resp = change.readLine(); 
			logger.debug("<응답>... "+ resp);
			
			if(change!=null) {
				//응답 내용 ReadyResponse 객체에 저장
				ObjectMapper objectMapper = new ObjectMapper();
				ApproveResponse approve = null;
				//세션에 저장된 로그인한 회원 정보 불러옴
				MemberVO user = (MemberVO)session.getAttribute("user");
				try {
					approve = objectMapper.readValue(resp, ApproveResponse.class);
					logger.debug("<승인응답>..."+approve);
					//세션에 저장
					session.setAttribute("approve", approve);
					
					//회원번호를 이용해 직전의 포인트 사용 내역 불러옴
					LionPointVO pointVO = kakaoService.selectPointbyMem(user.getMem_num());
					logger.debug("<포인트 내역>..."+pointVO);
					
					if(pointVO!=null) {
						logger.debug("<충전 내역 있음>...");
						pointVO.setLionpoint(approve.getAmount().getTotal());
						pointVO.setCash(pointVO.getRemain());
						pointVO.setRemain(pointVO.getCash()+approve.getAmount().getTotal());
						user.setCash(pointVO.getRemain());
						
						kakaoService.insertPoint(pointVO);
						kakaoService.updateMemberCash(user);
					}else if(pointVO == null){
						logger.debug("<충전 내역 없음>...1");
						logger.debug("<충전 내역 없음>...2");
						
						LionPointVO pointVO2 = new LionPointVO();
						//정보 세팅
						pointVO2.setMem_num(user.getMem_num());
						pointVO2.setLionpoint(approve.getAmount().getTotal());
						pointVO2.setCash(0);
						pointVO2.setRemain(pointVO2.getCash()+approve.getAmount().getTotal());
						
						user.setCash(pointVO2.getRemain());
						
						kakaoService.insertPoint(pointVO2);
						kakaoService.updateMemberCash(user);
					}
					
				}catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}catch(NullPointerException e) {
					logger.debug("<충전 내역 없음>...3");
					
					LionPointVO pointVO = new LionPointVO();
					//정보 세팅
					pointVO.setMem_num(user.getMem_num());
					pointVO.setLionpoint(approve.getAmount().getTotal());
					pointVO.setCash(0);
					pointVO.setRemain(pointVO.getCash()+approve.getAmount().getTotal());
					
					user.setCash(pointVO.getRemain());
					
					kakaoService.insertPoint(pointVO);
					kakaoService.updateMemberCash(user);
				}
			}

		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		//결제가 완료되면 메인페이지로 이동 = "main"
		//마이페이지로 이동 = "redirect:myPage.do"
		return "redirect:paymentList.do";
	}
	
	//==========포인트 내역 목록=============//
	@RequestMapping("/member/paymentList.do")
	public ModelAndView paymentList(@RequestParam(value="pageNum",defaultValue="1")int currentPage, 
									HttpSession session) {
		logger.debug("<결제 내역 조회>...");
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		int count = kakaoService.selectPointCnt(user.getMem_num());
		logger.debug("<count>..." + count);
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,"paymentList.do");
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		List<LionPointVO> list = null;
		if(count>0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			map.put("mem_num", user.getMem_num());
			list = kakaoService.selectPointList(map);
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("paymentList");
		mav.addObject("member", user);
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
}
