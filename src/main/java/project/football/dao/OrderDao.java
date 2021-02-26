package project.football.dao;

import java.util.List;
import project.football.model.Order;
import project.football.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderByUser(User user);
}
