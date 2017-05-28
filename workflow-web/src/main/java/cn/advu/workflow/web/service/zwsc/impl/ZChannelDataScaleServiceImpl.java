package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.dao.database.SysUserMapper;
import cn.advu.workflow.dao.database.ZChannelDataScaleMapper;
import cn.advu.workflow.domain.database.ChannelDataScale;
import cn.advu.workflow.web.service.zwsc.ZChannelDataScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *分成比例
 */
@Service
public class ZChannelDataScaleServiceImpl implements ZChannelDataScaleService {

    @Autowired
    private ZChannelDataScaleMapper zChannelDataScaleMapper;
    @Autowired
    private SysUserMapper userMapper;


    @Override
    public Page getChannelDateScalesPages(String cnid, Page page, String scaleType, Integer userId) {
        //查看当前用户是否为商务用户，获得分配渠道
        String channel = userMapper.selectBusinessChannelByUserId(userId);
        page.setData(zChannelDataScaleMapper.getChannelDataScales(cnid,page.getStart(),page.getPageSize(),scaleType,channel));
        page.setTotal(zChannelDataScaleMapper.getChannelDataScalesCount(cnid,scaleType,channel));
        return page;
    }

    @Override
    public void addChannelScale(ChannelDataScale channelDateScale) {
        zChannelDataScaleMapper.addChannelDataScale(channelDateScale);
    }

    @Override
    public void updateChannelScale(ChannelDataScale channelDateScale) {
        zChannelDataScaleMapper.updateChannelScale(channelDateScale);
    }

    @Override
    public void deleteChannelScale(Integer id) {
        zChannelDataScaleMapper.deleteChannelDataById(id);
    }

    @Override
    public ChannelDataScale getChannelDataScaleById(Integer id) {
        return zChannelDataScaleMapper.getChannelDataScalesById(id);
    }

    @Override
    public ChannelDataScale getChannelShareScale(String date, String date1, String cnid) {
        return zChannelDataScaleMapper.getChannelShareScale(date,date1,cnid);
    }

    @Override
    public ChannelDataScale findExchannelDataScale(ChannelDataScale channelDateScale) {
        return zChannelDataScaleMapper.findExchannelDataScale(channelDateScale);
    }
}
