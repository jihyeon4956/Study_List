package week05.thread;

public class Main {
    public static void main(String[] args) {
//        TestThread thread = new TestThread();
//        thread.start();  /// TestThread에서 run()으로 만든  메서드를 .start()로 실행할 수 있음
    Runnable run = new TeatRunable();
    Thread thread = new Thread(run);
    thread.start();
    }
}
