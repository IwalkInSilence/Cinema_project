package project.cinema;

import project.cinema.lib.Injector;
import project.cinema.model.Movie;
import project.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("project.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        Movie sw = new Movie();
        sw.setTitle("Star Wars");
        sw.setDescription("Amazing Saga");
        movieService.add(sw);
        movieService.getAll().forEach(System.out::println);
    }
}
