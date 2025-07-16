package phuctv;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInjectionExample {
    private static final Logger logger = Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";

        // Truy vấn chỉ lấy cột cần thiết
        String sql = "SELECT username FROM users WHERE username = ?";

        // Kiểm tra cấp độ log trước khi log để tránh đánh giá sớm argument
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Preparing to execute query: " + sql + " with parameter: " + userInput);
        }

        try {
            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, userInput);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info("User found: " + rs.getString("username"));
                    }
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error", e);
        }
    }
}