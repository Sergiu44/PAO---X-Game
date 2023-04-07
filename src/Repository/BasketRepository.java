package Repository;

import Model.Basket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketRepository implements IRepository<Basket> {
    List<Basket> baskets = new ArrayList<>();

    @Override
    public void add(Basket basket) {
        baskets.add(basket);
    }

    @Override
    public Basket getById(UUID basketId) {
        for (Basket basket : baskets) {
            if (basket.getBasketId().equals(basketId)) {
                return basket;
            }
        }
        throw new RuntimeException("Basket not found");
    }

    @Override
    public List<Basket> getAll() {
        return baskets;
    }

    @Override
    public void deleteById(UUID basketId) {
        boolean removed = baskets.removeIf(basket -> basket.getBasketId().equals(basketId));
        if (!removed) {
            throw new RuntimeException("Basket not found");
        }
    }
}