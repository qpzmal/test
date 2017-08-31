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


    public static final String WORKFLOW_BUY = "buyOrder"; // 单独采购审批
    public static final String WORKFLOW_BUY_FRAME = "buyFrame";// 框架采购审批模型
    public static final String WORKFLOW_SALE_ORDER = "saleOrder";// 单独销售审批模型/排期执行审批模型
    public static final String WORKFLOW_SALE_EXECUTE = "saleExecute"; // 排期执行审批模型
    public static final String WORKFLOW_SALE_FRAME = "saleFrame";// 框架销售审批模型

    public static class Audit {
        public static final String MEDIA = "mediaAudit"; // 媒介主管审核
        public static final String SALER_DM = "salerDMAudit"; // 销售主管审核
        public static final String SALER_GM = "salerGMAudit"; // 销售总经理审核
        public static final String FINANCIAL_GM = "financialGMAudit"; // 财务主管审核
        public static final String LEGAL_GM = "legalGMAudit"; // 法务主管审核
        public static final String MODIFY_APPLY = "modifyApply"; // 申请人调整申请

        public static final String SIGN_CONTRACT = "signContract"; // 签署合同
        public static final String UPLOAD_CONTRACT_IMG = "uploadContractImg"; // 上传扫描版合同
        public static final String ORIGINAL_CONTRACT = "originalContract"; // 追要原章合同
        public static final String UPLOAD_EXECUTE_ORDER_IMG = "uploadExecuteOrderImg"; // 上传扫描版排期单
        public static final String ORIGINAL_EXECUTE_ORDER = "originalExecuteOrder"; // 追要原章排期单
        public static final String CONFIRM_COST = "confirmCost"; // 成本确认
        public static final String REMINDER_PAYMENT = "reminderPayment"; // 财务向销售催款
        public static final String GATHERING = "gathering"; // 催款
    }

    public static class AuditPass {
        public static final String MEDIA = "mediaGMPass"; // 媒介主管审核
        public static final String SALER_DM = "salerDMPass"; // 销售主管审核
        public static final String SALER_GM = "salerGMPass"; // 销售总经理审核
        public static final String FINANCIAL_GM = "financialGMPass"; // 财务主管审核
        public static final String LEGAL_GM = "legalGMPass"; // 法务主管审核
        public static final String MODIFY_APPLY = "reApply"; // 申请人调整申请
    }

    public static class WorkFlow {
        public static final String SAVE = "save"; // 保存草稿
        public static final String START = "start"; // 发起流程
        public static final Byte STATUS_NEG_1 = -1; // -1，审核前(未触发流程，类似于草稿)；
        public static final Byte STATUS_0 = 0; // 0，审核中（流程启动中，但尚未审核通过）；
        public static final Byte STATUS_1 = 1; // 1，未开始（审核流程通过，但未投放）；
        public static final Byte STATUS_2 = 2; // 2，进行中（开始投放）；
        public static final Byte STATUS_3 = 3; // 3，已结束（投放结束）
    }

    public static class Notify {
        public static final String TEMPLATE_DEMAND = "demand"; // 审核
        public static final String TEMPLATE_REMIND = "remind"; // 催款提醒
    }

    public static class FileUpload {
        public static final String BIZ_TYPE_1 = "1"; // 1合同图片，
        public static final String BIZ_TYPE_2 = "2"; // 2排期单图片
    }

    public static class ActivitiRole {
        public static final String SALER = "saler"; // 销售员工
        public static final String MEDIA = "media"; // 媒介员工
        public static final String MEDIA_GM = "mediaGM"; // 媒介主管
        public static final String SALER_DM = "salerDM"; // 销售主管
        public static final String SALER_GM = "salerGM"; // 销售总经理
        public static final String FINANCIAL_GM = "financialGM"; // 财务主管
        public static final String LEGAL_GM = "legalGM"; // 法务主管
    }
}
