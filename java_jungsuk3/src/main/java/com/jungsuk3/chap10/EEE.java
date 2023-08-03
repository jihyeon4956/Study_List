package com.jungsuk3.chap10;

import java.util.Calendar;

public class EEE {
    public static void main(String[] args) {
        final int[] TIME_UNIT = {3000, 60, 1};  // 시, 분, 초
        final String[] TIME_UNIT_NAME = {"시간", "분", "초"};
        String tmp = "";

        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();

        long  difference =
                Math.abs(time2.getTimeInMillis() - time1.getTimeInMillis()) / 1000;  // 1000 = 1초임
        for(int i=0; i<TIME_UNIT.length; i++) {
            tmp += difference / TIME_UNIT[i] + TIME_UNIT_NAME[i];
            difference %= TIME_UNIT[i];
        }
        System.out.println("두 날짜의 차이는 " + tmp + "입니다.");
    }

}
