package service;

import dao.TicketDao;
import model.Ticket;
import model.User;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {

    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<Ticket> findAllTicketsById(int ticketId) {
        return ticketDao.findAllById(ticketId);
    }

    public void addTicket(HttpServletRequest request) {
        ticketDao.add(fillTicket(request, new Ticket()));
    }

    public Ticket findById(HttpServletRequest request) {
        String idTicket = request.getParameter("ticketId");
        Ticket foundTicket = null;
        if (idTicket != null) {
            foundTicket = ticketDao.findById(Integer.parseInt(idTicket));
        }
        return foundTicket;
    }

    public void updateTicket(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Ticket currentTicket = ticketDao.findById(id);
        ticketDao.update(fillTicket(request, currentTicket));
    }

    public void deleteTicketById(HttpServletRequest request) {
        String tickedId = request.getParameter("ticketId");
        if (tickedId != null) {
            int id = Integer.parseInt(request.getParameter("ticketId"));
            ticketDao.delete(id);
        }
    }

    private Ticket fillTicket(HttpServletRequest request, Ticket ticket) {
        User currentUser = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String description = request.getParameter("desc");
        if (name != null) {
            ticket.setName(name);
        }
        if (description != null) {
            ticket.setDescription(description);
        }
        if (currentUser != null) {
            ticket.setUserId(currentUser.getId());
        }
        return ticket;
    }
}
