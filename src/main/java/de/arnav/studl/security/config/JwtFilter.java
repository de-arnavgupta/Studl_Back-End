package de.arnav.studl.security.config;

import de.arnav.studl.security.service.JwtService;
import de.arnav.studl.security.service.MyUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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
import java.security.SignatureException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailService myUserDetailService;

    public JwtFilter(JwtService jwtService,MyUserDetailService myUserDetailService){
        this.jwtService=jwtService;
        this.myUserDetailService=myUserDetailService;
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

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = myUserDetailService.loadUserByUsername(email);

                if(!jwtService.validateToken(token,userDetails)){
                    throw new JwtAuthenticationException("Invalid or expired JWT token");
                }

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch(ExpiredJwtException e){
            throw new JwtAuthenticationException("JWT token has expired",e);
        } catch (MalformedJwtException e) {
            throw new JwtAuthenticationException("Malformed JWT token", e);
        } catch (SignatureException e) {
            throw new JwtAuthenticationException("Invalid JWT signature", e);
        } catch (IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is missing or invalid", e);
        } catch (Exception e) {
            throw new JwtAuthenticationException("An unexpected error occurred during authentication", e);
        }

        filterChain.doFilter(request, response);
    }
}
