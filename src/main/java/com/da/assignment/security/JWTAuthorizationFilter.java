package com.da.assignment.security;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION = "Authorization";

  private static final String BEARER = "Bearer";

  private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);


  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String headervalue = request.getHeader(AUTHORIZATION);
    if (isAdminRequest(request, headervalue)) {
      authorizeRequest(headervalue.replace(BEARER, ""));
    }
    chain.doFilter(request, response);
  }

  private void authorizeRequest(String token) {
    FirebaseToken decodedToken = null;
    try {
      decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.trim());
    } catch (FirebaseAuthException e) {
      log.error("Failed to authenticate with firebase", e);
      return;
    }
    String uid = decodedToken.getUid();
    if (StringUtils.isNotBlank(uid)) {
      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
          null, null, Collections.singleton(new SimpleGrantedAuthority("AUTHENTICATED"))));
    }
  }

  private boolean isAdminRequest(HttpServletRequest request, String headervalue) {
      if (StringUtils.isNotBlank(headervalue) && headervalue.startsWith(BEARER)) {
        return true;
      }
    return false;
  }
}
