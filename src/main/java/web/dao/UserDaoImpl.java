package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user, long id) {
        User updatedUser = getUserById(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        return em.merge(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
