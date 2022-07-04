import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    public static final int MAX_NUMBER_OF_MESSAGES = 7;
    public static final int DELAY_TIME = 2000;
    private final String taskName;
    private final int numberOfMessages;

    public MyCallable(String taskName) {
        this.taskName = taskName;
        numberOfMessages = getRandomNumberOfMessages();
    }

    private int getRandomNumberOfMessages() {
        return new Random().nextInt(MAX_NUMBER_OF_MESSAGES) + 1;
    }

    @Override
    public String call() throws InterruptedException {
        int numberOfMessagesSent = 0;
        for (int i = 0; i < numberOfMessages; i++) {
            Thread.sleep(DELAY_TIME);
            System.out.printf("I am %s. doing %s. This %d message from %d.\n", Thread.currentThread().getName(), taskName,
                    ++numberOfMessagesSent, numberOfMessages);
        }
        return String.format("I am %s. %s finished. Send to console %d messages from %d.",
                Thread.currentThread().getName(), taskName, numberOfMessagesSent, numberOfMessages);
    }
}