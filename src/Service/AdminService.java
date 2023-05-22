package Service;

import Model.Admin;
import Model.User;
import Repository.AdminRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AdminService {
    public AdminRepository adminRepository;
    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public void Add(Admin newAdmin) throws SQLException {
        adminRepository.add(newAdmin);
    }

    public User GetById(String CNP) throws SQLException {
        return adminRepository.getByCNP(CNP);
    }

    public List<User> GetAll() throws SQLException {
        return adminRepository.getAll();
    }
}
