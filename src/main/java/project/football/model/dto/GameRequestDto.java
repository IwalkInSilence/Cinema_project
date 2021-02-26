package project.football.model.dto;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Size;

public class GameRequestDto {
    @NotNull
    private String title;
    @Size(max = 1500)
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
