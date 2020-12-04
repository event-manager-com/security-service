package gregad.eventmanager.securityservice.jwt;

import gregad.eventmanager.securityservice.jwt.JwtTokenProvider;
import gregad.eventmanager.securityservice.users.User;
import gregad.eventmanager.securityservice.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Greg Adler
 */
@Service
public class SecurityService {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    
    public boolean validateToken(String token){
        String actualToken = tokenProvider.resolveToken(token);
        if (actualToken==null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"JWT token is expired or invalid");
        }
         tokenProvider.validateToken(actualToken);
        return true;
    }
    
    public String generateToken(String name,String password){
       // User user = userService.getUser(name, password);
        String token = tokenProvider.createToken(name, password);
        //user.setToken(token);
        return token;
    }
}
