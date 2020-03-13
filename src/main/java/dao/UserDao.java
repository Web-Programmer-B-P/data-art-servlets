package dao;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DaoUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final static UserDao INSTANCE = new UserDao();
    private final static PoolConnection poolConnection = PoolConnection.getInstance();
    private static final Logger LOG = LogManager.getLogger(UserDao.class.getName());
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String ADD_NEW_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
    private static final String FIND_ALL = "SELECT * FROM users";

    private UserDao() {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = poolConnection.getConnection();
             Statement prepare = connection.createStatement()) {
            try (ResultSet resultSet = prepare.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    allUsers.add(DaoUtils.fillUser(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error("Смотри в выборку всех пользователей", e);
        }
        return allUsers;
    }


    public User findUserByLoginAndPassword(String login, String pass) {
        User foundUser = null;
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD)) {
            DaoUtils.prepareQueryToExecuteUser(login, pass, prepare);
            try (ResultSet resultSet = prepare.executeQuery()) {
                if (resultSet.next()) {
                    foundUser = DaoUtils.fillUser(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("Смотри в поиск пользователя по логину и паролю", e);
        }
        return foundUser;
    }

    public int add(User user) {
        int newId = -1;
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection
                .prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            DaoUtils.prepareQueryToExecuteUser(user.getLogin(), user.getPassword(), prepare);
            prepare.execute();
            try (ResultSet res = prepare.getGeneratedKeys()) {
                if (res.next()) {
                     newId = res.getInt("id");
                }
            }
        } catch (SQLException e) {
            LOG.error("Смотри в добавление нового пользователя", e);
        }
        return newId;
    }
}
