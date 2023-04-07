package Controller;

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
        Admin admin = new Admin();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setCNP(cnp);
        admin.setUserId(UUID.randomUUID());
        admin.setDeleted(false);
        // Save the admin user to the database or other storage mechanism
        return admin;
    }

    public void read(Admin admin) {
        System.out.println("Admin user information:");
        // Retrieve the admin user from the database or other storage mechanism
        System.out.println("First Name: " + admin.getFirstName());
        System.out.println("Last Name: " + admin.getLastName());
        System.out.println("CNP: " + admin.getCNP());
        System.out.println("User ID: " + admin.getUserId());
        System.out.println("Is Deleted: " + admin.getDeleted());
    }

    public Admin addDefault() {
        Admin admin = new Admin();
        adminService.Add(admin);
        return admin;
    }

    public Admin add() {
        Admin admin = create();
        adminService.Add(admin);
        return admin;
    }

    public void getById(UUID uuid) {
        User user = adminService.GetById(uuid);
        read((Admin)user);
    }

    public void showAll() {
        List<User> users = adminService.GetAll();
        for(User user : users) {
            read((Admin)user);
        }
    }

    public List<User> getAll(){
        return adminService.GetAll();
    }
}