package Service;

import Model.Basket;
import Repository.BasketRepository;
import Repository.GameVariantRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BasketService {

    public BasketRepository basketRepository;
    public GameVariantRepository gameVariantRepository;
    public BasketService() {
        this.basketRepository = new BasketRepository();
        this.gameVariantRepository = new GameVariantRepository();
    }

    public void Add(Basket newBasket) throws SQLException {
        basketRepository.add(newBasket);
    }

    public void AddVariant(String basketId, String gameVariantId) throws SQLException {
        basketRepository.addVariant(basketId, gameVariantId);
    }

    public Basket GetById(UUID basketId) throws SQLException {
        return basketRepository.getById(basketId);
    }

    public List<Basket> GetAll() throws SQLException {
        return basketRepository.getAll();
    }

    public void Update(String newTitle, UUID basketId) throws SQLException {
        basketRepository.update(newTitle, basketId);
    }

    public void Remove(String gameVariantId, UUID basketId) throws SQLException {
        basketRepository.remove(gameVariantId, basketId);
    }

    public Basket DeleteById(UUID basketId) throws SQLException {
        return basketRepository.deleteById(basketId);
    }
}