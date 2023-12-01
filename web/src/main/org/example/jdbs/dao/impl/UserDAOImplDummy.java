package main.org.example.jdbs.dao.impl;

import main.org.example.jdbs.dao.abs.UserDAO;
import main.org.example.model.Role;
import main.org.example.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDAOImplDummy implements UserDAO {

    private static List<User> users;
    private static RoleDAOImplDummy roleDAO = new RoleDAOImplDummy();

    static {

        RoleDAOImplDummy roleDAO = new RoleDAOImplDummy();
        users = new ArrayList<>();
        users.add(new User(1233, "John", "John@gmail.com",
                "123", "Some details", true, roleDAO.findByName("Admin"), null, null ));
        users.add(new User(2135235, "Bob", "Bob@gmail.com",
                "123", "Some details", false, roleDAO.findByName("Manager"), null, null ));
        users.add(new User(2356346, "Mike", "Mike@gmail.com",
                "123", "Some details", true, roleDAO.findByName("General_User"), null, null ));

    }

    @Override
    public User findByEmail(String email) {
        for (User user:users){
            if (user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;
    }

    @Override
    public Set<User> findByRole(Role role) {
        return null;
    }

    @Override
    public void activate(User user) {
        for (User u:users){
            if (u.getEmail().equalsIgnoreCase(user.getEmail())){
                u.setIsActive(true);
                System.out.println("User with email: " + user.getEmail()+" is Activated now! ");
            }
        }
    }

    @Override
    public boolean create(User type) {

        type.setCreatedTs(new Timestamp(System.currentTimeMillis()));
        type.setIsActive(false);
        type.setRole(roleDAO.findByName("General_User"));
        System.out.println("User with email: " + type.getEmail()+" is Created now! ");
        return users.add(type);

    }

    @Override
    public User findById(Integer key) {
        return null;
    }

    @Override
    public boolean deleteById(Integer key) {
        return false;
    }

    @Override
    public boolean update(User type) {
        return false;
    }

    @Override
    public Set<User> all() {
        return null;
    }


}
