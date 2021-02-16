package project.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.cinema.model.ShoppingCart;
import project.cinema.service.MovieSessionService;
import project.cinema.service.ShoppingCartService;
import project.cinema.service.UserService;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private MovieSessionService movieSessionService;
    private UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
            MovieSessionService movieSessionService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long userId, @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(
                 movieSessionService.getById(movieSessionId), userService.getById(userId).get());
    }

    @GetMapping
    public ShoppingCart getByUser(@RequestParam Long id) {
        return shoppingCartService.getByUser(userService.getById(id).get());
    }
}
