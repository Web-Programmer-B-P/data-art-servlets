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

@WebServlet("/create-ticket")
public class CreateTicketController extends HttpServlet {
    private static final TicketService TICKET_SERVICE = TicketService.getInstance();
    private static final String CREATE_TICKET_JSP_URI = "ticket/create_ticket.jsp";
    private static final String ERROR_MESSAGE = "Fill all fields!";
    private static final String USER_TICKETS_URI = "/list-tickets";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_TICKET_JSP_URI).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("desc");
        if (!(name.isEmpty() && description.isEmpty())) {
            User currentUser = (User) req.getSession().getAttribute("user");
            Ticket newTicket = new Ticket(name, description, currentUser.getId());
            TICKET_SERVICE.addTicket(newTicket);
            resp.sendRedirect(USER_TICKETS_URI);
        } else {
            req.setAttribute("error", ERROR_MESSAGE);
            doGet(req, resp);
        }
    }
}
