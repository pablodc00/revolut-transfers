package com.revolut.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revolut.dao.Dao;
import com.revolut.model.User;
import com.revolut.store.UserDocument;

public class UserDao implements Dao<User> {
    
    @Override
    public Optional<User> get(String id) {
        return Optional.ofNullable(UserDocument.getInstance().getUsers().get(id));
    }

    @Override
    public List<User> getAll() {
        return UserDocument.getInstance().getUsers().values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        UserDocument.getInstance().getUsers().put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        UserDocument.getInstance().getUsers().remove(user.getId());
    }

}
