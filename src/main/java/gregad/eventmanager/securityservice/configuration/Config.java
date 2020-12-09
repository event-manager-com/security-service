package gregad.eventmanager.securityservice.configuration;

import gregad.eventmanager.securityservice.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Greg Adler
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class Config {
    
    @Resource(name = "getUsers")
    private List<User> users;

    @Bean
    public List<User> getUsers(
            @Value("#{'${credential.users.name}'.split('@')}")List<String> names,
            @Value("#{'${credential.users.password}'.split('@')}") List<String> passwords
    ) {

        List<User> userList = new ArrayList<>();
        for (int i= 0;i< names.size();i++){
            userList.add(new User(names.get(i),passwords.get(i)));
        }
        return userList;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
