package framewise.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 페이지 처리를 지원하는 모델 클래스. 페이징 주요 데이터 항목과 로직을 포함함
 *
 * @author chanwook
 */
public class PageInformation implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(PageInformation.class);

    /**
     * 몇 번째 페이지 인가
     */
    private int pageNumber;

    /**
     * 페이징 당 몇 개의 데이터(=아이템)을 보여줄 것인가?
     */
    private int pageItemSize;

    /**
     * 전체는 몇 개의 데이터(=아이템)이 있는
     */
    private long totalItemCount;

    /**
     * 전체 페이지 수는?
     */
    private int totalPage;

    /**
     * 페이징 화면 하단의 직접 네비게이션 가능한 숫자는?
     */
    private Integer[] navigationNumber;

    /**
     * 이전 버튼을 활성화할 것인가?
     */
    private boolean enablePrevious = false;

    /**
     * 다음 버튼을 활성화할 것인가?
     */
    private boolean enableNext = false;

    /**
     * 현재 네비게이션 바 중에서 몇 번째 숫자를 보여주고 있는가?
     */
    private int navigationOnCount;

    /**
     * 페이지 수로 페이징 처리 하는 경우에 한 화면에 보여주는 페이지의 수
     */
    private int navigationNumberSize;

    /**
     * 렌더싱 시 전달되는 generic한 데이터 구조
     */
    private Map<String, String> templateVariableMap = new HashMap<String, String>();
    private int navigationOrder;

    /**
     * 기본 생성자
     */
    public PageInformation() {
    }

    /**
     * 실제 필요한 데이터를 인자로 받아서 인스턴스를 생성하는 생성자
     *
     * @param pageNumber           전체 페이지 중 현재 페이지 수
     * @param pageItemSize         한 페이지에서 담는 아이템 수
     * @param totalItemCount       전체 아이템 수
     * @param totalPage            전체 페이지 수
     * @param navigationNumberSize 페이지 수로 페이징 처리 하는 경우에 한 화면에 보여주는 페이지의 수
     */
    public PageInformation(int pageNumber, int pageItemSize, long totalItemCount, int totalPage, int navigationNumberSize) {
        this.pageNumber = pageNumber;
        this.pageItemSize = pageItemSize;
        this.totalItemCount = totalItemCount;
        this.totalPage = totalPage;
        this.navigationNumberSize = navigationNumberSize;

        createPageNavigation(navigationNumberSize);
    }

    public PageInformation(PagingParam param, long totalItemCount, int totalPage) {
        this(param.getPageNumber(), param.getPageItemSize(), totalItemCount, totalPage, param.getNavigationSize());
        this.templateVariableMap = param.getTemplateVariableMap();
    }

    /**
     * 페이지 네비게이션을 위한 정보를 생성
     *
     * @param navigationNumberSize
     */
    public void createPageNavigation(int navigationNumberSize) {
        if (navigationNumberSize < 1) {
            logger.debug("Navigation Size가 1보다 작기 때문에 Navigation bar 계산 로직을 수행하지 않습니다.(요청 size:" + navigationNumberSize + ")");
            return;
        }

        int navigationOnCount = calNavigationOnCount(navigationNumberSize);
        setNavigationOnCount(navigationOnCount);

        int navigationOrder = calNavigationOrder(navigationNumberSize);
        setNavigationOrder(navigationOrder);

        List<Integer> list = resolveNavigationNumber(navigationNumberSize, navigationOnCount);

        this.navigationNumber = list.toArray(new Integer[list.size()]);

        // 이전버튼 활성화 처리 여부
        if (this.navigationNumber[0] > navigationNumberSize) {
            this.enablePrevious = true;
        }

        // 다음버튼 활성화 처리 여부
        if (this.navigationNumber.length == navigationNumberSize &&
                ((this.navigationNumber.length == 5 && this.navigationNumber[4] < this.totalPage)
                        || (navigationNumberSize == 1 && navigationNumberSize < this.totalPage))) {
            this.enableNext = true;
        }
    }

    protected List<Integer> resolveNavigationNumber(int navigationNumberSize, int navigationOnCount) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i <= navigationNumberSize; i++) {
            if (i == navigationOnCount) {
                addIfPossible(list, pageNumber);
            } else if (i > navigationOnCount) {
                addIfPossible(list, pageNumber + (i - navigationOnCount));
            } else if (i < navigationOnCount) {
                addIfPossible(list, pageNumber - (navigationOnCount - i));
            }
        }
        return list;
    }

    protected int calNavigationOrder(int navigationNumberSize) {
        int navigationOrder = this.pageNumber / navigationNumberSize;
        if (this.pageNumber % navigationNumberSize != 0) {
            navigationOrder += 1;
        }
        return navigationOrder;
    }

    /**
     * 현재 페이지가 네비게이션 바에서 몇 번째 순서인지 계산함
     *
     * @param navigationSize
     * @return
     */
    private int calNavigationOnCount(int navigationSize) {
        int count = this.pageNumber % navigationSize;

        // 네비게이션하기 위한 마지막 페이지인경우 navigationSize로 나눌 씨 나머지가 0이고,
        if (this.pageNumber % navigationSize == 0) {
            return navigationSize;
        }
        return count;

    }

    private void addIfPossible(List<Integer> list, int count) {
        if (count <= this.totalPage) {
            list.add(count);
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageItemSize() {
        return pageItemSize;
    }

    public void setPageItemSize(int pageItemSize) {
        this.pageItemSize = pageItemSize;
    }

    public long getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(long totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Integer[] getNavigationNumber() {
        return navigationNumber;
    }

    public void setNavigationNumber(Integer[] navigationNumber) {
        this.navigationNumber = navigationNumber;
    }

    public boolean isEnablePrevious() {
        return enablePrevious;
    }

    public void setEnablePrevious(boolean enablePrevious) {
        this.enablePrevious = enablePrevious;
    }

    public boolean isEnableNext() {
        return enableNext;
    }

    public void setEnableNext(boolean enableNext) {
        this.enableNext = enableNext;
    }

    public int getNavigationOnCount() {
        return navigationOnCount;
    }

    public void setNavigationOnCount(int numberSetCount) {
        this.navigationOnCount = numberSetCount;
    }

    public Map<String, String> getTemplateVariableMap() {
        return templateVariableMap;
    }

    public void setTemplateVariableMap(Map<String, String> templateVariableMap) {
        this.templateVariableMap = templateVariableMap;
    }

    public int getNavigationNumberSize() {
        return navigationNumberSize;
    }

    public void setNavigationNumberSize(int navigationNumberSize) {
        this.navigationNumberSize = navigationNumberSize;
    }

    public void setNavigationOrder(int navigationOrder) {
        this.navigationOrder = navigationOrder;
    }

    public int getNavigationOrder() {
        return navigationOrder;
    }


}
