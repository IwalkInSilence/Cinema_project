package project.cinema.service;

import project.cinema.model.Order;
import project.cinema.model.dto.OrderResponseDto;

public interface OrderMapper {
    OrderResponseDto parseToDto(Order order);
}
