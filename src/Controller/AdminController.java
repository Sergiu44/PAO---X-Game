package Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Model.Admin;
import Model.User;
import Service.AdminService;

public class AdminController {

    private final Scanner scanner = new Scanner(System.in);
    private final AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }

    public Admin create() {
        System.out.println("Creating a new admin user:");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter CNP: ");
        String cnp = scanner.nextLine();
        return new Admin(UUID.randomUUID(), 0, 0, UUID.randomUUID(), firstName, lastName, cnp);
    }

    public void read(Admin admin) {
        System.out.println("Admin user information:");
        System.out.println("Admin ID: " + admin.getAdminId());
        System.out.println("First Name: " + admin.getFirstName());
        System.out.println("Last Name: " + admin.getLastName());
        System.out.println("CNP: " + admin.getCNP());
        System.out.println("Games Created: "  + admin.getGamesCreated());
        System.out.println("Games Variants Created: "  + admin.getGameVariantsCreated());
    }

    public Admin add() throws SQLException {
        Admin admin = create();
        adminService.Add(admin);
        return admin;
    }

    public User getById(String CNP) throws SQLException {
        return adminService.GetById(CNP);
    }

    public void showAll() throws SQLException {
        List<User> users = adminService.GetAll();
        for(User user : users) {
            read((Admin)user);
        }
    }

    public List<User> getAll() throws SQLException {
        return adminService.GetAll();
    }
}