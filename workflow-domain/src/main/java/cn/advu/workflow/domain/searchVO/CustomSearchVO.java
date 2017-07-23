package cn.advu.workflow.domain.searchVO;


/**
 * 查询条件用
 */
public class CustomSearchVO {

    private String likeSearch;

    private Integer customType;

    private Integer parentId;


    public String getLikeSearch() {
        return likeSearch;
    }

    public void setLikeSearch(String likeSearch) {
        this.likeSearch = likeSearch;
    }

    public Integer getCustomType() {
        return customType;
    }

    public void setCustomType(Integer customType) {
        this.customType = customType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}