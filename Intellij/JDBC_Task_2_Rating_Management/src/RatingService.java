import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RatingService {

    public static void addRating(int userId, int ambiance, int food, int service, int cleanliness, int drinks) {
        String query = "INSERT INTO ratings (user_id, ambiance, food, service, cleanliness, drinks) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, ambiance);
            stmt.setInt(3, food);
            stmt.setInt(4, service);
            stmt.setInt(5, cleanliness);
            stmt.setInt(6, drinks);

            stmt.executeUpdate();
            System.out.println("Rating added successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to add rating: " + e.getMessage());
        }
    }
}
