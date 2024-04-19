package uz.oasis.jsp_user_crud_git.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_user_crud_git.entity.Student;
import uz.oasis.jsp_user_crud_git.repo.GroupRepo;
import uz.oasis.jsp_user_crud_git.repo.StudentRepo;

import java.io.IOException;

@WebServlet(name = "Student servlet", urlPatterns = "/student/*")
public class StudentServlet extends HttpServlet {

    GroupRepo groupRepo = new GroupRepo();
    StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/student/get")) {
            Integer studentId = Integer.parseInt(req.getParameter("id"));
            Student byId = studentRepo.findById(studentId);
        } else if (req.getRequestURI().equals("/student/findAll")) {
            resp.getWriter().println(studentRepo.findAll());
        }else if (req.getRequestURI().equals("/student/delete")) {
            Integer studentId = Integer.parseInt(req.getParameter("id"));
            studentRepo.deleteById(studentId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/student/post")) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String groupId = req.getParameter("groupId");
            Student student = Student.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .group(groupRepo.findById(Integer.parseInt(groupId)))
                    .build();
            studentRepo.save(student);
        }
    }
}
