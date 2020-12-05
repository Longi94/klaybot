package in.dragonbra.klayb0t.thread;

public class Delay {
    public static void run(Runnable r, long ms) {
        new DelayedThread(r, ms).start();
    }

    private static class DelayedThread extends Thread {
        private final Runnable r;
        private final long ms;

        private DelayedThread(Runnable r, long ms) {
            this.r = r;
            this.ms = ms;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(ms);
                r.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }
}
