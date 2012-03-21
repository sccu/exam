package name.sccu.springexam.server.springconfig;

import java.util.HashSet;
import java.util.Set;


public class UserDaoImpl implements UserDao {
	
	private static final Set<String> users = new HashSet<String>();
	
	public Set<String> getUsers() {
		synchronized(users) {
			return new HashSet<String>(users);
		}
	}

	public void save(String name) {
		synchronized(users) {
			users.add(name);
		}
	}

}
