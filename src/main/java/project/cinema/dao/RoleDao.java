package project.cinema.dao;

import java.util.Optional;
import project.cinema.model.Role;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
