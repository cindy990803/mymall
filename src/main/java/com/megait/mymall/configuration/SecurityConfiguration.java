package com.megait.mymall.configuration;

//security 관련 Configuration 빈

import com.megait.mymall.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor  //autowired????
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //alt + insert 단축키 : 오버라이드, 생성자 등

    private final CustomOAuth2UserService customOAuth2UserService;

    //웹 관련 보안 설정 (예. 방화벽)
    @Override
    public void configure(WebSecurity web) throws Exception {
        //정적 리소스를 허용
        //web.ignoring() : 세큐리티 검사(필터링)를 아예 안 하는 것
        web.ignoring().requestMatchers( PathRequest.toStaticResources().atCommonLocations() );
    }

    //요청/응답 관련 보안 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 127.0.0.1:8080/ 요청은 모든 사용자에게 허용
        http.authorizeRequests().mvcMatchers("/").permitAll();  //모든 세큐리티 검사(17단계)를 통과시키는 것

        //http.authorizeRequests().antMatchers("/**/*.png").permitAll();  //잘못된 방법임!

/*
        //로그인 form 사용 및 설정
        http.formLogin()
            .loginPage("/login")  //로그인 페이지 경로를 "/login"이 아닌 다른 경로로 쓰고 싶을 때 사용
            .defaultSuccessUrl("/", true);  //로그인 성공 시 리다이렉트할 경로

        //로그아웃 설정
        http.logout()
            .logoutUrl("/logout")  //로그아웃을 실행할 url. 디폴트는 "logout"
            .invalidateHttpSession(true)  //로그아웃했을 때 세션을 종료시킬 것인가
            .logoutSuccessUrl("/");  //로그아웃 성공 시 리다이렉트할 경로

        //한 문장으로 줄이면 ↓와 같이 됨!!
*/

        http
            .authorizeRequests()
                .mvcMatchers("/")
                .permitAll()

                .antMatchers("/mypage/**")
                .authenticated()

                //get방식으로 들어오는 것만 허용
                .mvcMatchers(HttpMethod.GET, "email-check")
                .permitAll()
                //mvcMatchers() : MVC 컨트롤러가 사용하는 요청 url 패턴.

                //antMatchers() : "**"(0개 이상의 디렉토리를 의미), "*", "?"을 사용하는 url 패턴.

        .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
        .and()
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/")
        //네이버로 로그인, 구글로 로그인 기능에 필요
        .and()
            .oauth2Login()
            .loginPage("/login")
            .userInfoEndpoint()
            .userService(customOAuth2UserService)

        ;



    }


}
