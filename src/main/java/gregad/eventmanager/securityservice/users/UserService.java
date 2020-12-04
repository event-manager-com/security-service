package gregad.eventmanager.securityservice.users;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Greg Adler
 */
@Component
public class UserService {
    private List<User>users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User("user-service","KDlewe*889Km)k,kfl+cseE+-9kk,k44rf.dJDjjjd?kJ"));
        users.add(new User("user-event-service","k0(kdDWW:pkW#jcskl:<DMKN!l568%$tgH-j,m"));
        users.add(new User("router-service","lbjto^h8k)nk+csS=asFEoe$hjsep=j+mMKk"));
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
