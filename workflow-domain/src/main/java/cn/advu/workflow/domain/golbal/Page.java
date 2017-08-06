package cn.advu.workflow.domain.golbal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hekai
 * 
 */
public class Page {
	public static final int DEFAUTLPAGESIZE = 10;
	public static final int DEFAUTL_APP_PAGESIZE = 20; // 移动端默认每页件数
	public static final int[] OPTIONALPAGESIZE = { 10,20,30,50,100,200,300,500};
	private int total = 0;
	private int pageSize = DEFAUTLPAGESIZE;
	private List<? extends Object> data = new ArrayList<Object>();
	private Integer pageIndex = 1;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {

		for (int size : OPTIONALPAGESIZE) {
			if (size == pageSize) {
				return size;
			}
		}

		return DEFAUTLPAGESIZE;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize == null ? DEFAUTLPAGESIZE:pageSize;
	}

	public List<? extends Object> getData() {
		return data;
	}

	public void setData(List<? extends Object> data) {
		this.data = data;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex == null ? 1 : pageIndex;
	}

	/**
	 * start by 0   先查总数，再查list
	 * 
	 * @return
	 */
	public int getStartIndex() {
		return total == 0 || pageIndex == 1 ? 0 : (pageIndex - 1) * pageSize;
	}
	public int getStart() {
		return  pageIndex == 1 ? 0 : (pageIndex - 1) * pageSize;
	}

	public int getEndIndex() {
		return getStartIndex() + pageSize;
	}

	public int getPageTotal() {
		
		if(this.total/pageSize == 0){
			return 1;
		}else if(this.total%pageSize == 0){
			return total/pageSize ;
		}else{
			return total/pageSize +1 ;
		}
	}

	/**
	 * 系统默认支持的不同pageSize的分页策略
	 * 
	 * @return
	 */
	public int[] getOptionalPageSize() {
		return OPTIONALPAGESIZE;
	}
	

	@Override
	public String toString() {
		return "Page{" +
				"total=" + total +
				", pageSize=" + pageSize +
				", data=" + data +
				", pageIndex=" + pageIndex +
				'}';
	}
}
