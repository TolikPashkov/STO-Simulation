import java.util.Random;

/**
 * Created by Home on 28.05.2017.
 */
public class AutoCreator implements Runnable {
    private AutoBuffer buffer[];
    private Integer autoCreateTime;

    public AutoCreator(AutoBuffer[] buffer, Integer autoCreateTime) {
        this.buffer = buffer;
        this.autoCreateTime = autoCreateTime;
    }

    Random random = new Random();
    @Override
    public void run() {
        int autoId = 1;
        while (true) {
            try {
                Thread.sleep(autoCreateTime);
                buffer[random.nextInt(2)].put(new Auto(autoId++));
            } catch (InterruptedException ignore) {}
        }
    }
}
