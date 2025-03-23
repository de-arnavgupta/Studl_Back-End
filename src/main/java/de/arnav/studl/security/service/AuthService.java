package de.arnav.studl.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
    @Bean
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



    @Bean
    public boolean verifyOrganization(String email){
        String domain=email.getDomain(email);

        if(organizationJpaRepository.getOrganizationByDomain(domain)==null){
            return false;
        }
        return true;
    }
}
