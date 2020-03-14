package dao;

import model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private final PoolConnection poolConnection = PoolConnection.getInstance();
    private final static TicketDao INSTANCE = new TicketDao();
    private static final Logger LOG = LogManager.getLogger(TicketDao.class.getName());
    private static final String UPDATE_TICKET_QUERY = "UPDATE ticket SET name=?, description=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM ticket WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM ticket WHERE id=?";
    private static final String ADD_NEW_TICKET = "INSERT INTO ticket (name, description, user_id) VALUES (?, ?, ?)";
    private static final String FIND_ALL_BY_ID = "SELECT * FROM ticket WHERE user_id=?";
    private static final int PRIMARY_KEY_INDEX = 1;
    private static final String MESSAGE_FIND_ALL_BY_ID = "Смотри в выборку всех тикетов по id";
    private static final String MESSAGE_ADD_TICKET = "Смотри в добавление нового тикета";
    private static final String MESSAGE_FIND_BY_ID = "Смотри в поск тикета по id";
    private static final String MESSAGE_UPDATE = "Смотри в обновление тикета";
    private static final String MESSAGE_DELETE = "Смотри в удаление тикета";

    private TicketDao() {

    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public List<Ticket> findAllById(int ticketId) {
        List<Ticket> foundTickets = new ArrayList<>();
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(FIND_ALL_BY_ID)) {
            prepare.setInt(PRIMARY_KEY_INDEX, ticketId);
            try (ResultSet resultSet = prepare.executeQuery()) {
                while (resultSet.next()) {
                    foundTickets.add(fillTicket(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error(MESSAGE_FIND_ALL_BY_ID, e);
        }
        return foundTickets;
    }

    public void add(Ticket ticket) {
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(ADD_NEW_TICKET)) {
            prepareQueryToExecute(ticket, prepare, ticket.getUserId());
            prepare.execute();
        } catch (SQLException e) {
            LOG.error(MESSAGE_ADD_TICKET, e);
        }
    }

    public Ticket findById(int ticketId) {
        Ticket foundTicket = null;
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(FIND_BY_ID)) {
            prepare.setInt(PRIMARY_KEY_INDEX, ticketId);
            try (ResultSet resultSet = prepare.executeQuery()) {
                while (resultSet.next()) {
                    foundTicket = fillTicket(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error(MESSAGE_FIND_BY_ID, e);
        }
        return foundTicket;
    }

    public void update(Ticket ticket) {
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(UPDATE_TICKET_QUERY)) {
            prepareQueryToExecute(ticket, prepare, ticket.getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            LOG.error(MESSAGE_UPDATE, e);
        }
    }

    public void delete(int ticketId) {
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(DELETE_QUERY)) {
            prepare.setInt(PRIMARY_KEY_INDEX, ticketId);
            prepare.executeUpdate();
        } catch (SQLException e) {
            LOG.error(MESSAGE_DELETE, e);
        }
    }

    private Ticket fillTicket(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        boolean status = resultSet.getBoolean("status");
        int userId = resultSet.getInt("user_id");
        return new Ticket(id, name, description, status, userId);
    }

    private void prepareQueryToExecute(Ticket ticket, PreparedStatement prepare, int userId) throws SQLException {
        prepare.setString(1, ticket.getName());
        prepare.setString(2, ticket.getDescription());
        prepare.setInt(3, userId);
    }
}
