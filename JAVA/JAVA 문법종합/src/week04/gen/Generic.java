package week04.gen;

// 1. 제네릭은 클래스 또는 메서드에 사용 가능함
// <> 안에 들어가야할 타입을 명시함
public class Generic<T> {
    // 2. 메인에서 생성된 객체로 T=String이 됨
    private T t;
    // 3. method의 return타입도 String임
    public T get() {
        return this.t;
    }

    public void set(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        // 4.
        Generic<String> stringGeneric = new Generic<>();
        // 5.
        stringGeneric.set("Hello World");

        String tValueTurnOutWithString = stringGeneric.get();

        System.out.println(tValueTurnOutWithString);
    }
}