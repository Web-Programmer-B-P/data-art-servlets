package servlets.ticket;

import service.TicketService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-ticket")
public class DeleteTicketController extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();
    private static final String LIST_TICKETS_URI = "/list-tickets";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ticketService.deleteTicketById(req);
        resp.sendRedirect(LIST_TICKETS_URI);
    }
}
