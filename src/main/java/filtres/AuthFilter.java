package filtres;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    private static final String SING_IN_URI = "/sing-in";
    private static final String SING_UP_URI = "/sing-up";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User currentUser = (User) req.getSession().getAttribute("user");
        String currentRequestUri = req.getRequestURI();
        if (currentUser != null || (currentRequestUri.contains(SING_IN_URI)
                || currentRequestUri.contains(SING_UP_URI))) {
            chain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher(SING_IN_URI).forward(req, resp);
        }
    }
}
