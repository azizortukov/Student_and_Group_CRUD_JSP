package uz.oasis.jsp_user_crud_git.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "Admin page filter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object object = request.getSession().getAttribute("currentUser");
        if (object != null) {
            filterChain.doFilter(request, response);
        }else {
            response.sendRedirect("/login.jsp");
        }
    }
}
