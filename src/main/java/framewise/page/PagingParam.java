package framewise.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 페이징 처리를 위해 제공하는 웹 공통 파라미터
 *
 * @author chanwook
 */
public class PagingParam implements Serializable {

    private int pageNumber = 1;
    private int pageItemSize = 10;
    private int navigationSize = 5;

    private PagingType type = PagingType.NUMBERS;

    private Map<String, String> templateVariableMap = new HashMap<String, String>();

    public PagingParam() {
    }

    public PagingParam(int pageNumber, int pageItemSize) {
        this.pageNumber = pageNumber;
        this.pageItemSize = pageItemSize;
    }

    public PagingParam(int pageNumber, int pageItemSize, int navigationSize) {
        this.pageNumber = pageNumber;
        this.pageItemSize = pageItemSize;
        this.navigationSize = navigationSize;
    }

    public PagingParam(int pageNumber, int pageItemSize, int navigationSize, PagingType type) {
        this.pageNumber = pageNumber;
        this.pageItemSize = pageItemSize;
        this.navigationSize = navigationSize;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PagingParam that = (PagingParam) o;

        if (navigationSize != that.navigationSize) return false;
        if (pageItemSize != that.pageItemSize) return false;
        if (pageNumber != that.pageNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageNumber;
        result = 31 * result + pageItemSize;
        result = 31 * result + navigationSize;
        return result;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageItemSize() {
        return pageItemSize;
    }

    public int getNavigationSize() {
        return navigationSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageItemSize(int pageItemSize) {
        this.pageItemSize = pageItemSize;
    }

    public void setNavigationSize(int navigationSize) {
        this.navigationSize = navigationSize;
    }

    public PagingType getType() {
        return type;
    }

    public void setType(PagingType type) {
        this.type = type;
    }

    public Map<String, String> getTemplateVariableMap() {
        return templateVariableMap;
    }

    public void setTemplateVariableMap(Map<String, String> templateVariableMap) {
        this.templateVariableMap = templateVariableMap;
    }

    public void addTemplateVariable(String key, String value) {
        this.templateVariableMap.put(key, value);
    }
}
