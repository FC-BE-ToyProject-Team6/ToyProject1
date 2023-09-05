package view;

public class ProgramExit implements ConsoleView {

    boolean status;

    @Override
    public ConsoleView print() {

        System.out.println("\n프로그램을 종료합니다.");
        status = true;
        return this;
    }

    public boolean isEnd() {
        return status;
    }
}
