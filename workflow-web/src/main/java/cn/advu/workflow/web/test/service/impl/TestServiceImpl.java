package cn.advu.workflow.web.test.service.impl;

import cn.advu.workflow.dao.database.UserMapper;
import cn.advu.workflow.domain.database.User;
import cn.advu.workflow.web.test.service.TestService;
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
    private UserMapper userMapper;


    @Override
    public User findUser(String param) {

        logger.info("method m() param:" + param);

        User user = userMapper.findUserById(1l);
        return user;
    }
}
