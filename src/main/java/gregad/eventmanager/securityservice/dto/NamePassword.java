package gregad.eventmanager.securityservice.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author Greg Adler
 */
@Data
public class NamePassword {
    private String name;
    private String password;
}
