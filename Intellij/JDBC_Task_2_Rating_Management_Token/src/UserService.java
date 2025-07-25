import java.sql.*;

public class UserService {

    public static void registerUser(String name, String email, String password, String role) {
        //? are placeholders later replaced from statement in line 12 - 15
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
                User user = new User(id, name, email, role);

                // Token generate kar rahe hain
                String token = java.util.UUID.randomUUID().toString();

                // Token ko user ke object me set karna
                user.setToken(token);

                return user;
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }

        return null; // login failed
    }

}
