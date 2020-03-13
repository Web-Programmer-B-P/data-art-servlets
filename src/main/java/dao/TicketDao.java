package dao;

import model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DaoUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();
    private final static PoolConnection poolConnection = PoolConnection.getInstance();
    private static final Logger LOG = LogManager.getLogger(TicketDao.class.getName());
    private static final String UPDATE_TICKET_QUERY = "UPDATE ticket SET name=?, description=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM ticket WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM ticket WHERE id=?";
    private static final String ADD_NEW_TICKET = "INSERT INTO ticket (name, description, user_id) VALUES (?, ?, ?)";
    private static final String FIND_ALL_BY_ID = "SELECT * FROM ticket WHERE user_id=?";

    private TicketDao() {

    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public List<Ticket> findAllById(int ticketId) {
        List<Ticket> foundTickets = new ArrayList<>();
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(FIND_ALL_BY_ID)) {
            prepare.setInt(1, ticketId);
            try (ResultSet resultSet = prepare.executeQuery()) {
                while (resultSet.next()) {
                    foundTickets.add(DaoUtils.fillTicket(resultSet));
                }
            }
        } catch (SQLException e) {
            LOG.error("Смотри в выборку всех тикетов по id", e);
        }
        return foundTickets;
    }

    public void add(Ticket ticket) {
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(ADD_NEW_TICKET)) {
            DaoUtils.prepareQueryToExecute(ticket, prepare, ticket.getUserId());
            prepare.execute();
        } catch (SQLException e) {
            LOG.error("Смотри в добавление нового тикета", e);
        }
    }

    public Ticket findById(int ticketId) {
        Ticket foundTicket = null;
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(FIND_BY_ID)) {
            prepare.setInt(1, ticketId);
            try (ResultSet resultSet = prepare.executeQuery()) {
                while (resultSet.next()) {
                    foundTicket = DaoUtils.fillTicket(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("Смотри в поск тикета по id", e);
        }
        return foundTicket;
    }

    public void update(Ticket ticket) {
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(UPDATE_TICKET_QUERY)) {
            DaoUtils.prepareQueryToExecute(ticket, prepare, ticket.getId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Смотри в обновление тикета", e);
        }
    }

    public void delete(int ticketId) {
        try (Connection connection = poolConnection.getConnection();
             PreparedStatement prepare = connection.prepareStatement(DELETE_QUERY)) {
            prepare.setInt(1, ticketId);
            prepare.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Смотри в удаление тикета", e);
        }
    }
}
