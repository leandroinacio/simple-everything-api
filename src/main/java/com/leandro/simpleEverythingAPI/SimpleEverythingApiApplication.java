package com.leandro.simpleEverythingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class SimpleEverythingApiApplication {

 	public static void main(String[] args) {
		SpringApplication.run(SimpleEverythingApiApplication.class, args);
	}
}

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      // @formatter:off
      http
          .httpBasic()
          .and()
          .authorizeRequests()
          .antMatchers("/auth/signin", "/auth/signup", "/").permitAll()
          .anyRequest().authenticated()
          .and()
          .csrf().disable();
      //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
      // @formatter:on
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
}