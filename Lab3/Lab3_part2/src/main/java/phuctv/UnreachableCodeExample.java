package phuctv;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger logger = Logger.getLogger(UnreachableCodeExample.class.getName());

    // Định nghĩa hằng số trực tiếp thay vì tạo method trả về hằng số
    public static final int NUMBER = 42;

    public static void main(String[] args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("The number is: %d", NUMBER));
        }
    }
}