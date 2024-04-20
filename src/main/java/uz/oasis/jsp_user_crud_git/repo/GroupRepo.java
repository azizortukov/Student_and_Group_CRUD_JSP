package uz.oasis.jsp_user_crud_git.repo;

import jakarta.persistence.TypedQuery;
import uz.oasis.jsp_user_crud_git.entity.Group;
import uz.oasis.jsp_user_crud_git.entity.Student;

import java.util.Optional;

public class GroupRepo extends BaseRepo<Group, Integer> {


    public String getStudentGroup(Student student) {
        Group group = em.find(Group.class, student.getGroup().getId());
        return group.getName();
    }

    public Optional<Group> findByGroups(String user) {
        TypedQuery<Group> query = em.createQuery("SELECT u FROM Group u WHERE u.name =:name", Group.class);
        query.setParameter("name", user);
        if (query.getResultList().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(query.getSingleResult());
    }

}
