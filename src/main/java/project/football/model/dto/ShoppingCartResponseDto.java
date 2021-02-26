package project.football.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private List<Long> ticketsIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getTicketsIdList() {
        return ticketsIdList;
    }

    public void setTicketsIdList(List<Long> ticketsIdList) {
        this.ticketsIdList = ticketsIdList;
    }
}
