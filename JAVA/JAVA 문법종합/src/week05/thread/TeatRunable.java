package week05.thread;

public class TeatRunable implements Runnable{

    @Override
    public void run() {
        // 쓰레드에서 수행할 작업을 정의!
        // 이 방식이 더 많이 사용됨
        for (int i=0; i<100; i++){
            System.out.print("$");
        }
    }
}
