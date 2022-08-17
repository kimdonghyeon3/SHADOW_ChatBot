package com.example.shadow.security.config;

import com.example.shadow.security.service.MemberSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* 인가 구분을 위한 url path 지정 */
    private static final String[] AUTH_WHITELIST_STATIC = {"/css/**", "/js/**", "/assert/*.ico"}; // 정적 파일 인가 없이 모두 허용
    private static final String[] AUTH_ALL_LIST = {"/singup/**", "/login/**"}; // 모두 허용
    private static final String[] AUTH_ADMIN_LIST = {"/admin"}; // admin 롤 만 허용
    private static final String[] AUTH_AUTHENTICATED_LIST = {"/shadows/**", "/flowcharts/**", "/main/**"}; // 인가 필요

    private final MemberSecurityService memberSecurityService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(AUTH_ALL_LIST).permitAll()
                .antMatchers(AUTH_ADMIN_LIST).hasRole("ADMIN")
                .antMatchers(AUTH_AUTHENTICATED_LIST).authenticated();
        http
            .csrf().ignoringAntMatchers("/h2-console/**")
            .and()
            .headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}