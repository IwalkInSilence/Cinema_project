package project.cinema.security;

import java.util.Optional;
import project.cinema.exception.AuthenticationException;
import project.cinema.lib.Inject;
import project.cinema.lib.Service;
import project.cinema.model.User;
import project.cinema.service.ShoppingCartService;
import project.cinema.service.UserService;
import project.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent()) {
            byte[] salt = userFromDB.get().getSalt();
            String hashPassword = HashUtil.hashPassword(password, salt);
            if (hashPassword.equals(userFromDB.get().getPassword())) {
                return userFromDB.get();
            }
        }
        throw new AuthenticationException("Incorrect password, try again");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
