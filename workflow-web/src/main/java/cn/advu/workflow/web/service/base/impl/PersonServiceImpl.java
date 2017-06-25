package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BasePersonMapper;
import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.web.service.base.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private BasePersonMapper basePersonMapper;

    @Override
    public int insert(BasePerson obj) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(String id) {
        return 0;
    }

    @Override
    public List<BasePerson> queryAll() {
        return null;
    }

    @Override
    public List<BasePerson> queryByCondition(BasePerson obj) {
        return null;
    }
}
