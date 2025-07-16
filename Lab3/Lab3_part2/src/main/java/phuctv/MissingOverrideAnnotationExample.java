package phuctv;

import java.util.logging.Level;
import java.util.logging.Logger;

class Animal {
    private static final Logger logger = Logger.getLogger(Animal.class.getName());

    void speak() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Animal speaks");
        }
    }
}

class Dog extends Animal {
    private static final Logger logger = Logger.getLogger(Dog.class.getName());

    @Override // Viết đúng tên, có @Override
    void speak() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Dog barks");
        }
    }
}

public class MissingOverrideAnnotationExample {
    private static final Logger logger = Logger.getLogger(MissingOverrideAnnotationExample.class.getName());

    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();

        animal.speak(); // Animal speaks
        dog.speak();    // Dog barks (gọi để thành công)

        if (logger.isLoggable(Level.INFO)) {
            logger.info("Program executed successfully.");
        }
    }
}
