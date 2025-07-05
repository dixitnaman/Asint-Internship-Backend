import java.sql.*;

public class UserService {

    public static void registerUser(String name, String email, String password, String role) {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);

            stmt.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public static User login(String email, String password) {
        String query = "SELECT id, name, email, role FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                return new User(id, name, email, role);
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }

        return null; // login failed
    }

}
