package project.football.service;

import project.football.model.Order;
import project.football.model.dto.OrderResponseDto;

public interface OrderMapper {
    OrderResponseDto parseToDto(Order order);
}
