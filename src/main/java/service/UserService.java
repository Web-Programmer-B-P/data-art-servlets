package service;

import dao.UserDao;
import model.User;
import javax.servlet.http.HttpServletRequest;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public User findUserByLoginAndPassword(HttpServletRequest request) {
        User foundedUser = null;
        String login = request.getParameter("login");
        String password = request.getParameter("pwd");
        if (login != null && password != null) {
            foundedUser = userDao.findUserByLoginAndPassword(login, password);
        }
        return foundedUser;
    }

    public User add(HttpServletRequest request) {
        User newUser = fillUser(request);
        newUser.setId(userDao.add(newUser));
        return newUser;
    }

    private User fillUser(HttpServletRequest request) {
        User user = new User();
        String login = request.getParameter("login");
        String password = request.getParameter("pwd");
        if (login != null) {
            user.setLogin(login);
        }
        if (password != null) {
            user.setPassword(password);
        }
        return user;
    }
}
