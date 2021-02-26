package project.football.model.dto;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Min;

public class StadiumRequestDto {
    @NotNull
    private String description;
    @Min(1)
    private int capacity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
