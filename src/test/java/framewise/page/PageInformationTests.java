package framewise.page;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * 페이지 처리 로직에 대한 테스트 작성
 *
 * @author chanwook
 */
public class PageInformationTests {

    public static final int NAVIGATION_SIZE = 5;
    public static final int TOTAL_PAGE = 12;
    public static final int TOTAL_COUNT = 60;

    @Test
    public void createNavigation() {
        PageInformation p = new PageInformation(1, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(2, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(3, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(4, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(5, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(6, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(7, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(8, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(9, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(10, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(11, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{11, 12}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(false));

        p = new PageInformation(12, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{11, 12}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(false));
    }

    @Test
    public void calcNavigationOrderValue(){
        PageInformation p = new PageInformation(1, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(1));

        p = new PageInformation(2, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(1));

        p = new PageInformation(3, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(1));

        p = new PageInformation(4, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(1));

        p = new PageInformation(5, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(1));

        p = new PageInformation(6, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(2));

        p = new PageInformation(10, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(2));

        p = new PageInformation(11, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(3));

        p = new PageInformation(12, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationOrder(), is(3));

//        p = new PageInformation(33, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
//        assertThat(p.getNavigationOrder(), is(7));
    }

    @Test
    public void beforeNextNavigation(){
        PageInformation p = new PageInformation(3, 5, 30, 6, 1);
        assertThat(p.getNavigationNumber().length, is(1));
        assertThat(p.getNavigationNumber()[0], is(3));
    }
}
