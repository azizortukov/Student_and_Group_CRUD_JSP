package uz.oasis.jsp_user_crud_git.repo;

import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpSession;
import uz.oasis.jsp_user_crud_git.entity.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepo extends BaseRepo<User, UUID> {


    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        System.out.println("1");
        if (query.getResultList().isEmpty()) {
            System.out.println("2");
            return Optional.empty();
        }
        System.out.println("3");
        return Optional.ofNullable(query.getSingleResult());
    }

    public Optional<User> getUserBySession(HttpSession session) {
        Object object = session.getAttribute("currentUser");
        if (object == null) {
            return Optional.empty();
        } else {
            return Optional.of((User) object);
        }
    }
}
