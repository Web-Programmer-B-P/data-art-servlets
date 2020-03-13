package servlets.ticket;

import service.TicketService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-ticket")
public class DeleteTicketController extends HttpServlet {
    private final static TicketService TICKET_SERVICE = TicketService.getInstance();
    private static final String LIST_TICKETS_URI = "/list-tickets";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("ticketId"));
            TICKET_SERVICE.deleteTicketById(id);
            resp.sendRedirect(LIST_TICKETS_URI);
        }
    }
}
