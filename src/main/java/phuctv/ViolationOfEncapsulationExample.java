package phuctv;

import java.util.logging.Level;
import java.util.logging.Logger;

class User {
    private static final Logger logger = Logger.getLogger(User.class.getName());

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void display() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Name: %s, Age: %d", name, age));
        }
    }
}

public class ViolationOfEncapsulationExample {
    private static final Logger logger = Logger.getLogger(ViolationOfEncapsulationExample.class.getName());

    public static void main(String[] args) {
        User user = new User("Alice", 22);

        user.setName("Bob");
        user.setAge(30);

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Tuổi của user: " + user.getAge());
        }

        user.display();
    }
}