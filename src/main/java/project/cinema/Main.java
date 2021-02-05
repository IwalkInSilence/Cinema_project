package project.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import project.cinema.exception.AuthenticationException;
import project.cinema.lib.Injector;
import project.cinema.model.CinemaHall;
import project.cinema.model.Movie;
import project.cinema.model.MovieSession;
import project.cinema.model.Order;
import project.cinema.model.ShoppingCart;
import project.cinema.model.User;
import project.cinema.security.AuthenticationService;
import project.cinema.service.CinemaHallService;
import project.cinema.service.MovieService;
import project.cinema.service.MovieSessionService;
import project.cinema.service.OrderService;
import project.cinema.service.ShoppingCartService;

public class Main {
    private static Injector injector = Injector.getInstance("project.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        Movie sw = new Movie();
        sw.setTitle("Star Wars");
        sw.setDescription("Amazing Saga");
        movieService.add(sw);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        cinemaHall.setDescription("Large IMAX hall");
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());

        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.of(2021, 2, 2, 15, 30));
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(sw);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        LocalDate dateToFind = LocalDate.of(2021, 2, 2);
        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(sw.getId(), dateToFind);
        availableSessions.forEach(System.out::println);

        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        authenticationService.register("bobos@gmail.com", "1123");
        User bob = authenticationService.login("bobos@gmail.com", "1123");
        System.out.println(bob);

        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, bob);
        shoppingCartService.addSession(movieSession, bob);
        ShoppingCart bobShopCard = shoppingCartService.getByUser(bob);
        System.out.println("Bobs shopping card: " + bobShopCard);
        OrderService orderService = (OrderService)
                injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCartService.getByUser(bob));
        System.out.println(order);
        List<Order> ordersHistory = orderService.getOrdersHistory(bob);
        ordersHistory.forEach(System.out::println);
    }
}
