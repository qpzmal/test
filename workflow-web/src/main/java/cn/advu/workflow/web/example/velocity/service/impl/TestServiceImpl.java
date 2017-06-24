package cn.advu.workflow.web.example.velocity.service.impl;

import cn.advu.workflow.dao.fcf_vu.SysUserMapper;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.example.velocity.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kai on 16/3/30.
 */
@Service
public class TestServiceImpl implements TestService {

    private final Logger logger = Logger.getLogger(TestServiceImpl.class);
    @Autowired
    private SysUserMapper userMapper;


    @Override
    public SysUser findUser(String param) {

        logger.info("method m() param:" + param);

        SysUser user = userMapper.selectByPrimaryKey(1);
        return user;
    }
}
