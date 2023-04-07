package Repository;

import Model.Admin;
import Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminRepository extends UserRepository {
    private final List<Admin> admins = new ArrayList<>();

    @Override
    public void add(User user) {
        if(!(user instanceof Admin)) {
            throw new IllegalArgumentException("[REPOSITORY-ADMIN]: Only admins can be added in here");
        }
        super.add(user);
        admins.add((Admin) user);
    }

    @Override
    public Admin getById(UUID adminId) {
        for (Admin admin : admins) {
            if (admin.getUserId().equals(adminId)) {
                return admin;
            }
        }
        throw new RuntimeException("[REPOSITORY-ADMIN]: Admin not found for id: " + adminId);
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = super.getAll();
        List<User> adminsOnly = new ArrayList<>();
        for (User user : allUsers) {
            if (user instanceof Admin) {
                adminsOnly.add(user);
            }
        }
        return adminsOnly;
    }

    @Override
    public void deleteById(UUID adminId) {
        try {
            super.deleteById(adminId);
            admins.removeIf(admin -> admin.getUserId().equals(adminId));
        } catch (RuntimeException e) {
            throw new RuntimeException("[REPOSITORY-ADMIN]: Error deleting admin: " + e.getMessage());
        }
    }
}