/**
 * Created by Home on 31.05.2017.
 */
public class CurrentTime implements Runnable {
    private int seconds;
    public static String time;
    @Override
    public void run() {
        while (true) {
            int days = seconds / 24;
            int hours = seconds % 24;
            time = String.format("%02d days : %02d hours", days, hours);
            int scale = GUI.scaleInt;
            try {
                Thread.sleep(scale);
            } catch (InterruptedException ignore) {}
            seconds++;
        }
    }
}
