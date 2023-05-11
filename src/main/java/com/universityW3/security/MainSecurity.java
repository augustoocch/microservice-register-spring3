package com.universityW3.security;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {
    //WebSecurityConfigurerAdapter esta deprecado
    //Hay que migrarlo

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private UserDetailedService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailService)
                .passwordEncoder(this.passowrdEncoder());
    }

    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
   @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET,"/").permitAll()
			.antMatchers(new String[]{"/auth/**"}).permitAll()
            .antMatchers("/swagger-resources/**").permitAll() ////Agreagada
			.antMatchers("/v2/api-docs","/configuration/**","/swagger*/**","/webjar/**").permitAll()
			.anyRequest().authenticated()
			.and()
			//exception handler > error
			.exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint) this.jwtEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		//http.addFilterAfter(this.filter(), UsernamePasswordAuthenticationFilter.class);
	}
        
        
    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public Filter filter() {
        return new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passowrdEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String passowrdEncrypt(String passw) {

        String passwordE = null;
        try {
            passwordE = new String(passowrdEncoder().encode(passw).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passwordE;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
