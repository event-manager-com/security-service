package gregad.eventmanager.securityservice.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Greg Adler
 */
@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String password;
}
