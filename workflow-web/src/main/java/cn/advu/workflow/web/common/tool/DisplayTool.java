package cn.advu.workflow.web.common.tool;

import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by weiqz on 2017/8/12.
 */
public class DisplayTool {

    /**
     * 判断用户是否有buttonValue的权限，如果有，以buttonKey为键值，放入resultModel
     * @param resultModel
     * @param buttonKey
     * @param buttonValue
     */
    public static void buttonDisplay(Model resultModel, String buttonKey, String buttonValue) {
        List<String> functionList = UserThreadLocalContext.getCurrentUser().getUserFunction();

        if (functionList.contains(buttonValue)) {
            resultModel.addAttribute(buttonKey, "0");
        }
    }

}
