package gregad.eventmanager.securityservice.rest;

import gregad.eventmanager.securityservice.dto.NamePassword;
import gregad.eventmanager.securityservice.dto.Token;
import gregad.eventmanager.securityservice.jwt.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static gregad.eventmanager.securityservice.api.ApiConstants.*;

/**
 * @author Greg Adler
 */
@RestController
public class Controller {
    @Autowired
    private SecurityService securityService;
    
    @PostMapping(value = VALIDATE)
    public Boolean validate(@RequestHeader(name=HEADER) String token){
        return securityService.validateToken(token);
    }
    
    @PostMapping(value = GENERATE)
    public Token generateToken(@RequestBody NamePassword namePassword){
        String token = securityService.generateToken(namePassword.getName(), namePassword.getPassword());
        System.out.println(PREFIX+token);
        return new Token(PREFIX+token);
    }
}
