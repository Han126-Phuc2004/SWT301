package phuctv;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger logger = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { // Sử dụng try-with-resources để quản lý Scanner
            logger.info("Please enter some text:");
            String text = scanner.nextLine(); // Lấy dữ liệu từ người dùng, đảm bảo có thể không null

            if (text != null && !text.isEmpty()) { // Kiểm tra null và rỗng đúng cách
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Text is not empty.");
                }
            } else {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("Text is null or empty.");
                }
            }

        } catch (Exception e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, "An unexpected error occurred: {0}", e.getMessage());
            }
        }

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Program completed successfully.");
        }
    }
}
