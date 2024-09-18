package multithreading.classes;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceCarRunnable extends Car implements Runnable {
    private int passed = 0;
    private final int distance;
    private boolean isFinish = false;
    private final CountDownLatch latch;
    private long finishTime;

    public RaceCarRunnable(String name, int maxSpeed, int distance, CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.latch = latch;
    }

    private int getRandomSpeed() {
        Random random = new Random();
        return random.nextInt((getMaxSpeed() / 2), getMaxSpeed() + 1);
    }

    @Override
    public void run() {
        while (!isFinish) {
            int speed = getRandomSpeed();
            passed += speed;
            System.out.println(getName() + " => speed: " + speed + "; progress: " + passed + "/" + distance);
            if (passed >= distance) {
                isFinish = true;
                finishTime = System.currentTimeMillis() - Race.startRaceTime.get();
                latch.countDown();
                System.out.println(getName() + " FINISHED!");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getFinishTime() {
        return finishTime;
    }
}
