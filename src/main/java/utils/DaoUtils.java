package utils;

import model.Ticket;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

final public class DaoUtils {
    private DaoUtils() {

    }

    public static Ticket fillTicket(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        boolean status = resultSet.getBoolean("status");
        int userId = resultSet.getInt("user_id");
        return new Ticket(id, name, description, status, userId);
    }

    public static void prepareQueryToExecute(Ticket ticket, PreparedStatement prepare, int userId) throws SQLException {
        prepare.setString(1, ticket.getName());
        prepare.setString(2, ticket.getDescription());
        prepare.setInt(3, userId);
    }

    public static User fillUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("login");
        String login = resultSet.getString("password");
        return new User(id, name, login);
    }

    public static void prepareQueryToExecuteUser(String login, String pass, PreparedStatement prepare) throws SQLException {
        prepare.setString(1, login);
        prepare.setString(2, pass);
    }
}
