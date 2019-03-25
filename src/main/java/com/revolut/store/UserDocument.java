package com.revolut.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.revolut.model.User;

public class UserDocument {
   
    private Map<String, User> users = null;    
    private static UserDocument userDocument = null;
    
    private UserDocument() {
        users = new ConcurrentHashMap<>();
        //To tests purposes
        User user = new User("aaaa", "aaaa", 105.00);        
        users.put(user.getId(), user);        
        
        user = new User("bbbb", "bbbb", 105.00);
        users.put(user.getId(), user);
        
        user = new User("cccc", "cccc", 105.00);
        users.put(user.getId(), user);
        
        user = new User("dddd", "dddd", 105.00);
        users.put(user.getId(), user);        
    }
    
    public static UserDocument getInstance() {
        if (null == userDocument) {
            userDocument = new UserDocument();
        }
        return userDocument;
    }
    
    public Map<String, User> getUsers() {
        return users;
    }
}
