       import java.util.Random;
        import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Random random = new Random(); // 서로 다른 3개 숫자 뽑기 위함
        Scanner sc = new Scanner(System.in);

        int[] comArr = new int[3]; // 컴퓨터가 생성하는 서로 다른 숫자 배열

        for (int i = 0; i < 3; i++) { // 서로다른 3개의 숫자를 생성
            comArr[i] = random.nextInt(10);
            for (int k = 0; k < i; k++) {
                if (comArr[i] == comArr[k]) {
                    i--;
                }
            }
        }


        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맟춰보세요 !");

        String meNum = "";
        int[] meArr = new int[3];
        int count = 0;


        while (true) {
            int bNum = 0;
            int sNum = 0;
            meNum = sc.next();
            for (int i = 0; i < 3; i++) { // 입력 받은 문자열을 배열에 넣는 과정
                meArr[i] = meNum.charAt(i) - '0';
                System.out.println(meArr[i] = meNum.charAt(i));
                System.out.println(meArr[i] = meNum.charAt(i) - '0');
            }
            for(int i=0; i<3; i++){ // 스크라이크 수 세는 방법
                if(meArr[i] == comArr[i]){
                    sNum++;
                }
                for(int o=0; o<3; o++){ // 볼 수 세는 방법
                    if(meArr[i] == comArr[o] && i != o){
                        bNum++;
                    }
                }
            }
            count++;

            if(sNum == 3) { // 3스트라이크인 경우
                System.out.println(count + "번째 시도 : " + meNum);
                System.out.println(sNum + "S");
                System.out.println(count + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            } else if(bNum == 3){ // 3볼인 경우
                System.out.println(count + "번째 시도 : " + meNum);
                System.out.println(bNum + "B");

            }else { // 그밖의 경우
                System.out.println(count + "번째 시도 : " + meNum);
                System.out.println(bNum + "B" + sNum + "S");

            }

        }

    }
}
