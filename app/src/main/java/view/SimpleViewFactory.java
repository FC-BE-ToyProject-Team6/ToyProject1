package view;

import model.dao.TripJsonDAO;

public class SimpleViewFactory {

    public ConsoleView selectView(int menu){

        ConsoleView consoleView = null;

        if (menu == 1) {
            consoleView = new TripInput();
        } else if (menu == 2) {
            consoleView = new ItineraryInput();
        } else if (menu == 3) {
            consoleView = new TripsSelect(new TripJsonDAO());
        } else if (menu == 4) {
            consoleView = new ItinerarySelect();
        } else if (menu == 5) {
            consoleView = new ProgramExit();
        } else{
            throw new IllegalArgumentException("잘 못 된 입력 입니다.");
        }

        return  consoleView;

    }

}
