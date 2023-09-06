package view;

import java.util.Scanner;

public class MainMenuView implements ConsoleView {

    Scanner sc;
    int status;

    public MainMenuView() {
        this.sc = new Scanner(System.in);
    }


    @Override
    public void print() {
        printTitle();
        printDivider();
        printMenuList();
        printDivider();
        inputMenuNumber();
        moveNextMenu();
    }


    //이 메소드에 대해 개선 필요성이 있는거 같습니다.
    private ConsoleView moveNextMenu() {
        if (status == 1) {
            ConsoleView cv = new TripInputView();
            cv.print();
        }
        if (status == 2) {
            // return 여정 입력 화면 인스턴스
        }
        if (status == 3) {
            // return 여행 조회 화면 인스턴스
        }
        if (status == 4) {
            // return 여정 조회 화면 인스턴스
        }
        if (status == 5) {
            System.out.println("\n프로그램을 종료합니다.");
            System.exit(0);
        }

        throw new IllegalArgumentException("잘 못 된 입력 입니다.");
    }


    private void printTitle() {
        System.out.println("# 여행 여정을 기록하고 관리하는 SNS 서비스 #");
    }

    private void printMenuList() {
        System.out.println("여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5)");
    }

    private void inputMenuNumber() {
        System.out.print("시작 할 메뉴 번호를 입력하세요 : ");
        status = sc.nextInt();
    }

    private void printDivider() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append("----------");
        }
        System.out.println(sb.toString());
    }

    public boolean isEnd() {
        return false;
    }
}
