package uz.oasis.jsp_user_crud_git.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseRepo<T, I> {

    public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PROJECT_1");
    public static final EntityManager em = entityManagerFactory.createEntityManager();

    private final Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
    public BaseRepo() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.persistenceClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        } else {
            throw new IllegalArgumentException("Class type not found.");
        }
    }

    public void begin() {
        em.getTransaction().begin();
    }

    public void commit() {
        em.getTransaction().commit();
    }

    public void save(T t) {
        begin();
        em.persist(t);
        commit();
    }

    public void deleteById(I id) {
        begin();
        T entity = findById(id);
        if (entity != null) {
            em.remove(entity);
        }
        commit();
    }

    public T findById(I id) {
        return em.find(persistenceClass, id);
    }

    public List<T> findAll() {
        return em.createQuery("from " + persistenceClass.getSimpleName(), persistenceClass).getResultList();
    }

    public void delete(T t) {
        begin();
        em.remove(t);
        commit();
    }
}
