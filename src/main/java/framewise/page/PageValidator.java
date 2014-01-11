package framewise.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 페이징 처리에 필요한 파라미터 정보를 검증
 *
 * @author chanwook
 *
 */
public class PageValidator {

    private final Logger logger = LoggerFactory.getLogger(PageValidator.class);

    public void validate(PagingParam param) {
        if (0 >= param.getPageNumber()) {
            String msg = "페이지 번호는 1부터 시작해야 합니다! (요청 페이지 번호=" + param.getPageNumber() + ")";
            logger.error(msg);
            throw new UnCorrentPageArgumentException(msg);
        }
    }
}
