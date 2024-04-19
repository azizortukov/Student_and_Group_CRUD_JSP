package uz.oasis.jsp_user_crud_git.repo;

import uz.oasis.jsp_user_crud_git.entity.Group;
import uz.oasis.jsp_user_crud_git.entity.Student;

public class GroupRepo extends BaseRepo<Group, Integer> {


    public String getStudentGroup(Student student) {
        Group group = em.find(Group.class, student.getGroup().getId());
        return group.getName();
    }
}
