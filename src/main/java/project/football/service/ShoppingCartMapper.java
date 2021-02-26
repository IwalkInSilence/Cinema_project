package project.football.service;

import project.football.model.ShoppingCart;
import project.football.model.dto.ShoppingCartResponseDto;

public interface ShoppingCartMapper {
    ShoppingCartResponseDto parseToDto(ShoppingCart shoppingCart);
}
