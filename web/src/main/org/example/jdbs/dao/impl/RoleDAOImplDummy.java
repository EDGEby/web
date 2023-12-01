package main.org.example.jdbs.dao.impl;

import main.org.example.jdbs.dao.abs.RoleDAO;
import main.org.example.model.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDAOImplDummy implements RoleDAO {

    private static List<Role> roles = Arrays.asList(
            new Role(123,"Admin","Admin role",null,null),
            new Role(222,"Manager","Manager",null,null),
            new Role(333,"General_User","Default role",null,null));

    @Override
    public Role findByName(String name) {
        for (Role role: roles)
            if (role.getName().equalsIgnoreCase(name))
                return role;
        return null;
    }

    @Override
    public boolean create(Role type) {
        return false;
    }

    @Override
    public Role findById(Integer key) {
        for (Role role: roles)
            if (role.getId().equals(key))
                return role;
        return null;
    }

    @Override
    public boolean deleteById(Integer key) {
        return false;
    }

    @Override
    public boolean update(Role type) {

        return false;
    }

    @Override
    public Set<Role> all() {
        return new HashSet<>(roles);
    }








}
