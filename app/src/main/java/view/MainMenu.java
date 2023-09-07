package view;

import java.util.Scanner;
import model.dao.TripJsonDAO;

public class MainMenu implements ConsoleView {

    private final Scanner sc;

    public MainMenu() {
        this.sc = ConsoleUtil.getScanner();
    }


    @Override
    public ConsoleView print() {
        ConsoleUtil.printTitle("여행 여정을 기록하고 관리하는 SNS 서비스");
        printMenuList();
        ConsoleUtil.printDivier();
        return inputNextMenu();
    }


    //이 메소드에 대해 개선 필요성이 있는거 같습니다.
    private ConsoleView inputNextMenu() {
        System.out.print("시작 할 메뉴 번호를 입력하세요 : ");

        int status = Integer.parseInt(sc.nextLine());

        if (status == 1) {
            return new TripInput();
        } else if (status == 2) {
            return new ItineraryInput();
        } else if (status == 3) {
            return new TripsSelect(new TripJsonDAO());
        } else if (status == 4) {
            return new ItinerarySelect();
        } else if (status == 5) {
            return new ProgramExit();
        }

        throw new IllegalArgumentException("잘 못 된 입력 입니다.");
    }


    private void printMenuList() {
        System.out.println("여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5)");
    }

}
