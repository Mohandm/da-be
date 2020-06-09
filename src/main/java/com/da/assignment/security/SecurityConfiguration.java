package com.da.assignment.security;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers("/h2-console").permitAll();
    http.authorizeRequests().anyRequest()
        .hasAuthority("AUTHENTICATED");
    http.formLogin().disable();
    http.httpBasic().disable();
    http.addFilterAfter(authorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.cors();

  }

  @Bean
  public JWTAuthorizationFilter authorizationFilter() throws IOException {
    InputStream is = new ClassPathResource("da-assignment.json").getInputStream();
    FirebaseOptions options =
        new FirebaseOptions.Builder().setCredentials(GoogleCredentials
            .fromStream(is))
            .setDatabaseUrl("https://da-assignment-ffd9d.firebaseio.com").build();

    FirebaseApp.initializeApp(options);

    return new JWTAuthorizationFilter();

  }
}
