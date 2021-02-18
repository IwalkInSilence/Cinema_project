package project.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.cinema.model.Order;
import project.cinema.model.dto.OrderResponseDto;
import project.cinema.service.OrderMapper;
import project.cinema.service.OrderService;
import project.cinema.service.ShoppingCartService;
import project.cinema.service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(
            UserService userService, OrderService orderService,
            ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication auth) {
        String name = auth.getName();
        orderService.completeOrder(
                shoppingCartService.getByUser(userService.findByEmail(name).get()));
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(Authentication auth) {
        String name = auth.getName();
        List<Order> ordersHistory = orderService
                .getOrdersHistory(userService.findByEmail(name).get());
        return ordersHistory
                .stream()
                .map(orderMapper::parseToDto)
                .collect(Collectors.toList());
    }
}
