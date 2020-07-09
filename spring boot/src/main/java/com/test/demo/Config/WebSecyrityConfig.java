//package com.test.demo.Config;
//
//import com.test.demo.service.impl.CustomUserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecyrityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    UserDetailsService customUserService() {
//        return new CustomUserService();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().permitAll();
////        http.authorizeRequests()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login").defaultSuccessUrl("/user")
////                .failureUrl("/")
////                .permitAll()
////                .and()
////                .logout().permitAll();
//    }
//}
