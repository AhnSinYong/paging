package framewise.page;

/**
 * 데이터의 Validation을 지원하는 유틸 클래스
 *
 * @author chanwook
 */
public class Assert {
    public static void notLessThan(int base, int target, String message) {
        if (base >= target) {
            throw new IllegalArgumentException(message);
        }
    }
}
