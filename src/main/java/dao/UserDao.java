package dao;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class UserDao {
    private final PoolConnection poolConnection = PoolConnection.getInstance();
    private final static UserDao INSTANCE = new UserDao();
    private static final Logger LOG = LogManager.getLogger(UserDao.class.getName());
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String ADD_NEW_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
    private static final String MESSAGE_FIND_BY_LOGIN_AND_PASSWORD = "Смотри в поиск пользователя по логину и паролю";
    private static final String MESSAGE_ADD = "Смотри в добавление нового пользователя";

    private UserDao() {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public User findUserByLoginAndPassword(String login, String pass) {
        User foundUser = null;
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD)) {
            prepareQueryToExecuteUser(login, pass, prepare);
            try (ResultSet resultSet = prepare.executeQuery()) {
                if (resultSet.next()) {
                    foundUser = fillUser(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error(MESSAGE_FIND_BY_LOGIN_AND_PASSWORD, e);
        }
        return foundUser;
    }

    public int add(User user) {
        int newId = -1;
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection
                .prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            prepareQueryToExecuteUser(user.getLogin(), user.getPassword(), prepare);
            prepare.execute();
            try (ResultSet res = prepare.getGeneratedKeys()) {
                if (res.next()) {
                     newId = res.getInt("id");
                }
            }
        } catch (SQLException e) {
            LOG.error(MESSAGE_ADD, e);
        }
        return newId;
    }

    private User fillUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("login");
        String login = resultSet.getString("password");
        return new User(id, name, login);
    }

    private void prepareQueryToExecuteUser(String login, String pass, PreparedStatement prepare) throws SQLException {
        prepare.setString(1, login);
        prepare.setString(2, pass);
    }
}
