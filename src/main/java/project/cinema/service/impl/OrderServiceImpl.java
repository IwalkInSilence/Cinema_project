package project.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import project.cinema.dao.OrderDao;
import project.cinema.lib.Inject;
import project.cinema.lib.Service;
import project.cinema.model.Order;
import project.cinema.model.ShoppingCart;
import project.cinema.model.User;
import project.cinema.service.OrderService;
import project.cinema.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrderByUser(user);
    }
}
