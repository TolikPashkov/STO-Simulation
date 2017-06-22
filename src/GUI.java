import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Home on 30.05.2017.
 */
public class GUI {
    private JButton firstButton;
    private JPanel MyPanel;
    private JLabel timeText;
    private JLabel post1;
    private JTextField textField1;
    private JLabel post2;
    private JTextField textField2;
    private JLabel post3;
    private JTextField textField3;
    private JLabel post4;
    private JTextField textField4;
    private JTextField textTime;
    private JButton start;
    private JTextField scale;
    private JTextField timeAuto;
    private JTextField timeRepair;
    private JTextField probability;
    private JButton confirm;
    private JButton stop;

    private int timeOfCreateAuto = 1000;
    private int timeOfRepairAuto = 5000;
    private int probabilityOfAgainBreak = 20;
    public static int scaleInt = 1;

    private AutoBuffer buffer [] = {new AutoBuffer(1), new AutoBuffer(2)};
    private Station station1;
    private Station station2;
    private Station station3;
    private Station station4;

    private Thread station1Thread;
    private Thread station2Thread;
    private Thread station3Thread;
    private Thread station4Thread;
    private Thread currentTimeThread;

    public GUI() {
        confirm.addActionListener(e -> {
            scaleInt = 1000 / Integer.parseInt(scale.getText());
            timeOfCreateAuto = Integer.parseInt(timeAuto.getText()) * scaleInt;
            timeOfRepairAuto = Integer.parseInt(timeRepair.getText()) * scaleInt;
            probabilityOfAgainBreak = Integer.parseInt(probability.getText());
        });
        start.addActionListener(e -> {
            scaleInt = 1000 / Integer.parseInt(scale.getText());
            timeOfCreateAuto = Integer.parseInt(timeAuto.getText()) * scaleInt;
            timeOfRepairAuto = Integer.parseInt(timeRepair.getText()) * scaleInt;
            probabilityOfAgainBreak = Integer.parseInt(probability.getText());
            start();
            System.out.println("SIMULATION IS STARTED!");
        });
        stop.addActionListener(e -> {
            stop();
            System.out.println("SIMULATION IS STOPPED!");
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("STO");
        frame.setContentPane(new GUI().MyPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void start(){
        currentTimeThread = new Thread(new CurrentTime());
        currentTimeThread.start();
        Timer timer = new Timer(100, e -> {
            textTime.setText(CurrentTime.time);
            textField1.setText(station1.getCounter().toString());
            textField2.setText(station2.getCounter().toString());
            textField3.setText(station3.getCounter().toString());
            textField4.setText(station4.getCounter().toString());
        });
        timer.start();
        station1 = new Station(timeOfRepairAuto, buffer[0], 1, probabilityOfAgainBreak);
        station2 = new Station(timeOfRepairAuto, buffer[0], 2, probabilityOfAgainBreak);
        station3 = new Station(timeOfRepairAuto, buffer[1], 3, probabilityOfAgainBreak);
        station4 = new Station(timeOfRepairAuto, buffer[1], 4, probabilityOfAgainBreak);
        new Thread(new AutoCreator(buffer, timeOfCreateAuto)).start();
        station1Thread = new Thread(station1);
        station1Thread.start();
        station2Thread = new Thread(station2);
        station2Thread.start();
        station3Thread = new Thread(station3);
        station3Thread.start();
        station4Thread = new Thread(station4);
        station4Thread.start();
    }
    private void stop(){
        station1Thread.stop();
        station2Thread.stop();
        station3Thread.stop();
        station4Thread.stop();
        currentTimeThread.stop();
    }
}
