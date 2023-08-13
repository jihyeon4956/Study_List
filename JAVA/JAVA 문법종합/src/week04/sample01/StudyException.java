package week04.sample01;

public class StudyException {
    public static void main(String[] args) {
        OurClass ourClass = new OurClass();  // 객체생성
//        ourClass.thisMethodIsDangerous();  // 위험하다고 throes, throw로 처리했기 때문에 그냥은 못부름

        /** try~catch~finally 구문
         * try : 시도하다
         * catch : 잡다(붙잡다)
         * finally : 마침내
         */

        // 일단 try해~ 그리고, 예외가 발생한다면 그걸 잡아!(catsh)
        // 그리고 오류여부에 관계없이 수행되야 하는 로직을(finally) 수행해

        try{
            // 일단 실행!!
            ourClass.thisMethodIsDangerous();
        }catch (OurBadException e){  // throws에 정의한 상황 쓰기(예외처리 종류)
            System.out.println(e.getMessage()); // e가 가지고 있는 메세지를 호출함
        }finally {
            // 여긴 무조건 실행됨
            System.out.println("우리는 방금 예외를 handling 했습니다. 정상여부와 관계없이 여기를 거쳐요!");

        }

    }
}
