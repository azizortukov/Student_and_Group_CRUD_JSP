package uz.oasis.jsp_user_crud_git.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import uz.oasis.jsp_user_crud_git.entity.User;
import uz.oasis.jsp_user_crud_git.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Auth servlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {

    UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String parameter = req.getParameter("remember_me");
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (userOptional != null && userOptional.isPresent()) {
            String hpw = userOptional.get().getPassword();
            if (BCrypt.checkpw(password, hpw)) {
                req.getSession().setAttribute("currentUser", userOptional.get());
                if (parameter != null) {
                    setCookieToUser(userOptional.get(), resp);
                }
                resp.sendRedirect("/admin/student.jsp");
                return;
            }
        }
        Optional<User> userByCookie = userRepo.getUserByCookie(req.getCookies());
        if (userByCookie.isPresent()) {
            req.getSession().setAttribute("currentUser", userByCookie.get());
            resp.sendRedirect("/admin/student.jsp");
            return;
        }
        resp.sendRedirect("/login.jsp?multiple=true");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("currentUser");
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("userId")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setSecure(false);
                resp.addCookie(cookie);
                resp.sendRedirect("/");
                return;
            }
        }
    }

    private void setCookieToUser(User user, HttpServletResponse resp) {
        Cookie cookie = new Cookie("userId", user.getId().toString());
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
    }

}
