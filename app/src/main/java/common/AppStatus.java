package common;

public class AppStatus {

    private static boolean isProgramRunning;

    static {
        isProgramRunning = true;
    }

    public static boolean isProgramRunning() {
        return isProgramRunning;
    }

    public static void setProgramRunning(boolean b) {
        isProgramRunning = b;
    }


}
