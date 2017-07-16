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
    String START_DATE_AFTER_END_DATE = "开始日期晚于结束日期";
    String NAME_IS_NULL = "名称为空";
    String DATE_IS_DUPLICATED = "客户结算日期重复";
    String AREA_IS_NOT_EXISTS="区域不存在";
    // 部门
    String ASSIGN_AREA_IS_NULL = "所属区域没有设置";
    String DEPT_IS_NOT_EXISTS = "部门不存在";

    // 角色
    String ROLE_NAME_IS_NULL = "角色名称没有设置";
    String ROLE_NAME_IS_DUPLICATED = "角色名称重复";
    String ROLE_IS_USING = "有用户正在使用该角色，请先删除用户下的角色";
    String ROLE_IS_NOT_EXISTS = "角色不存在";
    // 用户
    String USER_NAME_IS_DUPLICATED = "用户登录名称重复";
}
