package view;

import static common.StringUtil.printDivier;
import static common.StringUtil.printTitle;

import common.Scan;

public class MainMenu implements ConsoleView {

    private static MainMenu instance;
    private final SimpleViewFactory svf;

    private MainMenu() {
        svf = new SimpleViewFactory();
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    @Override
    public ConsoleView print() {
        printTitle("여행 여정을 기록하고 관리하는 SNS 서비스");
        printMenuList();
        printDivier();
        return inputNextMenu();
    }


    //이 메소드에 대해 개선 필요성이 있는거 같습니다.
    private ConsoleView inputNextMenu() {
        int status = Scan.nextInt("시작 할 메뉴 번호를 입력 하세요.", 1, 5);
        return svf.selectView(status);
    }


    private void printMenuList() {
        System.out.println("여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5)");
    }

}
