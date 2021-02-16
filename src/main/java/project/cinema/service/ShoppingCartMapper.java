package project.cinema.service;

import project.cinema.model.ShoppingCart;
import project.cinema.model.dto.ShoppingCartResponseDto;

public interface ShoppingCartMapper {
    ShoppingCartResponseDto parseToDto(ShoppingCart shoppingCart);
}
