package fa.training.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fa.training.spring.security.jwt.JwtEntryPoint;
import fa.training.spring.security.jwt.JwtTokenFilter;
import fa.training.spring.security.userprinciple.UserDetailService;

@SuppressWarnings("deprecation")
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/v1/signup", "/api/v1/signin", "/api/v1/product", "/api/v1/category", "/favicon.ico ").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/user/user").hasRole("CUSTOMER")
				.antMatchers(HttpMethod.GET, "/api/v1/user/location/**").hasAnyRole("ADMIN", "CUSTOMER")
				.antMatchers(HttpMethod.GET, "/api/v1/user/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyRole("ADMIN", "CUSTOMER")
				.and().exceptionHandling()
				.authenticationEntryPoint(jwtEntryPoint)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}
