package view;
import static common.StringUtil.*;
import common.AppStatus;

public class ProgramExit implements ConsoleView {


    @Override
    public ConsoleView print() {
        AppStatus.setProgramRunning(false);
        printTitle("프로그램을 종료합니다.");
        return null;
    }
}
