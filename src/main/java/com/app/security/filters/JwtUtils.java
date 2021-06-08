package com.app.security.filters;

import com.app.models.user.User;
import com.app.security.SecurityConstants;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@Log4j
public class JwtUtils {

    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.KEY)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SecurityConstants.KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken, HttpServletRequest request) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.KEY).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
//            log.error("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
//            log.error("JWT token is expired from: " + getIp(request));
        } catch (UnsupportedJwtException e) {
//            log.error("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
//            log.error("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }

    public static String getIp(HttpServletRequest request) {
        return request.getHeader("X-FORWARDED-FOR");
    }
}
