package view;

import common.AppStatus;

public class ProgramExit implements ConsoleView {


    @Override
    public ConsoleView print() {
        AppStatus.setProgramRunning(false);
        ConsoleUtil.printTitle("프로그램을 종료합니다.");
        return null;
    }
}
