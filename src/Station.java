import java.util.Random;

/**
 * Created by Home on 28.05.2017.
 */
public class Station implements Runnable{
    private Integer timeWork;
    private AutoBuffer buffer;
    private int id;
    private int probabilityOfBreaking;
    private Integer counter = 0;

    public Station(Integer timeWork, AutoBuffer buffer, int id, int probabilityOfBreaking) {
        this.timeWork = timeWork;
        this.buffer = buffer;
        this.id = id;
        this.probabilityOfBreaking = probabilityOfBreaking;
    }

    public Integer getCounter() {
        return counter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Auto repairingAuto = buffer.get();
                System.out.println(repairingAuto + " is repairing by " + this.id + " station!");
                Thread.sleep(timeWork);
                Random random = new Random();
                if (random.nextInt(100) < probabilityOfBreaking) {
                    System.out.println(repairingAuto + " is breaking again!");
                    Thread.sleep(timeWork);
                }
                System.out.println(repairingAuto + " is done!");
                counter++;
            } catch (InterruptedException ignore) {}
        }
    }
}
