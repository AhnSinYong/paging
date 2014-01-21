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

    private int pageNumber;
    private int pageSize;
    private long totalCount;
    private int totalPage;
    private Integer[] navigationNumber;
    private boolean enablePrevious = false;
    private boolean enableNext = false;
    private int numberGroupCount;

    private Map<String, String> templateVariableMap = new HashMap<String, String>();

    /**
     * 기본 생성자
     */
    public PageInformation() {
    }

    /**
     * 실제 필요한 데이터를 인자로 받아서 인스턴스를 생성하는 생성자
     *
     * @param pageNumber     전체 페이지 중 현재 페이지 수
     * @param pageSize       한 페이지에서 담는 아이템 수
     * @param totalCount     전체 아이템 수
     * @param totalPage      전체 페이지 수
     * @param navigationSize 페이지 수로 페이징 처리 하는 경우에 한 화면에 보여주는 페이지의 수
     */
    public PageInformation(int pageNumber, int pageSize, long totalCount, int totalPage, int navigationSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;

        createPageNavigation(navigationSize);
    }

    public PageInformation(PagingParam param, long totalCount, int totalPage) {
        this(param.getPageNumber(), param.getPageItemSize(), totalCount, totalPage, param.getNavigationSize());

        this.templateVariableMap = param.getTemplateVariableMap();
    }

    /**
     * 페이지 네비게이션을 위한 정보를 생성
     *
     * @param navigationSize
     */
    public void createPageNavigation(int navigationSize) {
        if (navigationSize < 1) {
            logger.debug("Navigation Size가 1보다 작기 때문에 Navigation bar 계산 로직을 수행하지 않습니다.(요청 size:" + navigationSize + ")");
            return;
        }

        int navigationCount = calNavigationCount(navigationSize);

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i <= navigationSize; i++) {
            if (i == navigationCount) {
                addIfPossible(list, pageNumber);
            } else if (i > navigationCount) {
                addIfPossible(list, pageNumber + (i - navigationCount));
            } else if (i < navigationCount) {
                addIfPossible(list, pageNumber - (navigationCount - i));
            }
        }
        setNumberGroupCount(navigationCount);
        this.navigationNumber = list.toArray(new Integer[list.size()]);

        // 이전버튼 활성화 처리 여부
        if (this.navigationNumber[0] > navigationSize) {
            this.enablePrevious = true;
        }

        // 다음버튼 활성화 처리 여부
        if (this.navigationNumber.length == navigationSize && this.navigationNumber[4] < this.totalPage) {
            this.enableNext = true;
        }
    }

    private int calNavigationCount(int navigationSize) {
        int count = this.pageNumber % navigationSize;
        if (0 == count) {
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
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

    public int getNumberGroupCount() {
        return numberGroupCount;
    }

    public void setNumberGroupCount(int numberSetCount) {
        this.numberGroupCount = numberSetCount;
    }

    public Map<String, String> getTemplateVariableMap() {
        return templateVariableMap;
    }

    public void setTemplateVariableMap(Map<String, String> templateVariableMap) {
        this.templateVariableMap = templateVariableMap;
    }
}
