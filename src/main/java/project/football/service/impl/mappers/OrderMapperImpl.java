package project.football.service.impl.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import project.football.model.Order;
import project.football.model.Ticket;
import project.football.model.dto.OrderResponseDto;
import project.football.service.OrderMapper;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderResponseDto parseToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setUserId(order.getUser().getId());
        dto.setTicketsIdList(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
