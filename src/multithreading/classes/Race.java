package multithreading.classes;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Race {
    public static AtomicLong startRaceTime = new AtomicLong();

    public static void startRace(List<Thread> cars) {
        new Thread(() -> {
            try {
                System.out.println("3...");
                Thread.sleep(500);
                System.out.println("2...");
                Thread.sleep(500);
                System.out.println("1...");
                Thread.sleep(500);
                System.out.println("GO!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startRaceTime.set(System.currentTimeMillis());

            for (Thread car : cars) {
                car.start();
            }
        }).start();
    }
}
