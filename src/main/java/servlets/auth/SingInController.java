package servlets.auth;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sing-in")
public class SingInController extends HttpServlet {
    private final static UserService USER_SERVICE = UserService.getInstance();
    private static final String ERROR_MESSAGE = "user not found!";
    private static final String PATH_TO_USER_LIST = "/list-tickets";
    private static final String PATH_TO_SING_IN_JSP = "auth/sing_in.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PATH_TO_SING_IN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        if (login != null && password != null) {
            User userFromStore = USER_SERVICE.findUserByLoginAndPassword(login, password);
            if (userFromStore != null) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", userFromStore);
                resp.sendRedirect(PATH_TO_USER_LIST);
            } else {
                req.setAttribute("error", ERROR_MESSAGE);
                doGet(req, resp);
            }
        }
    }
}
