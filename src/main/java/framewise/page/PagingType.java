package framewise.page;

/**
 * @author chanwook
 */
public enum PagingType {
    NUMBERS, FRONT_BACK, SCROLL;

    public static PagingType get(String key) {
        if ("N".equals(key)) {
            return NUMBERS;
        } else if ("FB".equals(key)) {
            return FRONT_BACK;
        } else if ("S".equals(key)) {
            return SCROLL;
        }
        throw new IllegalArgumentException("요청 키에 해당하는 페이징 처리 유형이 없네요!");
    }
}
