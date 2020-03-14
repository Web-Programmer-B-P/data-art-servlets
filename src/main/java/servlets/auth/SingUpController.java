package servlets.auth;

import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sing-up")
public class SingUpController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private static final String PATH_TO_USER_LIST = "/list-tickets";
    private static final String ERROR_MESSAGE = "user already exist!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auth/sing_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userService.findUserByLoginAndPassword(req) == null) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", userService.add(req));
            resp.sendRedirect(PATH_TO_USER_LIST);
        } else {
            req.setAttribute("error", ERROR_MESSAGE);
            doGet(req, resp);
        }
    }
}
