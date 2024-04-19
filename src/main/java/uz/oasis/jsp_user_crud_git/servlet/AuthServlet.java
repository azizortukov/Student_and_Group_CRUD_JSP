package uz.oasis.jsp_user_crud_git.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
        Optional<User> userOptional = userRepo.findByUsername(username);
        System.out.println("djklsf");
        if (userOptional != null && userOptional.isPresent()) {
            String hpw = userOptional.get().getPassword();
            System.out.println("whassup");
            if (BCrypt.checkpw(password, hpw)) {
                req.getSession().setAttribute("currentUser", userOptional.get());
                resp.sendRedirect("/admin/student.jsp");
                System.out.println("balo battar");
                return;
            }
        }
        System.out.println("wtf");
        resp.sendRedirect("/login.jsp?multiple=true");
    }
}
