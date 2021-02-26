package project.football.service;

import project.football.model.Stadium;
import project.football.model.dto.StadiumRequestDto;
import project.football.model.dto.StadiumResponseDto;

public interface StadiumMapper {
    Stadium parseFromDto(StadiumRequestDto dto);

    StadiumResponseDto parseToDto(Stadium stadium);
}
