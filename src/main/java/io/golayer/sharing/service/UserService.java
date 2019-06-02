package io.golayer.sharing.service;

import io.golayer.sharing.model.User;

public interface UserService {
    User findOrCreate(String email);
}
