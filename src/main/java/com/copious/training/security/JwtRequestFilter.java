package com.copious.training.security;

import com.copious.training.exceptions.JwtFilterException;
import com.copious.training.service.EmpUserDetailsService;
import com.copious.training.util.EnumExceptions;
import com.copious.training.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private EmpUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

        try {
            final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            String username = null;
            String jwt = null;
            if (!request.getRequestURL().toString().contains("/login") || request.getMethod() != "OPTIONS") {

                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    jwt = authorizationHeader.substring(7);
                    try {
                        username = jwtUtil.extractUsername(jwt);
                    } catch (Exception ex) {
                        throw new JwtFilterException(EnumExceptions.MALFORMED_TOKEN);
                    }
                }

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if (jwtUtil.validateToken(jwt, userDetails)) {

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    } else {
                        throw new JwtFilterException(EnumExceptions.INVALID_TOKEN);
                    }
                }
                chain.doFilter(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            throw new JwtFilterException(EnumExceptions.JWT_REQUEST_EXCEPTION);
        }
    }
}
