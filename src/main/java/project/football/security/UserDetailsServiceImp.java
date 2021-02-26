package project.football.security;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.football.model.User;
import project.football.service.UserService;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Can't find User by email : %s", email)));
        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        String[] userRoles = user.getRoles()
                .stream()
                .map(r -> r.getRoleName().toString())
                .toArray(String[]::new);
        builder.roles(userRoles);
        return builder.build();
    }
}
