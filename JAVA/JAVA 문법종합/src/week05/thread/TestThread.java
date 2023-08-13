package week05.thread;

// 1. Thread Class를 이용하는 방법(상속받을 수 있음)

public class TestThread extends Thread{
    @Override
    public void run() {
        // 실제 우리가 쓰레드에서 수향할 작업
        for (int i=0; i<100; i++){
            System.out.print("*");
        } // 이걸 Main에서 구현하고 실행할거임
    }
}
