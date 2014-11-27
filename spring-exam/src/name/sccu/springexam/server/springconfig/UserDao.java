package name.sccu.springexam.server.springconfig;

import java.util.Set;

public interface UserDao {
	
	Set<String> getUsers();

	void save(String string);

}
