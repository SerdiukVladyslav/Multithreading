package multithreading.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static multithreading.classes.Race.startRace;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<RaceCarRunnable> cars = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(3); // 3 участника

        // Инициализация гоночных машин
        cars.add(new RaceCarRunnable("Car1", 200, 1000, latch));
        cars.add(new RaceCarRunnable("Car2", 180, 1000, latch));
        cars.add(new RaceCarRunnable("Car3", 220, 1000, latch));

        // Создание потоков для машин
        List<Thread> carThreads = new ArrayList<>();
        for (RaceCarRunnable car : cars) {
            carThreads.add(new Thread(car));
        }

        // Старт гонки
        startRace(carThreads);

        // Ожидание окончания гонки всеми участниками
        latch.await();

        // Определение победителя
        RaceCarRunnable winner = cars.stream()
                .min((car1, car2) -> Long.compare(car1.getFinishTime(), car2.getFinishTime()))
                .get();

        System.out.println("\nRace Results:");
        for (RaceCarRunnable car : cars) {
            long finishTime = car.getFinishTime();
            String formattedTime = TimeFormatter.convertToTime(finishTime);
            System.out.println(car.getName() + " finished in " + finishTime + " ms (" + formattedTime + ")");
        }

        System.out.println("\nWinner is " + winner.getName() + " with time: " + winner.getFinishTime() + " ms (" + TimeFormatter.convertToTime(winner.getFinishTime()) + ")");
    }
}
