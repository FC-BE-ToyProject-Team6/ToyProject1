package view;

import java.util.Scanner;

public class MainMenuView implements ConsoleView {

    Scanner sc;
    boolean isContinue;

    public MainMenuView() {
        this.sc = new Scanner(System.in);
        isContinue = true;
    }


    @Override
    public void print() {
        printTitle();
        printDivider();
        printMenuList();
        printDivider();
        moveNextMenu();
    }


    //이 메소드에 대해 개선 필요성이 있는거 같습니다.
    private void moveNextMenu() {
        System.out.print("시작 할 메뉴 번호를 입력하세요 : ");
        int status = sc.nextInt();

        if (status == 1) {
            ConsoleView cv = new TripInputView();
            cv.print();
            return;
        }
        if (status == 2) {
            System.out.println("여정입력 기능 구현 필요");
            return;
        }
        if (status == 3) {
            System.out.println("여행조회 기능 구현 필요");
            return;
        }
        if (status == 4) {
            System.out.println("여정조회 기능 구현 필요");
            return;
        }
        if (status == 5) {
            isContinue = false;
            System.out.println();
            printDivider();
            System.out.println("프로그램을 종료합니다.");
            printDivider();
            return;
        }

        throw new IllegalArgumentException("잘 못 된 입력 입니다.");
    }


    private void printTitle() {
        System.out.println("# 여행 여정을 기록하고 관리하는 SNS 서비스 #");
    }

    private void printMenuList() {
        System.out.println("여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5)");
    }


    private void printDivider() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append("----------");
        }
        System.out.println(sb.toString());
    }

    public boolean isContinue() {
        return isContinue;
    }
}
