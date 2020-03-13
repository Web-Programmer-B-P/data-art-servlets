package service;

import dao.TicketDao;
import model.Ticket;

import java.util.List;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private final static TicketDao TICKET_DAO = TicketDao.getInstance();

    private TicketService() {

    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<Ticket> findAllTicketsById(int ticketId) {
        return TICKET_DAO.findAllById(ticketId);
    }

    public void addTicket(Ticket newTicket) {
        TICKET_DAO.add(newTicket);
    }

    public Ticket findById(int ticketId) {
        return TICKET_DAO.findById(ticketId);
    }

    public void updateTicket(Ticket ticket) {
        TICKET_DAO.update(ticket);
    }

    public void deleteTicketById(int ticketId) {
        TICKET_DAO.delete(ticketId);
    }
}
