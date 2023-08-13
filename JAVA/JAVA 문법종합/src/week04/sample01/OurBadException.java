package week04.sample01;

// 예외 클래스를 만들어서 예외를 정의한다.
// 예외상황을 미리 정해두는게 중요하고 지금은 예외 사항 정의하는걸 Class로 한다
public class OurBadException extends Exception{
    public OurBadException(){
        super("위험한 행동을 하면 예외처리를 꼭 해야함!!");  // 생성자가 호출될 때 부모클래스의 생성자로 호출한다
    }

}
