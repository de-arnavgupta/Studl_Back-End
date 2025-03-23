package de.arnav.studl.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final Email email;
    private final OrganizationJpaRepository organizationJpaRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService,Email email,OrganizationJpaRepository organizationJpaRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.email = email;
        this.organizationJpaRepository = organizationJpaRepository;
    }

    //for login

    public String authenticateUser(String email,String password) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails= userDetailsService.loadUserByUsername(email);

            return jwtService.generateToken(email);
        } catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid email or password");
        }
        catch(Exception e){
            throw new RuntimeException("Authenticaiton failed"+e.getMessage());
        }
    }




    public boolean verifyOrganization(String email){
        String domain=email.getDomain(email);

        if(organizationJpaRepository.getOrganizationByDomain(domain)==null){
            return false;
        }
        return true;
    }


    public void addtoken(String token) {
        LocalDateTime expirationTime = jwtService.getExpirationTime(token); // Extract expiry from JWT
        tokenBlacklistRepository.save(new BlacklistedToken(token, expirationTime));
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.findByToken(token).isPresent();
    }

    //method to logout
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            addtoken(token);
            return ResponseEntity.ok("Logged out successfully.");
        }

        return ResponseEntity.badRequest().body(null);
    }

}
