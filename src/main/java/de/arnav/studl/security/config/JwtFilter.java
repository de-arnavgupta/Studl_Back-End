package de.arnav.studl.security.config;

import de.arnav.studl.exception.JwtAuthenticationException;
import de.arnav.studl.security.service.AuthService;
import de.arnav.studl.security.service.JwtService;
import de.arnav.studl.security.service.MyUserDetailService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailService myUserDetailService;
    private final AuthService authService;

    public JwtFilter(JwtService jwtService,MyUserDetailService myUserDetailService,AuthService authService) {
        this.jwtService=jwtService;
        this.myUserDetailService=myUserDetailService;
        this.authService=authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token="";
        String email="";

        try{

            String authHeader = request.getHeader("Authorization");

            if(authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                email=jwtService.extractEmail(token);
            }

            // Check if token is blacklisted (User has logged out)
            if (authService.isTokenBlacklisted(token)) {
                throw new JwtAuthenticationException();
            }


            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = myUserDetailService.loadUserByUsername(email);

                if(!jwtService.validateToken(token,userDetails)){
                    throw new JwtAuthenticationException();
                }

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch(JwtException e){
            throw new JwtAuthenticationException();
        }

        filterChain.doFilter(request, response);
    }
}
