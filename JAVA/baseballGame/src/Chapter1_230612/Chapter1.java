package Chapter1_230612;

/*
 * randomSelect : random한 숫자를 선택하는 변수 random.nextInt()을 사용함
 * comNum: 생성된 random숫자를 저장할 ArrayList 변수명
 * guess : 사용자가 입력한 숫자를 받을 Array 변수명
 * guessEnter : 사용자가 입력한 숫자를 받는 String 변수
 * Snum: 조건 S일때 카운트하는 int형 변수
 * Bnum: 조건 B일때 카운트하는 int형 변수
 * count : 정답을 맞추는 시도횟수를 기록하는 int형 변수
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Chapter1 {
    public static void main(String[] args) {
        // 입력부
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Integer> comNum = new ArrayList<>();     // 컴퓨터 랜덤값 받을 배열
        int[] guess = new int[3];                        // 사용자 입력값 받을 배열
        int count = 1;  // S, B, 시도횟수 기록
        String guessEnter = "";

        // 랜덤값 중복없이 선택
        while(comNum.size() < 3) {                          // arrayList의 길이가 3이 될때까지 반복, 3이 되는 순간 반복문 종료됨
            int randomSelect = random.nextInt(10);
            if (!comNum.contains(randomSelect)) {              // randomSelect가 comNum 내부에 없을 경우에만 저장
                comNum.add(randomSelect);
            }
        }
        System.out.println("컴퓨터 랜덤값: " + comNum);           // 확인용

        // 사용자 입력값
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while (true) {
            System.out.print(count + "번째 시도 : ");
            guessEnter = sc.next();
            for (int i = 0; i < guess.length; i++) {    // 사용자 지정 숫자 3개 입력
                guess[i] = guessEnter.charAt(i) - 48;
//                System.out.println("★ 입력값 확인 : " + guess[i]);
            }
            int Snum = 0, Bnum = 0;         // 입력 후 상태확인에 사용되어 지역변수로 선언

            for (int i = 0; i < comNum.size(); i++) {
                if (comNum.get(i) == guess[i]) {
                     Snum++;
                } else {
                    for (int j = 0; j < comNum.size(); j++) {
                        if (comNum.get(i) == guess[j]) {
                            Bnum++;
                        }
                    }
                }
            }

            if (Snum < 3 && Bnum < 3) {
                System.out.println(Bnum + "B" + Snum + "S" );
                count++;
            }
            else if (Bnum == 3) {
                System.out.println(Bnum + "B" );
                count++;
            }
            else if (Snum == 3){
                System.out.println(Snum + "S" );
                System.out.println(count
                        + "번만에 맞히셨습니다."
                        + "\n"
                        + "게임을 종료합니다.");
                break;
            }
        }
        sc.close();         // Scanner() 종료 (안써도 무방하나,
                            // 추후 코드가 길어지거나 이후 Scanner를 다시 사용할 일이 생겼을 때 코드가 꼬일 수 있음
    }
}


