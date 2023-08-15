package baseballgame.my;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class My2_baseball_230814 {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Random random = new Random();
            Set<Integer> comNum = new HashSet<>(); // LinkedHashSet 사용
            int[] guess = new int[3];
            int count = 1;

            while (comNum.size() < 3) {
                comNum.add(random.nextInt(10));
            }


            System.out.println("컴퓨터 랜덤값: " + comNum); // 개발자 확인용

            System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

            while (true) {
                System.out.print(count + "번째 시도 : ");
                for (int i = 0; i < guess.length; i++) {
                    guess[i] = sc.nextInt();
                }

                int Snum = 0, Bnum = 0;

                int[] comNumArray = comNum.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
                // Set을 배열로 변환
                // .mapToInt : 결과값을 새로운 Int스트림으로 생성함


                for (int i = 0; i < comNumArray.length; i++) {
                    if (comNum.contains(guess[i])) {
                        if (comNumArray[i] == guess[i]) {    // 여기서 인덱스별로 일치여부 체크하려고 Array로 변환함
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