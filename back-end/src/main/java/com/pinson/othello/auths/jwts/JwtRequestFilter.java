package com.pinson.othello.auths.jwts;

import com.pinson.othello.players.OthelloPlayerService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final OthelloPlayerService othelloPlayerService;

    @Autowired
    public JwtRequestFilter(
        final JwtService jwtService,
        final OthelloPlayerService othelloPlayerService
    ) {
        super();
        this.jwtService = jwtService;
        this.othelloPlayerService = othelloPlayerService;
    }

    @Override
    protected void doFilterInternal(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final FilterChain chain
    ) throws ServletException, IOException {
        // Search bearer token in request header
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get token
        final String token = header.substring(7);
        // Validate token
        final String username = this.jwtService.extractUsername(token);

        if (
            StringUtils.isEmpty(username)
            ||
            SecurityContextHolder.getContext().getAuthentication() != null
        ) {
            chain.doFilter(request, response);
            return;
        }

        // Set user in context
        final UserDetails userDetails = othelloPlayerService.loadUserByUsername(username);

        if (!this.jwtService.isTokenValid(token, userDetails)) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );

        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        // continue
        chain.doFilter(request, response);
    }

}
