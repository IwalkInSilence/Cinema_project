package project.football.service.impl.mappers;

import org.springframework.stereotype.Component;
import project.football.model.Stadium;
import project.football.model.dto.StadiumRequestDto;
import project.football.model.dto.StadiumResponseDto;
import project.football.service.StadiumMapper;

@Component
public class StadiumMapperImpl implements StadiumMapper {
    @Override
    public Stadium parseFromDto(StadiumRequestDto dto) {
        Stadium stadium = new Stadium();
        stadium.setCapacity(dto.getCapacity());
        stadium.setDescription(dto.getDescription());
        return stadium;
    }

    @Override
    public StadiumResponseDto parseToDto(Stadium stadium) {
        StadiumResponseDto dto = new StadiumResponseDto();
        dto.setId(stadium.getId());
        dto.setDescription(stadium.getDescription());
        dto.setCapacity(stadium.getCapacity());
        return dto;
    }
}
