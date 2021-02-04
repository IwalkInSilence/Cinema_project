package project.cinema.dao;

import project.cinema.model.ShoppingCart;
import project.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
