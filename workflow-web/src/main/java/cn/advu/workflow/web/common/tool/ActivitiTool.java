package cn.advu.workflow.web.common.tool;

import cn.advu.workflow.web.common.constant.WebConstants;

import java.util.List;

/**
 * Created by weiqz on 2017/8/28.
 */
public class ActivitiTool {


    /**
     * 校验是否能查看申请
     * @param sysRoleList
     * @return
     */
    public static boolean checkViewAuth(List<String> sysRoleList){
        if (sysRoleList.contains(WebConstants.ActivitiRole.FINANCIAL_GM)
                || sysRoleList.contains(WebConstants.ActivitiRole.LEGAL_GM) ) { // 财务、法务可以看到全部申请
            return true;

        } else if (sysRoleList.contains(WebConstants.ActivitiRole.MEDIA_GM)
                || sysRoleList.contains(WebConstants.ActivitiRole.SALER_DM)
                || sysRoleList.contains(WebConstants.ActivitiRole.SALER_GM) ) { // 媒介主管、销售主管、销售总经理可以看到本公司下的申请
            return true;

        } else { // 其他人只能看到自己的申请
            return false;
        }
    }
}
