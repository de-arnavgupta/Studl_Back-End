package de.arnav.studl.security.config;

import de.arnav.studl.exception.JwtAuthenticationException;
import de.arnav.studl.exception.UserNotFoundException;
import de.arnav.studl.model.User;
import de.arnav.studl.repository.UserJpaRepository;
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
    private final UserJpaRepository userJpaRepository;


    public JwtFilter(JwtService jwtService, MyUserDetailService myUserDetailService,  UserJpaRepository userJpaRepository) {
        this.jwtService=jwtService;
        this.myUserDetailService=myUserDetailService;
        this.userJpaRepository = userJpaRepository;

    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token="";
        String email="";

        try{

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }


            token = authHeader.substring(7);
            email = jwtService.extractEmail(token);


            // Check if token is blacklisted (User has logged out)
            if (jwtService.isTokenBlacklisted(token)) {
                throw new JwtAuthenticationException("Error in blacklist filter");
            }


            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User user= userJpaRepository.findByUserEmail(email).orElseThrow(() -> new UserNotFoundException("User with this email not found. [Method: findUserByEmail]"));
                UserDetails userDetails = myUserDetailService.loadUserByUsername(email);

                if(!jwtService.validateToken(token,user)){
                    throw new JwtAuthenticationException("Error in validateToken in filter");
                }

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch(JwtException e){
            throw new JwtAuthenticationException("Error in filter");
        }

        filterChain.doFilter(request, response);
    }
}
