package com.jungsuk3.chap11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

class Student5 implements Comparable <Student5>{
    String name;
    int ban;
    int no;
    int kor, eng, math;

    Student5(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    int getTotal() {
        return kor + eng + math;
    }

    float getAverage() {
//        return (int) ((getTotal() / 3f) * 10 + 0.5) / 10f;
        return Math.round((getTotal() / 3f)*10) / 10f;
    }

    public String toString() {
        return name + "," + ban + "," + no + "," + kor + "," + eng + "," + math
                + "," + getTotal() + "," + getAverage();
    }

    @Override
    public int compareTo(Student5 s) {
            return name.compareTo(s.name); // String 클래스의 compareTo()를 호출함
    }
}

class Exercise11_5 {
    public static void main(String[] args) {
        ArrayList<Student5>list = new ArrayList<Student5>();
        list.add(new Student5("홍길동", 1, 1, 100, 100, 100));
        list.add(new Student5("남궁성", 1, 2, 90, 70, 80));
        list.add(new Student5("김자바", 1, 3, 80, 80, 90));
        list.add(new Student5("이자바", 1, 4, 70, 90, 70));
        list.add(new Student5("안자바", 1, 5, 60, 100, 80));


        Collections.sort(list); ; // list에 저장된 요소들을 정렬한다.(compareTo()호출)
        Iterator it = list.iterator();

        while (it.hasNext())
            System.out.println(it.next());
    }
}
