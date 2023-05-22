package Repository;

import Database.DatabaseConnection;
import Model.Admin;
import Model.Basket;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminRepository extends UserRepository {
    static Connection con = DatabaseConnection.getConnection();

    public Admin setResult (Admin admin, ResultSet rs) throws SQLException {
        super.setResult(admin, rs);
        admin.setAdminId(UUID.fromString(rs.getString("adminId")));
        admin.setGamesCreated(rs.getInt("gamesCreated"));
        admin.setGameVariantsCreated(rs.getInt("gameVariantsCreated"));
        return admin;
    }

    @Override
    public void add(User user) throws SQLException {
        if(!(user instanceof Admin)) {
            throw new IllegalArgumentException("[REPOSITORY-ADMIN]: Only admins can be added in here");
        }
        super.add(user);
        String query = "insert into admin(adminId, " + "userId, " + "gamesCreated, " + "gameVariantsCreated) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, ((Admin) user).getAdminId().toString());
        statement.setString(2, user.getUserId().toString());
        statement.setInt(3, ((Admin) user).getGamesCreated());
        statement.setInt(4, ((Admin) user).getGameVariantsCreated());
        statement.executeUpdate();
    }

    public User getByCNP(String CNP) throws SQLException {
        String query = "select * from admin left join user USING (userId) where CNP = ?";
        PreparedStatement statement = con.prepareStatement(query);

        statement.setString(1, CNP);
        Admin admin = new Admin();
        ResultSet rs = statement.executeQuery();
        boolean check = false;

        if(rs.next()) {
            check = true;
            admin = setResult(admin, rs);
        }
        if(check) {
            return admin;
        }
        return new Admin();
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * from admin left join user USING (userId)";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        List<User> admins = new ArrayList<>();

        while(rs.next()) {
            Admin admin = new Admin();
            admin = setResult(admin, rs);
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public Basket deleteById(UUID adminId) throws SQLException {
        String query = "delete from admin where adminId = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, adminId.toString());
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[REPOSITORY-ADMIN]: Error deleting admin: " + e.getMessage());
        }
        return null;
    }
}