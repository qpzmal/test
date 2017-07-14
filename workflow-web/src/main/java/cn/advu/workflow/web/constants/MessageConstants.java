package cn.advu.workflow.web.constants;

/**
 * Created by wangry on 17/7/13.
 */
public interface MessageConstants {
     String NAME_IS_DUPLICATED = "名称重复";
     String PARAM_IS_ILLEGAL = "参数不合法";
    String IS_NULL_OR_EMPTY = "为空";
    String CUSTOM_IS_NOT_EXISTS="客户不存在";
    String FA_CUSTOM_HAS_CHILD="客户下面有广告主，不能改变客户的类型";
    String FA_CUSTOM_HAS_CHILD_FOR_REMOVE="客户下面有广告主，请先删除客户下的广告主";
    String CUSTOM_TYPE_IS_NULL = "客户类型没有设置";
    String CUSTOM_PARENT_IS_NULL = "签约公司没有设置";
    String CUSTOM_IS_NULL = "签约公司没有设置";
    String START_DATE_IS_NULL = "开始日期没有设置";
    String END_DATE_IS_NULL = "开始日期没有设置";
    String NAME_IS_NULL = "名称为空";
    String CUSTOM_DATE_IS_DUPLICATED = "客户结算日期重复";
}
