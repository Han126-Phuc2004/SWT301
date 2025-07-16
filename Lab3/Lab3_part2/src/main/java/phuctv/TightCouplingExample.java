package phuctv;

import java.util.logging.Level;
import java.util.logging.Logger;

// Interface để tách abstraction
interface Printer {
    void print(String message);
}

// Implementation sử dụng Logger
class ConsolePrinter implements Printer {
    private static final Logger logger = Logger.getLogger(ConsolePrinter.class.getName());

    @Override
    public void print(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(message);
        }
    }
}

// Lớp Report chỉ phụ thuộc vào abstraction Printer
class Report {
    private final Printer printer;

    public Report(Printer printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print("Generating report...");
    }
}

// Chương trình chính
public class TightCouplingExample {
    public static void main(String[] args) {
        Printer printer = new ConsolePrinter(); // Có thể dễ dàng thay thế bằng các Printer khác sau này
        Report report = new Report(printer);
        report.generate();
    }
}
