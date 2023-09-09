import common.AppStatus;
import view.ConsoleView;
import view.MainMenu;

public class App {

    public static void main(String[] args) {

        run();

    }

    private static void run() {

        ConsoleView consoleView = MainMenu.getInstance();
        while (AppStatus.isProgramRunning()) {
            consoleView = consoleView.print();
        }

    }
}
