package servlets.ticket;

import service.TicketService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-ticket")
public class EditTicketController extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();
    private static final String TICKET_EDIT_JSP_URI = "ticket/edit_ticket.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("ticket", ticketService.findById(req));
        req.getRequestDispatcher(TICKET_EDIT_JSP_URI).forward(req, resp);
    }
}
