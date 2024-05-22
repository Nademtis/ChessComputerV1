import java.util.TimerTask;
import java.util.Timer;

public class SimpleTimer extends Thread{

    public int currentTime; // time in seconds

    public int timeLimit;

    private Timer timer;

    private Thread searchThread;

    private volatile boolean running = true;

    public SimpleTimer(int timeLimit, Thread searchThread){
        this.timeLimit = timeLimit;
        this.currentTime = 0;
        this.timer = new Timer();
        this.searchThread = searchThread;
    }

    @Override
    public void run() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!running) {
                    timer.cancel(); // Stop the timer
                    System.out.println("Timer stopped");
                } else if (currentTime < timeLimit) {
                    currentTime++;
                    System.out.println("Time elapsed: " + currentTime + " seconds");
                } else {
                    searchThread.interrupt();
                    timer.cancel();
                    System.out.println("Time limit reached: " + timeLimit + " seconds");
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000); // schedule the task to run every second
    }

    // Method to stop the timer
    public void stopTimer() {
        running = false;
        System.out.println("Stopping timer");
        timer.cancel();
    }
}
