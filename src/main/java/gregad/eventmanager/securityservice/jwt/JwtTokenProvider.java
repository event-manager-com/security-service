package gregad.eventmanager.securityservice.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

import static gregad.eventmanager.securityservice.api.ApiConstants.PREFIX;

@Component

public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;
//    @Autowired
//    private UserService userService;


    @PostConstruct
    protected void init(){
        secret= Base64.getEncoder().encodeToString(secret.getBytes());
    }

    
     String createToken(String name, String password){
        Claims claims= Jwts.claims().setSubject(name+"@"+password);

        Date now=new Date();
        Date validity=new Date(now.getTime()+validityInMilliseconds);
        return Jwts.builder().
                setClaims(claims).
                setIssuedAt(now).
                setExpiration(validity).
                signWith(SignatureAlgorithm.HS256,secret).
                compact();
    }
    
  
     String resolveToken(String requestToken){
        if (requestToken!=null && requestToken.startsWith(PREFIX)){
            return requestToken.substring(7) ;
        }
        return null;
    }
    
     String validateToken(String token){
        try {
            Jws<Claims> claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            String emailPassword = claims.getBody().getSubject();
            
            if (claims.getBody().getExpiration().before(new Date())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"JWT token is expired or invalid");
                // token=updateToken(emailPassword);
            }
            return token;
        } catch (JwtException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.PROXY_AUTHENTICATION_REQUIRED,"JWT token is expired or invalid");
        }
    }
//     String updateToken(String emailPassword){
//        String[] emailAndPassword = emailPassword.split("@");
//        User user = userService.getUser(emailAndPassword[0], emailAndPassword[1]);
//        String token = createToken(emailAndPassword[0], emailAndPassword[1]);
//        user.setToken(token);
//        return token;
//    }
    
    
  
}
