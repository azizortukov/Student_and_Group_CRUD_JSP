//package uz.oasis.jsp_user_crud_git.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.SneakyThrows;
//import uz.oasis.jsp_user_crud_git.entity.Group;
//import uz.oasis.jsp_user_crud_git.repo.GroupRepo;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
//@WebServlet(name = "Group servlet", urlPatterns = "/group/*")
//public class GroupServlet extends HttpServlet {
//
//    GroupRepo groupRepo = new GroupRepo();
//
//    @SneakyThrows
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String requestURI = req.getRequestURI();
//        if (requestURI.endsWith("/group/delete")) {
//            Integer groupId = Integer.parseInt(req.getParameter("id"));
//            groupRepo.deleteById(groupId);
//            resp.sendRedirect("/admin/group.jsp");
//        } else if (requestURI.endsWith("/group/get")) {
//            Integer groupId = Integer.parseInt(req.getParameter("id"));
//            Group group = groupRepo.findById(groupId);
//            resp.setContentType("text/html");
//            PrintWriter out = resp.getWriter();
//            out.println("<html><body>");
//            out.println("<h1>Group Details</h1>");
//            out.println("<p>ID: " + group.getId() + "</p>");
//            out.println("<p>Name: " + group.getName() + "</p>");
//            out.println("</body></html>");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String requestURI = req.getRequestURI();
//        if (requestURI.endsWith("/group/post")) {
//            String name = req.getParameter("name");
//            Group group = new Group();
//            groupRepo.save(group);
//        } else if (requestURI.endsWith("/group/put")) {
//            Integer groupId = Integer.parseInt(req.getParameter("id"));
//            Group group = groupRepo.findById(groupId);
//            String name = req.getParameter("name");
//            group.setName(name);
//            groupRepo.commit();
//        }
//        resp.sendRedirect("/admin/group.jsp");
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer groupId = Integer.parseInt(req.getParameter("id"));
//        groupRepo.deleteById(groupId);
//        resp.sendRedirect("/admin/group.jsp");
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer groupId = Integer.parseInt(req.getParameter("id"));
//        Group group = groupRepo.findById(groupId);
//        String name = req.getParameter("name");
//        group.setName(name);
//        groupRepo.commit();
//        resp.sendRedirect("/admin/group.jsp");
//    }
//}
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

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Group servlet", urlPatterns = "/group/*")
public class GroupServlet extends HttpServlet {

    private final GroupRepo groupRepo = new GroupRepo();
    private final StudentRepo studentRepo = new StudentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/group/delete")) {
            Integer groupId = Integer.parseInt(req.getParameter("id"));
            Group group = groupRepo.findById(groupId);
            List<Student> students = studentRepo.findAll();
            for (Student student : students) {
                if (student.getGroup().getId().equals(group.getId())) {
                    studentRepo.delete(student);
                }
            }
            groupRepo.deleteById(groupId);
            resp.sendRedirect("/admin/group.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/group/put")) {
            String groupName = req.getParameter("name");
            Integer groupId = Integer.parseInt(req.getParameter("id"));
            Group group = groupRepo.findById(groupId);
            groupRepo.begin();
            group.setName(groupName);
            groupRepo.commit();
        }
        resp.sendRedirect("/admin/group.jsp");
    }
}
