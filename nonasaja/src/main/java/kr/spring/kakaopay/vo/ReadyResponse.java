package kr.spring.kakaopay.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadyResponse {
	private String tid; //결제 고유 번호, 20자
	private boolean tms_result; //?
	private String next_redirect_app_url; //요청 클라이언트가 모바일 앱일 경우 결제페이지 redirect URL
	private String next_redirect_mobile_url; //요청 클라이언트가 모바일 웹일 경우 결제페이지 redirect URL
	private String next_redirect_pc_url; //요청 클라이언트가 pc 웹일 경우 결제페이지 redirect URL
	private String android_app_scheme; //카카오페이 결제 화면으로 이동하는 안드로이드 앱 스킴
	private String ios_app_scheme; //카카오페이 결제 화면으로 이동하는 iOS 앱 스킴
	private String created_at; //결제 준비 요청 시간
	//private String partner_order_id;
}
