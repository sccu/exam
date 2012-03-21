package name.sccu.springexam;

import static org.junit.Assert.*;

import java.util.Set;

import name.sccu.springexam.server.springconfig.SystemConfig;
import name.sccu.springexam.server.springconfig.UserDao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTest {

	@Test
	public void test() {
		ApplicationContext context = new AnnotationConfigApplicationContext(SystemConfig.class);
		UserDao userDao = context.getBean(UserDao.class);
		
		assertNotNull(userDao);
		
		userDao.save("scsc");
		assertTrue(userDao.getUsers().contains("scsc"));
		assertFalse(userDao.getUsers().contains("sccu"));
		
		Set<String> oldUsers = userDao.getUsers();
		userDao.save("sccu");
		assertFalse(oldUsers.contains("sccu"));
		assertTrue(userDao.getUsers().contains("sccu"));
	}

}
