package me.technopvp.common.utilities.player;

import java.util.HashMap;

import me.technopvp.common.dCommon;

public class UserManager {
    dCommon plugin;
    HashMap<String, User> users;

    public UserManager(dCommon plugin) {
        this.plugin = plugin;
        this.users = new HashMap<String, User>();
    }

    public User createPlayer(String name) {
    	User temp = new User(plugin, name);
        this.users.put(name, temp);
        return temp;
    }

    public User getUser(String name) {
    	User user = this.users.get(name);
        return (user == null ? createPlayer(name) : user);
    }

    public void removeUser(String name) {
        this.users.remove(name);
    }

    public boolean userExists(String name) {
        return this.users.containsKey(name);
    }
}