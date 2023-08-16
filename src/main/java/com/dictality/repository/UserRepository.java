package com.dictality.repository;

import com.dictality.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll() throws Exception;
}
