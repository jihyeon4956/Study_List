package week05.thread;

public class Main {
    public static void main(String[] args) {
        // Thread 상속
//        TestThread thread = new TestThread();
//        thread.start();  /// TestThread에서 run()으로 만든  메서드를 .start()로 실행할 수 있음

        // Runable
//    Runnable run = new TeatRunable();
//    Thread thread = new Thread(run);
//    thread.start();

        // 람다식
        Runnable task = () -> {
            int sum = 0;
            for (int i = 0; i < 50; i++) {
                sum += i;
                System.out.println(sum);
            }
            System.out.println(Thread.currentThread().getName() + " 최종 합 : " + sum);
        };

        Thread thread1 = new Thread(task);
        thread1.setName("thread1");
        Thread thread2 = new Thread(task);
        thread2.setName("thread2");

        thread1.start();
        thread2.start();
    }
}