package framewise.page;

/**
 *
 * 잘못된 페이지 정보를 인자로 넘기는 경우에 발생하는 예외 클래스
 *
 * @author chanwook
 */
public class UnCorrentPageArgumentException extends RuntimeException {
    public UnCorrentPageArgumentException(String msg) {
        super(msg);
    }
}
