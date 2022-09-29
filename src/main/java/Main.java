import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) {

        final int NUMBER_OF_THREADS = 4;

        Telephone callCenter = new Telephone();

        new Thread(() -> {
            try {
                callCenter.queueWorkingATS();
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }
        }).start();

        ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threadPool.submit(() -> {
                try {
                    callCenter.queueWorkingOperators();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}