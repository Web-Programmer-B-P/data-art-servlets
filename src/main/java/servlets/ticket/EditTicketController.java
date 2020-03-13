package servlets.ticket;

import model.Ticket;
import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-ticket")
public class EditTicketController extends HttpServlet {
    private final static TicketService TICKET_SERVICE = TicketService.getInstance();
    private static final String TICKET_EDIT_JSP_URI = "ticket/edit_ticket.jsp";
    private static final String ERROR_MESSAGE = "ticket not fount try again!";
    private static final String LIST_TICKETS_URI = "/list-tickets";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTicket = req.getParameter("ticketId");
        if (idTicket != null) {
            Ticket ticketFromStore = TICKET_SERVICE.findById(Integer.parseInt(idTicket));
            if (ticketFromStore != null) {
                req.setAttribute("ticket", ticketFromStore);
                req.getRequestDispatcher(TICKET_EDIT_JSP_URI).forward(req, resp);
                return;
            }
            req.setAttribute("error", ERROR_MESSAGE);
            resp.sendRedirect(LIST_TICKETS_URI);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action.equals("save")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("desc");
            Ticket currentTicket = TICKET_SERVICE.findById(id);
            currentTicket.setName(name);
            currentTicket.setDescription(description);
            TICKET_SERVICE.updateTicket(currentTicket);
            resp.sendRedirect(LIST_TICKETS_URI);
        }
    }
}
