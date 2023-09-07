import view.MainMenuView;

public class App {

    public static void main(String[] args) {

        run();

    }

    private static void run() {

        MainMenuView mainMenuView = new MainMenuView();
        while(mainMenuView.isContinue()){
            mainMenuView.print();
        }

    }
}
