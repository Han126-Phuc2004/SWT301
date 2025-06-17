package phuctv;

import java.util.logging.Level;
import java.util.logging.Logger;

final class Constants {
    public static final int MAX_USERS = 100;

    private Constants() {
        // Prevent instantiation
    }
}

public class InterfaceFieldModificationExample {
    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("The maximum number of users is: " + Constants.MAX_USERS);
        }

        // Constants.MAX_USERS = 200; // Compile-time error (vẫn không thể sửa vì là final)
    }
}