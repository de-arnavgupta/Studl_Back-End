package de.arnav.studl.security.service;

import de.arnav.studl.exception.InvalidCredentialsException;
import de.arnav.studl.exception.LogoutFailedException;
import de.arnav.studl.model.BlacklistedToken;
import de.arnav.studl.repository.OrganizationJpaRepository;
import de.arnav.studl.repository.TokenBlacklistJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final OrganizationJpaRepository organizationJpaRepository;
    private final TokenBlacklistJpaRepository tokenBlacklistJpaRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService,OrganizationJpaRepository organizationJpaRepository,TokenBlacklistJpaRepository tokenBlacklistRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.organizationJpaRepository = organizationJpaRepository;
        this.tokenBlacklistJpaRepository=tokenBlacklistRepository;
    }

    //for login Authenticate user and return JWT token.

    public String authenticateUser(String email,String password) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails= userDetailsService.loadUserByUsername(email);

            return jwtService.generateToken(email);
        } catch(BadCredentialsException e){
            throw new InvalidCredentialsException();
        }
    }

    //Verify if an email belongs to a valid organization
    public boolean verifyOrganization(String email){
        String domain=getDomainFromEmail(email);

        return organizationJpaRepository.findByDomainName(domain).isPresent();
    }

    // Extracts domain from email
    private String getDomainFromEmail(String email) {
        String domain = email.substring(email.indexOf("@") + 1); // Extracts domain (sst.scaler.com)

        String[] parts = domain.split("\\.");

        if (parts.length >= 2) {
            return parts[parts.length - 2]; // Extract the second-last part (scaler)
        } else {
            return domain; //if domain is not structured as expected
        }
    }


    // Add a token to the blacklist.
    public void addToken(String token) {
        if (isTokenBlacklisted(token)) {
            return;
        }

        LocalDateTime expirationTime = jwtService.getExpirationTime(token); // Extract expiry from JWT
        tokenBlacklistJpaRepository.save(new BlacklistedToken(token, expirationTime));
    }

   // Check if a token is blacklisted.
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistJpaRepository.findByToken(token).isPresent();
    }

    //method to logout
    public void logout(HttpServletRequest request) {
        try{
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                addToken(token);
            }
        } catch (Exception e){
            throw new LogoutFailedException();
        }
    }
}
