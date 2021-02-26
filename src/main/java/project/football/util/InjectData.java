package project.football.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import project.football.model.Role;
import project.football.model.RoleName;
import project.football.model.User;
import project.football.service.RoleService;
import project.football.service.ShoppingCartService;
import project.football.service.UserService;

@Component
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    public InjectData(UserService userService,
                      RoleService roleService,
                      ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    private void init() {
        User userAdmin = new User();
        userAdmin.setEmail("admin@epam.com");
        userAdmin.setPassword("1234");
        Role admin = new Role();
        admin.setRoleName(RoleName.ADMIN);
        Role user = new Role();
        user.setRoleName(RoleName.USER);
        roleService.add(admin);
        roleService.add(user);
        userAdmin.setRoles(Set.of(admin));
        userService.add(userAdmin);
        shoppingCartService.registerNewShoppingCart(userService.add(userAdmin));
    }
}
