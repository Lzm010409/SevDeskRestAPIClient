package main;

import org.jboss.logging.Logger;

public class ThreadSleeper {
    private final Logger logger = Logger.getLogger(ThreadSleeper.class);

    public ThreadSleeper() {

    }

    public void threadSleep(int i) {
        try {
            logger.log(Logger.Level.INFO, "Thread wird nun für: " + i + " Millisekunden unterbrochen.");
            Thread.sleep(i);
            logger.log(Logger.Level.INFO, "Thread wird nun fortgesetzt.");
        } catch (InterruptedException exception) {
            logger.log(Logger.Level.ERROR, "Der Thread Sleep wurde unterbrochen. Stacktrace: " + exception.getMessage());
        }
    }
}
