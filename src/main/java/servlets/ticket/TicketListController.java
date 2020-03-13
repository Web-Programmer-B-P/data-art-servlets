package servlets.ticket;

import model.Ticket;
import model.User;
import service.TicketService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/list-tickets")
public class TicketListController extends HttpServlet {
    private final static TicketService TICKET_SERVICE = TicketService.getInstance();
    private static final String TICKET_TICKET_JSP_URI = "ticket/ticket.jsp";
    private static final String CREATE_TICKET_URI = "/create-ticket";
    private static final String SING_IN_URI = "/sing-in";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (!session.isNew()) {
            User currentUser = (User) session.getAttribute("user");
            List<Ticket> tickets = TICKET_SERVICE.findAllTicketsById(currentUser.getId());
            if (tickets.size() > 0) {
                req.setAttribute("tickets", tickets);
                req.getRequestDispatcher(TICKET_TICKET_JSP_URI).forward(req, resp);
            } else {
                resp.sendRedirect(CREATE_TICKET_URI);
            }
        } else {
            resp.sendRedirect(SING_IN_URI);
        }
    }
}
