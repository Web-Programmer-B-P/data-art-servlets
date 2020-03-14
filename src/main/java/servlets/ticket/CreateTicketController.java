package servlets.ticket;

import service.TicketService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-ticket")
public class CreateTicketController extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();
    private static final String CREATE_TICKET_JSP_URI = "ticket/create_ticket.jsp";
    private static final String USER_TICKETS_URI = "/list-tickets";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_TICKET_JSP_URI).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketService.addTicket(req);
        resp.sendRedirect(USER_TICKETS_URI);
    }
}
