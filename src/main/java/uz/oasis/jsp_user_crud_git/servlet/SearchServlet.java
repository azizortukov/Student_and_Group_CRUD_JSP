package uz.oasis.jsp_user_crud_git.servlet;

import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_user_crud_git.entity.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static uz.oasis.jsp_user_crud_git.repo.BaseRepo.em;

@WebServlet(name = "search", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("name");
        if (req.getParameter("firstname") != null) {
            TypedQuery<Student> query = em.createQuery("select s from Student s where firstName=:search or lastName =:search", Student.class);
            query.setParameter("search", name);
            List<Student> resultList = query.getResultList();
            

        } else {

            try (PrintWriter out = resp.getWriter()) {
                out.println("<h3 style='color:crimson; text-align: center'>Item Don't student. <a href='index.jsp'>Go To Menu</a></h3>");
            }

        }
    }


}
