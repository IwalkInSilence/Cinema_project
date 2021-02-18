package project.cinema.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.cinema.model.ShoppingCart;
import project.cinema.model.dto.ShoppingCartResponseDto;
import project.cinema.service.MovieSessionService;
import project.cinema.service.ShoppingCartMapper;
import project.cinema.service.ShoppingCartService;
import project.cinema.service.UserService;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(
            ShoppingCartService shoppingCartService, MovieSessionService movieSessionService,
            UserService userService, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication auth, @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(
                 movieSessionService.getById(movieSessionId),
                userService.findByEmail(auth.getName()).get());
    }

    @GetMapping
    public ShoppingCartResponseDto getByUser(Authentication auth) {
        ShoppingCart shoppingCart = shoppingCartService
                .getByUser(userService.findByEmail(auth.getName()).get());
        return shoppingCartMapper.parseToDto(shoppingCart);
    }
}
