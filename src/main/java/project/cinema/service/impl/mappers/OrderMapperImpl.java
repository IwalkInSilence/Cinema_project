package project.cinema.service.impl.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import project.cinema.model.Order;
import project.cinema.model.Ticket;
import project.cinema.model.dto.OrderResponseDto;
import project.cinema.service.OrderMapper;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderResponseDto parseToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setUserId(order.getId());
        dto.setTicketsIdList(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
