package phuctv;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger logger = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { // Sử dụng try-with-resources
            logger.info("Please enter a string:");
            String s = scanner.nextLine();

            if (s != null && !s.isEmpty()) { // Điều kiện hợp lệ
                if (logger.isLoggable(Level.INFO)) {
                    logger.log(Level.INFO, "String length: {0}", s.length());
                }
            } else {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.warning("String is null or empty, cannot retrieve length.");
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