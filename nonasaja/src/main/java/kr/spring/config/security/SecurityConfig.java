//package kr.spring.config.security;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig{
//	
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeRequests()
//                		.mvcMatchers("/", "/login", "/member/*").permitAll() // permitAll(): 해당 요청에 인증을 요구하지 않게 예외 처리함
//                		.mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
//                		.anyRequest().authenticated() // 어떠한 url로 접근하던지 인증이 필요함을 설정
//                		.and()
//                		.formLogin().loginPage("/member/login").permitAll() // form 방식 로그인을 사용할 것임을 알림
//                		.and()
//                		.logout().logoutSuccessUrl("/") // 로그아웃 성공시 이동할 uri
//                		.and().build();
//                		//.rememberMe().userDetailsService(accountService).tokenRepository(tokenRepository())
//                		//.and().build();
//    }
//	
//	@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//        						.mvcMatchers("/css/**", "/images/**")
//        						.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//}
