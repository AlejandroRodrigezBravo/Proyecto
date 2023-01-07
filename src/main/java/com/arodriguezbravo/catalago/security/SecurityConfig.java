package com.arodriguezbravo.catalago.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configurador de seguridad de la web
 * 
 * @author bravo
 * @version 06/05/2022
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String PRODUCTO = "/api/producto/**";

	@Autowired
	private UserDetailsServiceImpl detailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(detailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http .authenticationProvider(authenticationProvider()) // Autenticación personalizada
				.authorizeRequests()
				.antMatchers("/", "/api/producto/**", "/index", "/usuario/registro", "/error", "/fragments", "/login/**", "/uploads/**", "/images/**")
				.permitAll()
				// Endpoint RESTProducto CRUD
				.antMatchers(HttpMethod.GET, PRODUCTO).permitAll().antMatchers(HttpMethod.POST, PRODUCTO).permitAll()
				.antMatchers(HttpMethod.PUT, PRODUCTO).permitAll().antMatchers(HttpMethod.DELETE, PRODUCTO).permitAll()
				.antMatchers("/usuario/registrar").permitAll().anyRequest().authenticated().and().formLogin()
				.loginProcessingUrl("/signin").loginPage("/usuario/login").permitAll().defaultSuccessUrl("/")
				.usernameParameter("nombreUsuario").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/cerrar")).logoutSuccessUrl("/").permitAll()
				.deleteCookies("JSESSIONID").and().rememberMe().tokenValiditySeconds(3600000).key("secret")
				.rememberMeParameter("checkRememberMe");
	}
}
