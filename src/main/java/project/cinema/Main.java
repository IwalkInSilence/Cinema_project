package project.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.cinema.config.AppConfig;
import project.cinema.exception.AuthenticationException;
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
    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MovieService movieService = context.getBean(MovieService.class);
        movieService.add(movie);
        Movie sw = new Movie();
        sw.setTitle("Star Wars");
        sw.setDescription("Amazing Saga");
        movieService.add(sw);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                context.getBean(CinemaHallService.class);
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
                context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        LocalDate dateToFind = LocalDate.of(2021, 2, 2);
        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(sw.getId(), dateToFind);
        availableSessions.forEach(System.out::println);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);

        authenticationService.register("bobos@gmail.com", "1123");
        User bob = authenticationService.login("bobos@gmail.com", "1123");
        System.out.println(bob);

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, bob);
        shoppingCartService.addSession(movieSession, bob);
        ShoppingCart bobShopCard = shoppingCartService.getByUser(bob);
        System.out.println("Bobs shopping card: " + bobShopCard);
        OrderService orderService = context.getBean(OrderService.class);
        Order order = orderService.completeOrder(shoppingCartService.getByUser(bob));
        System.out.println(order);
        List<Order> ordersHistory = orderService.getOrdersHistory(bob);
        ordersHistory.forEach(System.out::println);
    }
}
