package com.example.shadow.security.config;

import com.example.shadow.security.handler.CustomFailureHandler;
import com.example.shadow.security.handler.CustomSuccessHandler;
import com.example.shadow.security.service.MemberSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* 인가 구분을 위한 url path 지정 */
    private static final String[] AUTH_WHITELIST_STATIC = {"/css/**", "/js/**", "/assert/*.ico"}; // 정적 파일 인가 없이 모두 허용
    private static final String[] AUTH_ALL_LIST = {"/singup/**", "/login/**"}; // 모두 허용
    private static final String[] AUTH_ADMIN_LIST = {"/admin"}; // admin 롤 만 허용
    private static final String[] AUTH_AUTHENTICATED_LIST = {"/shadows/**", "/flowcharts/**", "/main/**"}; // 인가 필요

    private final MemberSecurityService customUserDetailsService;
    private final AuthenticationFailureHandler customFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(AUTH_ALL_LIST).permitAll()
                .antMatchers(AUTH_ADMIN_LIST).hasRole("ADMIN")
                .antMatchers(AUTH_AUTHENTICATED_LIST).authenticated();
        http
                .csrf().ignoringAntMatchers("/h2-console/**");
        http
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
        //http    .exceptionHandling()// auth 실패할때 handling

        http
                .formLogin()
                .loginPage("/login")
                .successHandler(customSuccessHandler())
                .failureHandler(customFailureHandler);
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
        http
                .exceptionHandling()
                .accessDeniedPage("/restrict");
        ;
    }


    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // client -> daoAuthenticationprovider : 인증 처리 위임
    // daoAuthenticationProvider -> UserDetailService 사용자 정보 가져오게 한다.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

/*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }
*/

}