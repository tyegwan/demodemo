package com.example.demodeom._core.config;

import com.example.demodeom._core.interceptor.LoginInterceptor;
import com.example.demodeom._core.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration // IoC 대상 - 하나 이상의 IoC 처리를 하고 싶을 때 사용 한다.
// DI
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired // DI 처리
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor)
                        .addPathPatterns("/**"); // 모든 URL 요청서 동작 함


        // 여기에 LoginInterceptor 등록할 예정

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/board/**", "/user/**")
                .excludePathPatterns(
                        // 로그인 관련(인증이 필요 없는 페이지)
                        "/login-form",  // 로그인 화면 요청 시
                        "/join-form", // 회원 가입 화면 요청 시
                        "/logout", // 로그아웃

                        // 게시글 조회 관련 (인증 없이도 볼 수 있는 페이지)
                        "/board/list", // 게시글 목록 화면 요청 시
                        "/"          , // 메인 페이지
                        "/index",
                        "/board/{id:\\d+}", // 게시글 상세보기( 숫자 ID만 허용)

                        // 정적 리소스 (CSS, JS, 이미지 등)
                        "/css/**",           // CSS 파일 제외
                        "/js/**",            // JS 파일 제외
                        "/images/**",        // 이미지 파일 제외
                        "/favicon.ico",      // 파비콘 제외

                        // H2 데이터베이스 콘솔 (개발 환경용)
                        "/h2-console/**"
                );


    }
}
