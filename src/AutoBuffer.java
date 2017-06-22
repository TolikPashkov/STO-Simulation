/**
 * Created by Home on 28.05.2017.
 */
public class AutoBuffer {
    private Auto buffer[] = {null, null};
    private int id;

    public AutoBuffer(int id) {
        this.id = id;
    }

    public synchronized void put(Auto newAuto) throws InterruptedException {
        while(this.buffer[0] != null && this.buffer[1] != null){
            this.wait();
        }
        if (this.buffer[0] == null && this.buffer[1] == null){
            this.buffer[0] = newAuto;
            System.out.println("Put auto " + newAuto.getId() + " at first position of " + id + " queue");
        }
        else if (this.buffer[0] != null && this.buffer[1] == null){
            this.buffer[1] = newAuto;
            System.out.println("Put auto " + newAuto.getId() + " at second position of " + id + " queue");
        }
        else{
            this.buffer[0] = this.buffer[1];
            this.buffer[1] = newAuto;
            System.out.println("Put auto " + newAuto.getId() + " at second position of " + id + " queue");
        }
        this.notifyAll();
    }

    public synchronized Auto get() throws InterruptedException {
        while(this.buffer[0] == null){
            this.wait();
        }
        Auto result = this.buffer[0];
        this.buffer[0] = null;
        this.notifyAll();
        return result;
    }
}
