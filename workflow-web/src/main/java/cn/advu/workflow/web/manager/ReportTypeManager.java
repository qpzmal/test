package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseReportType;
import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.web.dto.system.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class ReportTypeManager {

//    private  static List<SysRole> roleList
//    /**
//     * 初始化用户角色列表
//     *
//     * @param userId
//     * @return
//     */
//    private List<UserRole> initUserRoleList(String reportIds) {
//
//        List<UserRole> userRoleList = new ArrayList<>();
//        List<SysUserRole> sysUserRoleList = baseReportType.findUserRoleAll(userId).getData();
//
//        List<SysRole> roleList = sysRoleService.findAll().getData();
//
//        for (SysRole sysRole : roleList) {
//            UserRole userRole = new UserRole();
//            userRole.setSysRole(sysRole);
//            // 设置当前用户是否拥有此角色
//            for (SysUserRole sysUserRole : sysUserRoleList) {
//                if (sysRole.getId().equals(sysUserRole.getRoles())) {
//                    userRole.setSelected(true);
//                    break;
//                }
//            }
//            userRoleList.add(userRole);
//        }
//
//        return userRoleList;
//    }
}
