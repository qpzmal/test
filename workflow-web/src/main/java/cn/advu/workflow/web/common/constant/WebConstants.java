package cn.advu.workflow.web.common.constant;

public class WebConstants {    
    public static final int OPERATION_FAILURE = 0;//操作失败返回值
    public static final int OPERATION_SUCCESS = 1;//操作成功返回值
    public static final String LINE_CHART = "line";//折线图类型
    public static final String BAR_CHART = "bar";//柱状图类型
    public static final int COMMON_PAGESIZE = 10;//通用页大小
    public static final int BIG_PAGESIZE = 50;//广告页大小
    public static final int NO_PAGESIZE = 0;//导出使用
    public static final String SESSION_USER = "account";//session中user的key


    public static final String MD5_SALT = "advu";//md5加盐值
    public static final int ITEM_STATUS_NORMAL = 0;// 状态 ，0正常；1停用；9删除
    public static final int ITEM_STATUS_STOP = 1;// 状态 ，0正常；1停用；9删除
    public static final int ITEM_STATUS_DEL = 9;// 状态 ，0正常；1停用；9删除


    public static final String WORKFLOW_BUY = "buy"; // 年度采购框架合同模型
    public static final String WORKFLOW_BUY_FRAME = "buy_frame";// 合同审批模型
    public static final String WORKFLOW_SALE = "sale"; // 排期执行审批模型
    public static final String WORKFLOW_SALE_FRAME = "sale_frame";// CPM采购成本单模型
    public static final String WORKFLOW_EXECUTE = "execute";// 年度需求单审批模型
}
