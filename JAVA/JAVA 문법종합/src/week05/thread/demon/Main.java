package week05.thread.demon;

public class Main {
    public static void main(String[] args) {
        Runnable demon = () -> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(i + "번째 demon");
            }
        };

        // 우선순위가 낮다 => 상대적으로 다른 쓰레드에 비해 리소스를 적게 할당받는다.
        Thread thread = new Thread(demon);
        thread.setDaemon(true);  // true일 경우 우선순위가 낮은 Daemon으로 설정하는거임
                                 // 이 설정이 없다면 사용자 쓰레드로 생성된다.
                                 // JVM은 사용자 쓰레드의 작업이 끝나면 데몬 쓰레드도 자동으로 종료시켜 버린다
                                 // 부가적인 작업을 하는 역할이라 굳이 리소스를 할당하면서까지 기다려주진 않는다
        thread.start();

        for (int i = 0; i < 100; i++) {
            System.out.println(i + "번째 task");
        }
    }
}