package week03;
/*
 * 클래스를 만들기 위해서 진행하는 4가지 STEP
 *
 * 1. 만들려고 하는 설계도를 선언한다(클래스 선언)
 * 2. 객체가 가지고 있어야 할 속성(필드)를 정의한다.
 * 3. 객체를 생성하는 방식을 정의한다 (생성자)
 *      - constructer
 * 4. 객체가 가지고 있어야 할 행위(메서드)를 정의한다.
 */

import java.util.GregorianCalendar;

public class Car {
    // <필드영역>

    // 1) 고유 데이터 영역
    String company; // 자동차 회사
    String model = "Gv80"; // 자동차 모델
    String color; // 자동차 색상
    double price; // 자동차 가격

    // 2) 상태 데이터 영역
    double speed;  // 자동차 속도 (km/h)
    char gear; // 기어의 상태(P,R,N,D)
    boolean lights = true; // 자동차 조명의 상태 on,off

    // 3) ★객체 데이터 영역
    Tire tire = new Tire();  // Car가 인스턴스화 될 때 Tire도 인스턴스화가 진행된다.
    Door door;
    Handle handle;

    // 생성자: 처음 객체가 생성될 때 어떤 로직이 수행되어여 하며, 어떤 값이 필수로 들어와야 하는지 정의한다.
    public Car() {
        // 인풋, 로직이 없는 경우 기본생성자임.
        // 기본생성자는 생략이 가능하다
        System.out.println("생성자가 호출되었습니다. 객체가 생성됩니다.");
    }

    // <메서드 영역>

    // gasPedal (페달을 밟아서 가속을 진행함, 밟은 만큼 속도가 늘어남)
        // - input: kmh
        // - output: speed
    double gasPedal(double kmh, char type) {
        changeGear(type);       // 가속도 페달을 밟으면 자동으로 기어가 변한다
        speed = kmh;
        return speed;
    }

    // brakePedal
        // input 없음, output: speed
    double brakePedal() {
        speed = 0;
        return speed;
    }

    // changeGear
        // input: gear(char type)
        // output : gear(변경된 기어값|)
    char changeGear(char type) {
        gear = type;
        return gear;
    }

    // onOffLight
        // input: 없음, output: lights(boolean type)
    boolean onOffLight() {
        lights = !lights;
        return lights;
    }

    // horn
        // input: 없음 (누르면 소리 남)
        // output: 없음 (소리만 나면 끝임)
    void horn() {   // input, output 모두 없음->void
        System.out.println("빵빵");
    }

    void carSpeeds(double ... speeds) {
        for (double v : speeds) {
            System.out.println("v = " + v);
        }
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
