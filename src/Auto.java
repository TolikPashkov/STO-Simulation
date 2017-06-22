/**
 * Created by Home on 28.05.2017.
 */
public class Auto {
    private int id;

    public Auto(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }
}
