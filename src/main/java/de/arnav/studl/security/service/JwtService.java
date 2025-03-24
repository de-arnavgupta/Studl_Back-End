package de.arnav.studl.security.service;

import de.arnav.studl.exception.JwtAuthenticationException;
import de.arnav.studl.model.RoleType;
import de.arnav.studl.model.User;
import de.arnav.studl.repository.UserJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final String secretKey;
    private final UserJpaRepository userJpaRepository;


    public JwtService(@Value("${jwt.secret}") String secretKey,UserJpaRepository userJpaRespository) {
        this.secretKey = secretKey;
        this.userJpaRepository=userJpaRespository;

    }

    //generate token and set claims
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        User user=userJpaRepository.findByUserEmail(email).orElseThrow(()-> new  JwtAuthenticationException());
        Set<RoleType> roles=user.getRoleType();

        List<String> role=roles.stream().map(RoleType :: name).collect(Collectors.toList());

        return Jwts.builder()
                .subject(email)
                .claim("roles",role)
                .issuedAt(new Date(System.currentTimeMillis()))//set issue time
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject); //extracting the email stored inside jwt
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =extractAllClaims(token); //now claim holds all the data inside the Jwt
        return claimsResolver.apply(claims); //we apply claim resolver function to extract one specific claim
    }

    //generic method to extract claims from the database
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new JwtAuthenticationException();
        }
    }


    public boolean validateToken(String token, User user) {
        try{
            final String email=extractEmail(token);
            return (email.equals(user.getUserEmail()) && !isTokenExpired(token));
        } catch(Exception e){
            throw new JwtAuthenticationException();
        }

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public LocalDateTime getExpirationTime(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


}
