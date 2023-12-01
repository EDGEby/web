package main.org.example.jdbs.dao.abs;

import main.org.example.model.Role;

public interface RoleDAO extends AbstractDAO <Role, Integer> {
    Role findByName (String name);
}
