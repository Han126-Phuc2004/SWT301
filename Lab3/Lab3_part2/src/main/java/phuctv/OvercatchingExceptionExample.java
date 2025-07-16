package phuctv;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger logger = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = new int[5];

            // Gán giá trị vào mảng để tránh lỗi "never written to"
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i * 10;
            }

            int indexToAccess = 2; // Chỉ số hợp lệ

            if (logger.isLoggable(Level.INFO)) {
                logger.info("Accessing array index " + indexToAccess + "...");
            }

            int value = arr[indexToAccess]; // Truy cập an toàn trong giới hạn mảng

            if (logger.isLoggable(Level.INFO)) {
                logger.info("Array value: " + value);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, "Array index out of bounds: {0}", e.getMessage());
            }
        } catch (Exception e) {
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, "An unexpected error occurred: {0}", e.getMessage());
            }
        }

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Program completed.");
        }
    }
}
