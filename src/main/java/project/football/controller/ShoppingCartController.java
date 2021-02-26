package project.football.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.football.model.ShoppingCart;
import project.football.model.dto.ShoppingCartResponseDto;
import project.football.service.GameSessionService;
import project.football.service.ShoppingCartMapper;
import project.football.service.ShoppingCartService;
import project.football.service.UserService;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final GameSessionService gameSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(
            ShoppingCartService shoppingCartService, GameSessionService gameSessionService,
            UserService userService, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.gameSessionService = gameSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication auth, @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(
                 gameSessionService.getById(movieSessionId),
                userService.findByEmail(auth.getName()).get());
    }

    @GetMapping
    public ShoppingCartResponseDto getByUser(Authentication auth) {
        ShoppingCart shoppingCart = shoppingCartService
                .getByUser(userService.findByEmail(auth.getName()).get());
        return shoppingCartMapper.parseToDto(shoppingCart);
    }
}
