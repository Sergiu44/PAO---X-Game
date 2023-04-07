package Repository;

import Model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class UserRepository implements IRepository<User> {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User getById(UUID userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found for id: " + userId);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void deleteById(UUID userId) {
        boolean isRemoved = users.removeIf(user -> user.getUserId().equals(userId));
        if (!isRemoved) {
            throw new RuntimeException("User not found for id: " + userId);
        }
    }
}