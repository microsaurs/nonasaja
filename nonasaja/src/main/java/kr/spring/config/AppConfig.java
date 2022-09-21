package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.AdminCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;
//import kr.spring.interceptor.ClubWriterCheckInterceptor;

//자바코드 기반 설정 클래스

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	/*
	 * ClubWriterCheckInterceptor interceptor;
	 * 
	 * @Bean public ClubWriterCheckInterceptor interceptor() { interceptor = new
	 * ClubWriterCheckInterceptor(); return interceptor; }
	 */
	
	
	//인터셉터 지정
	@Override
	public void addInterceptors(
			       InterceptorRegistry registry) {
		registry.addInterceptor(
				    new LoginCheckInterceptor())
		        .addPathPatterns("/member/myPage.do")
		        .addPathPatterns("/member/update.do")
		        .addPathPatterns("/member/photoView.do")
		        .addPathPatterns("/member/myPageProduct.do")
		        .addPathPatterns("/member/myPageUsed.do")
		        .addPathPatterns("/member/myPageClub.do")
		        .addPathPatterns("/member/myPageCommu.do")
		        .addPathPatterns("/member/changePassword.do")
		        .addPathPatterns("/member/delete.do")
		        .addPathPatterns("/cart/cart_insert.do")
		        .addPathPatterns("/cart/cart_list.do")
		        .addPathPatterns("/cart/deleteCart.do")
		        .addPathPatterns("/cart/modifyCart.do")
		        .addPathPatterns("/order/order_list.do")
		        .addPathPatterns("/review/write_review.do")
		        .addPathPatterns("/review/modify_review.do");
		registry.addInterceptor(new AdminCheckInterceptor())
				.addPathPatterns("/product/admin_list.do")
				.addPathPatterns("/product/register.do")
				.addPathPatterns("/product/admin_modify.do")
				.addPathPatterns("/product/delete.do");
		/*
		 * registry.addInterceptor(interceptor) .addPathPatterns("/clubboard/write.do")
		 * .addPathPatterns("/clubboard/update.do")
		 * .addPathPatterns("/clubboard/delete.do");
		 * 
		 * 추가
		 */
			
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = 
				new TilesConfigurer();
		
		//해당 경로에 xml 설정 파일을 넣음
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/jung.xml",
				"/WEB-INF/tiles-def/kim.xml",
				"/WEB-INF/tiles-def/lee.xml",
				"/WEB-INF/tiles-def/oh.xml",
				"/WEB-INF/tiles-def/ohohoh.xml",
				"/WEB-INF/tiles-def/yun.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver =
				new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
}


