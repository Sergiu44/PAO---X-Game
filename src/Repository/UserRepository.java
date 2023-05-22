package Repository;

import Database.DatabaseConnection;
import Model.Basket;
import Model.User;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class UserRepository implements IRepository<User> {
    static Connection con = DatabaseConnection.getConnection();

    public User setResult (User user, ResultSet rs) throws SQLException {
        user.setUserId(UUID.fromString(rs.getString("userId")));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setCNP(rs.getString("CNP"));
        return user;
    }

    @Override
    public void add(User user) throws SQLException {
        String query = "insert into user(userId, " + "firstName, " + "lastName, " + "CNP) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        System.out.println(user.getUserId());
        statement.setString(1, user.getUserId().toString());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getCNP());
        statement.executeUpdate();
    }

    public User getById(String CNP) throws SQLException {
        String query = "select * from user where CNP = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, CNP);
        User user = new User();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        while(rs.next()) {
            check = true;
            user = setResult(user, rs);
        }
        if(check) {
            return user;
        }
        throw new RuntimeException("User not found for CNP: " + CNP);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * from user";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<User> users = new ArrayList<User>();

        while(rs.next()) {
            User user = new User();
            user = setResult(user, rs);
            users.add(user);
        }
        return users;
    }

    @Override
    public Basket deleteById(UUID userId) throws SQLException {
        String query = "delete from user where userId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, userId.toString());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("User not found for id: " + userId);
        }
        return null;
    }

    // unused method
    @Override
    public User getById(UUID userId) {
        return new User();
    }
}