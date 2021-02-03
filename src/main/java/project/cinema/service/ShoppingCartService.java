package project.cinema.service;

import project.cinema.model.MovieSession;
import project.cinema.model.ShoppingCart;
import project.cinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
