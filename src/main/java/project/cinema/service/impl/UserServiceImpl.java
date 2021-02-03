package project.cinema.service.impl;

import java.util.Optional;
import project.cinema.dao.UserDao;
import project.cinema.lib.Inject;
import project.cinema.lib.Service;
import project.cinema.model.User;
import project.cinema.service.UserService;
import project.cinema.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        String hashPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(hashPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
