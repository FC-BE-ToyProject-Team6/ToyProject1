import view.ConsoleView;
import view.MainMenuView;

public class App {

    public static void main(String[] args) {

        run();

    }

    private static void run() {

        ConsoleView consoleView = new MainMenuView();
        consoleView.print();

    }
}
