import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Telephone {

    private static ConcurrentLinkedQueue<Calls> callsQueue = new ConcurrentLinkedQueue<>(); // очередь звонков

    final int TIME_FOR_OPERATOR = 4000; // работа оператора
    final int TIME_FOR_ATS = 1500; // работа АТС


    final int ALL_CALL_LIMIT = 60;//Поток-АТС после запуска начинает генерировать несколько (например, 60) "звонков" раз в 1 секунду
    final int CALL_LIMIT = 20; //лимит вбросов звонков

    // Добавляем в очередь звонки
    public void queueWorkingATS() throws InterruptedException {

        while (callsQueue.size() < ALL_CALL_LIMIT) { // пока очередь не равна лимиту, каждую секунду вбрасываем по пачке звонков
            Thread.sleep(TIME_FOR_ATS);
            for (int i = 1; i < CALL_LIMIT + 1; i++) {
                //callsQueue.add(new Calls());
                callsQueue.add(new Calls(i));
            }
            for (Calls s : callsQueue) {
                System.out.println(s);
                //System.out.println(s.toString());
            }
            System.out.println("Очередь звонков составляет: " + callsQueue.size());
        }
    }


    //Обработка оператором звонков из очереди
    public void queueWorkingOperators() throws InterruptedException {
        while (true) {
            Thread.sleep(TIME_FOR_OPERATOR);
            Calls number = callsQueue.remove();
            if (number.equals(null)) {
                System.out.println("Звонков не было");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " обработал " + number);
        }
    }
}