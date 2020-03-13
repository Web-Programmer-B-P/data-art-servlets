package service;

import dao.UserDao;
import model.User;

import java.util.List;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserByLoginAndPassword(String login, String pass) {
        return userDao.findUserByLoginAndPassword(login, pass);
    }

    public User add(User user) {
        int id = userDao.add(user);
        if (id != -1) {
            user.setId(id);
        }
        return user;
    }
}
