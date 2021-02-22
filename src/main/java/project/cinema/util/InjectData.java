package project.cinema.util;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import project.cinema.model.Role;
import project.cinema.model.Roles;
import project.cinema.model.User;
import project.cinema.service.RoleService;
import project.cinema.service.ShoppingCartService;
import project.cinema.service.UserService;

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
        admin.setRole(Roles.ADMIN);
        Role user = new Role();
        user.setRole(Roles.USER);
        roleService.add(admin);
        roleService.add(user);
        userAdmin.setRoles(List.of(admin));
        shoppingCartService.registerNewShoppingCart(userService.add(userAdmin));
    }
}
