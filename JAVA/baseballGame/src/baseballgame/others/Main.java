package baseballgame.others;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
// 랜덤 객체 생성
        Random random = new Random();

// 공 개수 설정
        int[] balls = new int[3];

// 랜덤 난수 설정후 값 담기
        for (int i = 0; i < 3; i++) {
            balls[i] = random.nextInt(10);
// 한번씩 숫자들 값 비교하면서 돌고, 만약 숫자값이 같으면
// 한번 더 숫자뽑으러가기
            for (int j = 0; j < i; j++) {
                if (balls[i] == balls[j]) {
                    i--;
                }
            }
        }

        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
// 첫번째.
        int i = 1;
        while (true) {
// S, B 초기화
            int S = 0;
            int B = 0;

//사용자 입력값 받기
            Scanner sc = new Scanner(System.in);
            System.out.print(i + "번째 시도 : ");
            String userInput = sc.nextLine();

//userInput 값 -> int 배열로 쪼개서 전환
            int[] userArray = new int[3];
            for (int j = 0; j < balls.length; j++) {
                userArray[j] = userInput.charAt(j) - '0';
            }

// 해당 자리에 숫자 맞으면 continue로 다음 배열로 넘어가기
            for (int k = 0; k < 3; k++) {
                if (userArray[k] == balls[k]) {
                    S++;
                    continue;
// 자리 안맞으면 2중 반복문으로 해당 값이 다른배열에는 있는지 확인
                }
                for (int j = 0; j < 3; j++) {
                    if (userArray[k] == balls[j]) {
                        B++;
                    }
                }
            }

// 출력하기 (문제 예시에 따름.)
            if (S == 0 && B == 0) {
                System.out.println("0B0S");
            } else if (S == 0 && B >= 1) {
                System.out.println(B + "B");
            } else if (S >= 1 && B == 0) {
                System.out.println(S + "S");
            } else {
                System.out.println(B + "B" + S + "S");
            }

// 정답일 경우 반복문 종료, 오답일 경우 횟수 i 추가
            if (S == 3) {
                System.out.println(i + "번만에 맞히셨습니다.\n게임을 종료합니다.");
                break;
            } else {
                i++;
            }
        }
    }
}

