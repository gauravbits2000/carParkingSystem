package com.markit.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	
	 @Value("${ldap.urls}")
	 private String ldapUrls;
	 
	 @Value("${ldap.base.dn}")
	 private String ldapBaseDn;
	 
	 @Value("${ldap.username}")
	 private String ldapSecurityPrincipal;
	 
	 @Value("${ldap.password}")
	 private String ldapPrincipalPassword;
	 
	 @Value("${ldap.user.dn.pattern}")
	 private String ldapUserDnPattern;
	 
	 @Value("${ldap.enabled}")
	 private String ldapEnabled;
	 
	
   //Override this method to defines which URL paths should be secured and which should not
   @Override
   protected void configure(HttpSecurity http) throws Exception 
   {
      http
         .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .antMatchers("/login**").permitAll() 			 // For Loing Page
            .antMatchers("/profile/**").fullyAuthenticated() // Added for LDAP
            .anyRequest().authenticated()
            .and()
         .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .permitAll()
            .and()
         .logout()
     		.invalidateHttpSession(true)
     		.deleteCookies("JSESSIONID")
            .permitAll();
   }
   
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
   {
      auth
         .inMemoryAuthentication()
         .withUser("admin").password("password").roles("USER");
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception 
   {
   
	   if(Boolean.parseBoolean(ldapEnabled)) 
	   {
		   auth
			   .ldapAuthentication()
				   .contextSource()
				   .url(ldapUrls + ldapBaseDn)
				   .managerDn(ldapSecurityPrincipal)
				   .managerPassword(ldapPrincipalPassword)
				   .and()
			   .userDnPatterns(ldapUserDnPattern);
	   } 
	   else 
	   {
	           auth
	           .inMemoryAuthentication()
	               .withUser("user").password("password").roles("USER")
	               .and()
	               .withUser("admin").password("admin").roles("ADMIN");
	   }
   }
   
}
