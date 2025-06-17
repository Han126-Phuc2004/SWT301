package phuctv;

import java.util.logging.Logger;

public class HardcodedCredentialsExample {
    private static final Logger logger = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        // Đọc username và password từ biến môi trường
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");

        if (authenticate(username, password)) {
            logger.info("Access granted");
        } else {
            logger.warning("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Lấy thông tin đăng nhập hợp lệ từ biến môi trường
        String validUser = System.getenv("APP_USERNAME");
        String validPass = System.getenv("APP_PASSWORD");

        return user != null && pass != null && user.equals(validUser) && pass.equals(validPass);
    }
}