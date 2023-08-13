package baseballgame.my;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class My2_baseball_230814 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Set<Integer> comNum = new HashSet<>();
        int count = 1;

        while (comNum.size() < 3) {
            comNum.add(random.nextInt(10));
        }

        System.out.println("컴퓨터 랜덤값(검증용): " + comNum);

        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while (true) {
            System.out.print(count + "번째 시도 : ");
            String input = sc.nextLine();

            int[] guess = new int[3];
            for (int i = 0; i < input.length(); i++) {
                guess[i] = Character.getNumericValue(input.charAt(i));
            }

            int Snum = 0, Bnum = 0;
            int[] comNumArray = comNum.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();

            for (int i = 0; i < comNumArray.length; i++) {
                if (comNum.contains(guess[i])) {
                    if (comNumArray[i] == guess[i]) {
                        Snum++;
                    } else {
                        Bnum++;
                    }
                }
            }

            if (Snum < 3 && Bnum < 3) {
                System.out.println(Bnum + "B" + Snum + "S");
                count++;
            } else if (Bnum == 3) {
                System.out.println(Bnum + "B");
                count++;
            } else if (Snum == 3) {
                System.out.println(Snum + "S");
                System.out.println(count + "번만에 맞히셨습니다.\n게임을 종료합니다.");
                break;
            }
        }
        sc.close();
    }
}
