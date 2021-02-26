package project.football.service.impl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import project.football.dao.ShoppingCartDao;
import project.football.dao.TicketDao;
import project.football.model.GameSession;
import project.football.model.ShoppingCart;
import project.football.model.Ticket;
import project.football.model.User;
import project.football.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(
            ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(GameSession session, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(session);
        ShoppingCart userShoppingCart = shoppingCartDao.getByUser(user);
        userShoppingCart.getTickets().add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(userShoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
