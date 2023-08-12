package week03;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();   // 객체 생성


        System.out.println(car.speed);
        double speed = car.gasPedal(100, 'D');
        System.out.println(speed);
        System.out.println(car.gear);
        System.out.println(car.speed);

        System.out.println(car.lights);
        boolean lights = car.onOffLight();
        System.out.println(car.lights);

        System.out.println();
        car.carSpeeds(100, 80);
        System.out.println();
        car.carSpeeds(110, 120, 150);
      }
}
