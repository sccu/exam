package name.sccu.springexam.server.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {
	
	@Bean
	public UserDao createUserDao() {
		return new UserDaoImpl();
	}

}
