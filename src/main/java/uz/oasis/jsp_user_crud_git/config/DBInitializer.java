package uz.oasis.jsp_user_crud_git.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;
import uz.oasis.jsp_user_crud_git.entity.Group;
import uz.oasis.jsp_user_crud_git.entity.Student;
import uz.oasis.jsp_user_crud_git.entity.User;
import uz.oasis.jsp_user_crud_git.repo.GroupRepo;
import uz.oasis.jsp_user_crud_git.repo.StudentRepo;
import uz.oasis.jsp_user_crud_git.repo.UserRepo;

@WebListener
public class DBInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initData();
        ServletContextListener.super.contextInitialized(sce);
    }

    private void initData() {

        UserRepo userRepo = new UserRepo();
        StudentRepo studentRepo = new StudentRepo();
        GroupRepo groupRepo = new GroupRepo();
        if (groupRepo.findAll().isEmpty()) {
            groupRepo.save(Group.builder().name("G35").build());
            groupRepo.save(Group.builder().name("G36").build());
            groupRepo.save(Group.builder().name("G37").build());
        }else {
            System.out.println("Group table bush emas, tormoz");
        }
        if (studentRepo.findAll().isEmpty()) {
            studentRepo.save(Student.builder().group(groupRepo.findById(1)).firstName("Eshmat").lastName("Eshmatov").build());
            studentRepo.save(Student.builder().group(groupRepo.findById(2)).firstName("Toshmat").lastName("Toshmatov").build());
            studentRepo.save(Student.builder().group(groupRepo.findById(3)).firstName("Hikmat").lastName("Hikmatov").build());
            studentRepo.save(Student.builder().group(groupRepo.findById(1)).firstName("Qudrat").lastName("Qudratov").build());
            studentRepo.save(Student.builder().group(groupRepo.findById(3)).firstName("Teshaboy").lastName("Teshaboyev").build());
        }else {
            System.out.println("Student table bush emas, tormoz");
        }
        if (userRepo.findAll().isEmpty()) {
            String password = "123";
            String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
            userRepo.save(User.builder().username("eshmat").password(hashpw).build());
        }else {
            System.out.println("User table bush emas, tormoz");
        }
    }
}
