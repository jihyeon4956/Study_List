package com.jungsuk3.chap11;

import java.util.HashSet;
import java.util.Set;

/**
 * [11-1] 다음은 정수집합 1,2,3,4와 3,4,5,6의 교집합, 차집합, 합집합을 구하는 코드이다
 * 코드를 완성하여 실행결과와 같은 결과를 출력하시오 . .
 * [Hint] ArrayList addAll(), removeAll(), retainAll() . 클래스의 을 사용하라
 */

public class Ex11_1_2 {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        Set<Integer>  kyo = new HashSet<>(); // 교집합
        Set<Integer>  cha = new HashSet<>(); // 차집합
        Set<Integer>  hap = new HashSet<>(); // 합집합
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);


        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);

//(1) . 알맞은 코드를 넣어 완성하시오
        kyo.addAll(set1);
        kyo.retainAll(set2);

        cha.addAll(set1);
        cha.removeAll(set2);

        hap.addAll(set1);
        hap.addAll(set2);

        System.out.println("list1="+set1);
        System.out.println("list2="+set2);
        System.out.println("kyo="+kyo);
        System.out.println("cha="+cha);
        System.out.println("hap="+hap);
    }
}