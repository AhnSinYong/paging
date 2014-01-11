package framewise.page;

import java.io.Serializable;

/**
 * 페이징 처리를 위해 제공하는 웹 공통 파라미터
 *
 * @author chanwook
 */
public class PagingParam implements Serializable {

    private int pageNumber = 1;
    private int pageItemSize = 10;
    private int navigationSize = 5;

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
}
