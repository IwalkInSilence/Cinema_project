package project.cinema.dao;

import java.util.List;
import project.cinema.model.Order;
import project.cinema.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderByUser(User user);
}
