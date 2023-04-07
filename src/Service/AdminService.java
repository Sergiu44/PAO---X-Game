package Service;

import Model.User;
import Repository.AdminRepository;

import java.util.List;
import java.util.UUID;

public class AdminService {
    public AdminRepository adminRepository;
    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public void Add(User newUser) {
        adminRepository.add(newUser);
    }

    public User GetById(UUID userId) {
        return adminRepository.getById(userId);
    }

    public List<User> GetAll() {
        return adminRepository.getAll();
    }
}
