package project.cinema.service;

import java.util.List;
import project.cinema.model.Order;
import project.cinema.model.ShoppingCart;
import project.cinema.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
