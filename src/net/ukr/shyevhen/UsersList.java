package net.ukr.shyevhen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UsersList {
	private static Map<String, String> usersList = Collections.synchronizedMap(new HashMap<>());

	public UsersList() {
		super();
	}

	public Map<String, String> getUsersList() {
		return usersList;
	}

	public boolean checkUser(String login, String password) {
		if (usersList.containsKey(login) && usersList.get(login).equals(password)) {
			return true;
		}
		return false;
	}

	public boolean addUser(String login, String password) {
		if (!usersList.containsKey(login)) {
			usersList.put(login, password);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "UsersList [usersList=" + usersList + "]";
	}

}
