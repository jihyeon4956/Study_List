package baseballgame.others;

import java.util.Random;
import java.util.Scanner;

public class Park_baseball_230612 {
    public static void main(String[] args) {

        int[] computerBaseballNumbers = generateComputerBaseballNumbers();
// int[] computerBaseballNumbers = new int[]{1, 2, 3};
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
// 2. 한자리 숫자에 대해 볼, 스크라이크 판단 하는 부분 구현하기
        Scanner sc = new Scanner(System.in);
        int trial = 0;

        while (true) {
            trial++;
            System.out.print(trial + "번째 시도 : ");
            int[] userNumbers = inputUserNumbers(sc.nextLine());

// 3. 볼, 스트라이크를 표현하는 부분 구현하기
            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 3; i++) {
                if (userNumbers[i] == computerBaseballNumbers[i]) {
                    strike++;
                } else {
                    for (int j = 0; j < 3; j++) {
                        if (userNumbers[i] == computerBaseballNumbers[j]) {
                            ball++;
                            break;
                        }
                    }
                }
            }
            System.out.println(refereeBGameResult(strike, ball));

// 4. 게임 종료하는 부분 구현하기
            if (strike == 3) {
                System.out.println(trial + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            }
        }
        sc.close();
    }

    private static String refereeBGameResult(int strike, int ball) {
//3S
//3B
//2B1S
//2B0S
        StringBuilder sb = new StringBuilder();
        if (strike != 3) {
            sb.append(String.format("%dB", ball));
        }
        if (ball != 3) {
            sb.append(String.format("%dS", strike));
        }
        return sb.toString();
    }

    private static int[] inputUserNumbers(String input) {
        int[] trialNumber = new int[3];
        for (int i = 0; i < 3; i++) {
            trialNumber[i] = Character.getNumericValue(input.charAt(i)); //getNumericValue : 숫자 형태의 char형을 int형으로 변환
        }
        return trialNumber;
    }

    private static int[] generateComputerBaseballNumbers() {
        Random randomNumberGenerator = new Random();
        int[] computerBaseballNumbers = new int[3];//comBNo
        int computerBaseballNumberIndex = 0; //배열의 인덱스 0, 1, 2
// 1. 랜덤 숫자 만들기
        while (computerBaseballNumberIndex < 3) {
            int generatedComputerNumber = randomNumberGenerator.nextInt(10);

            boolean isNotDuplicatedBaseballNumber = findNotDuplicateBaseballNumber(computerBaseballNumbers, computerBaseballNumberIndex, generatedComputerNumber);

            if (isNotDuplicatedBaseballNumber) { //부정문 쓰는것을 권장하지 않음 : 가독성 문제 때문에 / 사람의 의식의 흐름에 맞지 않음 //중복이 없는 경우에만 새로운 숫자 추가
                computerBaseballNumbers[computerBaseballNumberIndex++] = generatedComputerNumber;
            }
        }
        return computerBaseballNumbers;
    }

    private static boolean findNotDuplicateBaseballNumber(int[] computerBaseballNumbers, int computerBaseballNumberIndex, int generatedComputerNumber) {
        for (int i = 0; i < computerBaseballNumberIndex; i++) {
            if (generatedComputerNumber == computerBaseballNumbers[i]) {
                return false;
            }
        }
        return true;
    }
}