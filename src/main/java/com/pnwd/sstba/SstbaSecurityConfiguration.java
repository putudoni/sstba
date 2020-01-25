/**
 * 
 */
package com.pnwd.sstba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Putu Ngurah Wigadoni
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SstbaSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private AuthenticationProvider authenticationProvider;

	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(new AntPathRequestMatcher("/api/**"));

	public SstbaSecurityConfiguration(AuthenticationProvider authenticationProvider) {
		super();
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class).authorizeRequests()
				.requestMatchers(PROTECTED_URLS).authenticated().and().csrf().disable().formLogin().disable().httpBasic()
				.disable().logout().disable();
	}

	@Bean
	public AuthenticationFilter authenticationFilter() throws Exception {
		final AuthenticationFilter authFilter = new AuthenticationFilter(PROTECTED_URLS);
		authFilter.setAuthenticationManager(authenticationManager());
		return authFilter;

	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	}
}
