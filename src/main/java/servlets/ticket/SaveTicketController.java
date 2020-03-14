package servlets.ticket;

import service.TicketService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save-item")
public class SaveTicketController extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();
    private static final String LIST_TICKETS_URI = "/list-tickets";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketService.updateTicket(req);
        resp.sendRedirect(LIST_TICKETS_URI);
    }
}
