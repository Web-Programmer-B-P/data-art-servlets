package servlets.ticket;

import model.Ticket;
import model.User;
import service.TicketService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list-tickets")
public class TicketListController extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();
    private static final String TICKET_JSP_URI = "ticket/ticket.jsp";
    private static final String CREATE_TICKET_URI = "/create-ticket";
    private static final int SIZE_OF_EMPTY = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        List<Ticket> tickets = ticketService.findAllTicketsById(currentUser.getId());
        if (tickets.size() > SIZE_OF_EMPTY) {
            req.setAttribute("tickets", tickets);
            req.getRequestDispatcher(TICKET_JSP_URI).forward(req, resp);
        } else {
            resp.sendRedirect(CREATE_TICKET_URI);
        }
    }
}
