package com.pragma.userservice.infraestructure.security.jwt;

import com.pragma.userservice.infraestructure.security.userdetails.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Slf4j
public class JwtProvider {

    private static final String JWT_SECRET_KEY = "clavelosuficientementelargaparaquehmac256lasoporte";
    private static final Long JWT_EXPIRATION_TIME = 3600000L;

    private JwtProvider(){
        throw new IllegalStateException("Utility Class");
    }

    public static String createToken(Authentication auth){

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) auth.getPrincipal();
        Long id = userDetailsImpl.getId();
        String email = userDetailsImpl.getUsername();
        String rol = userDetailsImpl.getAuthorities().toArray()[0].toString();

        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()))
                .compact();
    }
    public static boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch(Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
    public static String getSubjectFromToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(Exception e){
            return null;
        }
    }
    public static String getRolFromToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("rol", String.class);
        }catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
    public static Long getIdFromToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id", Long.class);
        }catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
