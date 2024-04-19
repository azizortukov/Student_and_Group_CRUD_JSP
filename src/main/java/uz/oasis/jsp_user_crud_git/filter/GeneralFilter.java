package uz.oasis.jsp_user_crud_git.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_user_crud_git.entity.User;
import uz.oasis.jsp_user_crud_git.repo.UserRepo;

import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "login filter", urlPatterns = "/*")
public class GeneralFilter implements Filter {

    UserRepo userRepo = new UserRepo();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<User> userByCookie = userRepo.getUserByCookie(request.getCookies());
        userByCookie.ifPresent(user -> request.getSession().setAttribute("currentUser", user));
        filterChain.doFilter(request, response);
    }
}
