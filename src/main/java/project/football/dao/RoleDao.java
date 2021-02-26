package project.football.dao;

import java.util.Optional;
import project.football.model.Role;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
