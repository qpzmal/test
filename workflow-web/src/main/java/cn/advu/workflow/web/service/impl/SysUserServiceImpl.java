package cn.advu.workflow.web.service.impl;

import cn.advu.workflow.dao.fcf_vu.SysUserMapper;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.SysUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService{

    private static Logger LOGGER = Logger.getLogger(SysUserServiceImpl.class);

//    @Autowired
    private SysUserMapper sysUserMapper;
//
//    @Autowired
//    private SysPermissionMapper permissionMapper;

    @Override
    public ResultJson<Void> add(SysUser user) {
        sysUserMapper.insert(user);
        return null;
    }

//    @Override
//    @Transactional
//    public ResultJson<Object> add(SysUser user, Integer role) {
//        ResultJson<Object> rj = new ResultJson<>();
//        //密码加密
//        user.setPasswd(StrMD5.getInstance().encrypt(user.getPasswd(), WebConstants.MD5_SALT));
//        int result = userMapper.add(user);
//
//        if(result == 0){
//            rj.setCode(WebConstants.OPERATION_FAILURE);
//            return rj;
//        }
//
//        //设定角色
//        userMapper.setRole(user.getUserId(), role, user.getCreatorId());
//        rj.setCode(WebConstants.OPERATION_SUCCESS);
//        return rj;
//    }
//
//    @Override
//    public ResultJson<List<SysUser>> getAll() {
//        ResultJson<List<SysUser>> rj = null;
//        try {
//            //获得有效用户
//            List<SysUser> users = userMapper.getValid();
//            rj = new ResultJson<>();
//            rj.setCode(WebConstants.OPERATION_SUCCESS);
//            rj.setData(users);
//        } catch (Exception e) {
//            LOGGER.error(e);
//            return null;
//        }
//        return rj;
//    }
//
//    @Override
//    public SysUser getById(Integer userId) {
//        return userMapper.getById(userId);
//    }
//
//    @Override
//    public ResultJson<Object> edit(SysUser user, Integer roleId) {
//
//        //如果密码为空不加密
//        if(user.getPasswd() != null && user.getPasswd() != "") {
//            user.setPasswd(StrMD5.getInstance().encrypt(user.getPasswd(), WebConstants.MD5_SALT));
//        }
//        //用户信息修改
//        userMapper.update(user);
//        //判断是否修改权限
//        if( null != roleId ) {
//            userMapper.updateRoleByUserId(user.getUserId(),roleId);
//        }
//        //更改redis中的权限
////        RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClient");
////        List<SysPermission> permissions = permissionMapper.getByUserId(user.getUserId().intValue());
////        JSONArray json = JSONArray.fromObject(permissions);
////        redisClient.set("login_permissions_key_"+user.getUserId().toString(),json.toString());
//
//        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//    }

}
