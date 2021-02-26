package project.football.service.impl.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import project.football.model.ShoppingCart;
import project.football.model.Ticket;
import project.football.model.dto.ShoppingCartResponseDto;
import project.football.service.ShoppingCartMapper;

@Controller
public class ShoppingCartMapperImpl implements ShoppingCartMapper {
    @Override
    public ShoppingCartResponseDto parseToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTicketsIdList(shoppingCart.getTickets()
                 .stream()
                 .map(Ticket::getId)
                 .collect(Collectors.toList()));
        return dto;
    }
}
