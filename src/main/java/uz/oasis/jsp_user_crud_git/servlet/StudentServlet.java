package uz.oasis.jsp_user_crud_git.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_user_crud_git.entity.Group;
import uz.oasis.jsp_user_crud_git.entity.Student;
import uz.oasis.jsp_user_crud_git.repo.GroupRepo;
import uz.oasis.jsp_user_crud_git.repo.StudentRepo;
import uz.oasis.jsp_user_crud_git.repo.UserRepo;

import java.io.IOException;

@WebServlet(name = "Student servlet", urlPatterns = "/student/*")
public class StudentServlet extends HttpServlet {

    GroupRepo groupRepo = new GroupRepo();
    StudentRepo studentRepo = new StudentRepo();
    UserRepo userRepo = new UserRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if (req.getRequestURI().equals("/student/delete")) {
            Integer studentId = Integer.parseInt(req.getParameter("id"));
            studentRepo.deleteById(studentId);
            resp.sendRedirect("/admin/student.jsp");
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
        }else if (req.getRequestURI().equals("/student/put")) {
            studentRepo.begin();
            Integer studentId = Integer.parseInt(req.getParameter("id"));
            Student student = studentRepo.findById(studentId);
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            Integer groupId = Integer.parseInt(req.getParameter("groupId"));
            Group studentGroup = groupRepo.findById(groupId);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setGroup(studentGroup);
            studentRepo.commit();
        }
        resp.sendRedirect("/admin/student.jsp");
    }
}
