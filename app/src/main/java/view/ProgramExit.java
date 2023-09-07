package view;

import common.AppStatus;
import common.StringUtil;

public class ProgramExit implements ConsoleView {


    @Override
    public ConsoleView print() {
        AppStatus.setProgramRunning(false);
        StringUtil.printTitle("프로그램을 종료합니다.");
        return null;
    }
}
