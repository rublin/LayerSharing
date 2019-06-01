package io.golayer.sharing.service;

import com.google.common.collect.Lists;
import io.golayer.sharing.model.User;
import io.golayer.sharing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOrCreate(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(new User(email));
    }
}
