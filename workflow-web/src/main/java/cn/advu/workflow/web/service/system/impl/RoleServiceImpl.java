package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.web.service.system.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
//
//    @Autowired
//    private SysRoleMapper roleMapper;
//
//    @Autowired
//    private SysPermissionMapper permissionMapper;
//
//    @Override
//    public ResultJson<List<Map<String, Object>>> getAllRoleAndPers() {
//        List<Map<String, Object>> data = new ArrayList<>();
//        List<SysRole> roles = roleMapper.getAll();//查询所有角色
//        for(SysRole role:roles){
//            List<SysPermission> permissions =
//                    permissionMapper.getByRoleId(role.getRoleId());//根据角色id查询该角色权限
//            Map<String, Object> roleAndPerms = new HashMap<>();//角色权限存放到map中
//            roleAndPerms.put("role", role);
//            roleAndPerms.put("perms", permissions);
//            data.add(roleAndPerms);
//        }
//        ResultJson<List<Map<String, Object>>> rj =
//                new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//        rj.setData(data);
//        return rj;
//    }
//
//    @Override
//    @Transactional
//    public ResultJson<Object> addRole(Integer creatorId, String roleName, String[] permissionIds) {
//        SysRole role = new SysRole();
//        role.setRoleName(roleName);
//        int result = roleMapper.add(role);//添加角色
//        if(result != 1){
//            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建角色失败!");
//        }
//        for(String pid:permissionIds){
//            permissionMapper.addPermissionForRole(creatorId, role.getRoleId(), pid);
//        }
//        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//    }
//
//    @Override
//    public ResultJson<List<SysPermission>> getAllPermissions() {
//        List<SysPermission> permissions = permissionMapper.getAll();
//        ResultJson<List<SysPermission>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//        rj.setData(permissions);
//        return rj;
//    }
//
//    @Override
//    public List<SysPermission> getPermissionsOf(Integer roleId) {
//        List<SysPermission> perms = permissionMapper.getByRoleId(roleId);
//        return perms;
//    }
//
//    @Override
//    @Transactional
//    public ResultJson<Object> editRole(Integer creatorId, String roleName, String[] permissionIds) {
//        Integer roleId = roleMapper.getIdByName(roleName);
//        //删除原有的权限
//        roleMapper.clearPermissionsByName(roleId);
//        //赋予新权限
//        for(String pid:permissionIds){
//            permissionMapper.addPermissionForRole(creatorId, roleId, pid);
//        }
//
//        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//    }
//
//    @Override
//    public ResultJson<List<SysRole>> getAll() {
//        List<SysRole> roles = roleMapper.getAll();
//        ResultJson<List<SysRole>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//        rj.setData(roles);
//        return rj;
//    }

}
