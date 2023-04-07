package Service;

import Model.Basket;
import Repository.BasketRepository;
import Repository.GameVariantRepository;

import java.util.List;
import java.util.UUID;

public class BasketService {

    public BasketRepository basketRepository;
    public GameVariantRepository gameVariantRepository;
    public BasketService() {
        this.basketRepository = new BasketRepository();
        this.gameVariantRepository = new GameVariantRepository();
    }

    public void Add(Basket newBasket) {
        basketRepository.add(newBasket);
    }

    public Basket GetById(UUID basketId) {
        return basketRepository.getById(basketId);
    }

    public List<Basket> GetAll() {
        return basketRepository.getAll();
    }
}