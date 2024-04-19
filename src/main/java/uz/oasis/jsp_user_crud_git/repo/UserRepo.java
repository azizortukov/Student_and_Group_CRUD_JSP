package uz.oasis.jsp_user_crud_git.repo;

import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import uz.oasis.jsp_user_crud_git.entity.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepo extends BaseRepo<User, UUID> {


    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        if (query.getResultList().isEmpty()) {
            return Optional.empty();
        }
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

    public Optional<User> getUserByCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("userId")) {
                UUID userId = UUID.fromString(cookie.getValue());
                return Optional.ofNullable(em.find(User.class, userId));
            }
        }
        return Optional.empty();
    }
}
