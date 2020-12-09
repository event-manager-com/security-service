package gregad.eventmanager.securityservice.users;

import gregad.event_manager.loggerstarter.aspect.DoLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Greg Adler
 */
@Component
public class UserService {

    @Autowired
    private List<User>users;

    public UserService() {
    }

    public User getUser(String name, String password){
        return users.stream()
                .filter(u->u.getName().equals(name) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Invalid username or password");
        });
    }
//    public User getUserByToken(String token){
//        return users.stream()
//                .filter(u->u.getToken().equals(token))
//                .findFirst()
//                .orElseThrow(()->{
//                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,"JWT token is expired or invalid");
//                });
//    }
    
}
