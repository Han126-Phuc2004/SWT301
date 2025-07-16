package phuctv;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceLeakExample {
    private static final Logger logger = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        String fileName = "data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Kiểm tra cấp độ log trước khi log để tránh đánh giá chuỗi không cần thiết
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Read line: " + line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, String.format("Error reading file: %s", fileName), e);
        }
    }
}
