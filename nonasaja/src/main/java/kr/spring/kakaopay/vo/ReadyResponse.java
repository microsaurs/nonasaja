package kr.spring.kakaopay.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
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
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public boolean isTms_result() {
		return tms_result;
	}
	public void setTms_result(boolean tms_result) {
		this.tms_result = tms_result;
	}
	public String getNext_redirect_app_url() {
		return next_redirect_app_url;
	}
	public void setNext_redirect_app_url(String next_redirect_app_url) {
		this.next_redirect_app_url = next_redirect_app_url;
	}
	public String getNext_redirect_mobile_url() {
		return next_redirect_mobile_url;
	}
	public void setNext_redirect_mobile_url(String next_redirect_mobile_url) {
		this.next_redirect_mobile_url = next_redirect_mobile_url;
	}
	public String getNext_redirect_pc_url() {
		return next_redirect_pc_url;
	}
	public void setNext_redirect_pc_url(String next_redirect_pc_url) {
		this.next_redirect_pc_url = next_redirect_pc_url;
	}
	public String getAndroid_app_scheme() {
		return android_app_scheme;
	}
	public void setAndroid_app_scheme(String android_app_scheme) {
		this.android_app_scheme = android_app_scheme;
	}
	public String getIos_app_scheme() {
		return ios_app_scheme;
	}
	public void setIos_app_scheme(String ios_app_scheme) {
		this.ios_app_scheme = ios_app_scheme;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "ReadyResponse [tid=" + tid + ", tms_result=" + tms_result + ", next_redirect_app_url="
				+ next_redirect_app_url + ", next_redirect_mobile_url=" + next_redirect_mobile_url
				+ ", next_redirect_pc_url=" + next_redirect_pc_url + ", android_app_scheme=" + android_app_scheme
				+ ", ios_app_scheme=" + ios_app_scheme + ", created_at=" + created_at + "]";
	}
	
}
