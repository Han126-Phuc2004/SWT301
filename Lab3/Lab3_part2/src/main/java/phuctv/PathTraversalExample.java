package phuctv;

import java.io.*;
import java.util.logging.Logger;

public class PathTraversalExample {
    private static final Logger logger = Logger.getLogger(PathTraversalExample.class.getName());
    private static final String BASE_DIRECTORY = "D:/safe/files/"; // Đường dẫn thư mục an toàn tuyệt đối

    public static void main(String[] args) throws IOException {
        // Lấy tập dữ liệu đầu vào của người dùng (có thể thay thế bằng scanner hoặc GUI thực tế)
        String userInput = "example.txt";

        // Xây dựng đường dẫn tuyệt đối an toàn
        File baseDir = new File(BASE_DIRECTORY).getCanonicalFile();
        File targetFile = new File(baseDir, userInput).getCanonicalFile();

        // Kiểm tra file có nằm trong thư mục an toàn hay không
        if (!targetFile.getPath().startsWith(baseDir.getPath())) {
            logger.warning("Attempt to access unauthorized file: " + targetFile.getPath());
            return;
        }

        // Kiểm tra file tồn tại và tiến hành đọc
        if (targetFile.exists() && targetFile.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
                logger.info("Reading file: " + targetFile.getPath());
                String line;
                while ((line = reader.readLine()) != null) {
                    logger.info(line);
                }
            } catch (IOException e) {
                logger.severe("Error reading file: " + e.getMessage());
            }
        } else {
            logger.warning("File does not exist or is not a valid file: " + targetFile.getPath());
        }
    }
}
