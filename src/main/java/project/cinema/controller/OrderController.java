package project.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserService userService;
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;
    private OrderMapper orderMapper;

    public OrderController(
            UserService userService, OrderService orderService,
            ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long id) {
        orderService.completeOrder(shoppingCartService.getByUser(userService.getById(id).get()));
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(@RequestParam Long id) {
        List<Order> ordersHistory = orderService.getOrdersHistory(userService.getById(id).get());
        return ordersHistory
                .stream()
                .map(o -> orderMapper.parseToDto(o))
                .collect(Collectors.toList());
    }
}
