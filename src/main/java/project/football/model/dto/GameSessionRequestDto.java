package project.football.model.dto;

import com.sun.istack.NotNull;

public class GameSessionRequestDto {
    @NotNull
    private Long gameId;
    @NotNull
    private Long stadiumId;
    @NotNull
    private String localTime;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }
}
