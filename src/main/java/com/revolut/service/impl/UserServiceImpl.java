package com.revolut.service.impl;

import java.util.List;
import java.util.Optional;

import com.revolut.dao.Dao;
import com.revolut.dao.impl.UserDao;
import com.revolut.model.User;
import com.revolut.service.UserService;

public class UserServiceImpl implements UserService {

    private Dao<User> userDao;

    public UserServiceImpl(Dao<User> userDao) {
        this.userDao = new UserDao();
    }

    @Override
    public List<User> getUsers() {
        return this.userDao.getAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userDao.get(userId);
    }    
    
}
