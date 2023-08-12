package week03.packageExample.main;


import week03.packageExample.pk1.Car;

public class Main {
    // 패키지는 클래스의 일부분이며, 하위 패키지를 도트(.)로 구분한다.
    public static void main(String[] args) {
        Car car = new Car();
        car.horn();
    }
}
