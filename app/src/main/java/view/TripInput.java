package view;

import static common.StringUtil.printQuestion;
import static common.StringUtil.printTitle;
import static common.StringUtil.println;

import common.Scan;
import controller.TripInputController;
import model.Date;
import model.trip.TripDto;

public class TripInput implements ConsoleView {

    private static TripInput instance;
    private final TripInputController tiController;

    private TripInput() {
        tiController = new TripInputController();
    }

    public static TripInput getInstance() {
        if (instance == null) {
            instance = new TripInput();
        }
        return instance;
    }

    @Override
    public ConsoleView print() {
        printTitle("여행 기록");

        int tripId = inputTrip();
        ItineraryInput itineraryInput = (ItineraryInput) ItineraryInput.getInstance();
        itineraryInput.inputItineraryByOtherView(tripId);

        return MainMenu.getInstance();
    }

    public int inputTrip() {
        printQuestion("여행 정보를 입력 해주세요.");
        String tripName = Scan.nextLine("여행 이름");
        Date startDate = Scan.nextDate("시작 날짜(yyyy-mm-dd)");
        Date endDate = Scan.nextDate("종료 날짜(yyyy-mm-dd)");
        TripDto dto = new TripDto(tripName, startDate, endDate);

        int tripId = tiController.createTrip(dto);

        println("여행 정보가 저장 되었습니다.");
        return tripId;
    }

}
