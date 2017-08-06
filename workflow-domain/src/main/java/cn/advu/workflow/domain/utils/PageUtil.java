package cn.advu.workflow.domain.utils;


import cn.advu.workflow.domain.golbal.Page;

/**
 * Created by weiqz on 2017/3/1.
 */
public class PageUtil {

    public static Page initPage(int pageIndex, int pageSize) {
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        return page;
    }

    public static Page initAppPage(int pageIndex) {
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(Page.DEFAUTL_APP_PAGESIZE);
        return page;
    }

}
