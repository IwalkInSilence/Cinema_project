package project.football.service;

import project.football.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
