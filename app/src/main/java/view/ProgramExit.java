package view;

import static common.StringUtil.printTitle;

import common.AppStatus;

public class ProgramExit implements ConsoleView {

    private static ProgramExit instance;

    private ProgramExit() {}

    public static ProgramExit getInstance() {
        if (instance == null) {
            instance = new ProgramExit();
        }
        return instance;
    }

    @Override
    public ConsoleView print() {
        AppStatus.setProgramRunning(false);
        printTitle("프로그램을 종료합니다.");
        return null;
    }
}
