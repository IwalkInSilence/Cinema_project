package project.football.service;

import java.util.List;
import project.football.model.Order;
import project.football.model.ShoppingCart;
import project.football.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
