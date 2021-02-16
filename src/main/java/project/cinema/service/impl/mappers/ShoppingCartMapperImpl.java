package project.cinema.service.impl.mappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import project.cinema.model.ShoppingCart;
import project.cinema.model.dto.ShoppingCartResponseDto;
import project.cinema.service.ShoppingCartMapper;

@Controller
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

    @Override
    public ShoppingCartResponseDto parseToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTicketsIdList(shoppingCart.getTickets()
                 .stream()
                 .map(t -> t.getId())
                 .collect(Collectors.toList()));
        return dto;
    }
}
