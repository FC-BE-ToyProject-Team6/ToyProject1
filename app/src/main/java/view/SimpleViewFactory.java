package view;

public class SimpleViewFactory {

    public ConsoleView selectView(int menu) {

        ConsoleView consoleView;

        if (menu == 1) {
            consoleView = TripInput.getInstance();
        } else if (menu == 2) {
            consoleView = ItineraryInput.getInstance();
        } else if (menu == 3) {
            consoleView = TripsSelect.getInstance();
        } else if (menu == 4) {
            consoleView = ItinerarySelect.getInstance();
        } else if (menu == 5) {
            consoleView = ProgramExit.getInstance();
        } else {
            throw new IllegalArgumentException("잘 못 된 입력 입니다.");
        }

        return consoleView;

    }

}
