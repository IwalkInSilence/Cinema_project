package project.football.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_session")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
    @Column(name = "show_time")
    private LocalDateTime showTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getMovie() {
        return game;
    }

    public void setMovie(Game movie) {
        this.game = movie;
    }

    public Stadium getCinemaHall() {
        return stadium;
    }

    public void setCinemaHall(Stadium stadium) {
        this.stadium = stadium;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "MovieSession{"
                + "id=" + id + ", movie=" + game
                + ", cinemaHall=" + stadium
                + ", showTime=" + showTime + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameSession that = (GameSession) o;
        return Objects.equals(id, that.id)
                && Objects.equals(game, that.game)
                && Objects.equals(stadium, that.stadium)
                && Objects.equals(showTime, that.showTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game, stadium, showTime);
    }
}
