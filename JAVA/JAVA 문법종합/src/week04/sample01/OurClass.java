package week04.sample01;

public class OurClass {
    private final boolean just = true;

    // throws : 던지다 (예외를 던지다. 발생시키다)

    public void thisMethodIsDangerous() throws OurBadException{  // 위험한 로딕으로 가정
        // custom logic
        // 사전에 위험할 수 있다고 인지한 상태에서 이건 위험하다라는 flag를 달아줌

        if(just) {
            throw new OurBadException(); // 만약에 여기로 들어오면 새로운 OurBadException을 만들어서 던진다는 의미
         }

    }
}
