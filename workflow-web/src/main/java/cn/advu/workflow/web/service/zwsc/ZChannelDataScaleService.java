package cn.advu.workflow.web.service.zwsc;


import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.domain.database.ChannelDataScale;

/**
 * Created by zhanglei on 2017/2/22.
 */
public interface ZChannelDataScaleService {



    public Page getChannelDateScalesPages(String cnid, Page page, String scaleType, Integer userId);

    void addChannelScale(ChannelDataScale channelDateScale);

    void updateChannelScale(ChannelDataScale channelDateScale);

    void deleteChannelScale(Integer id);

    ChannelDataScale getChannelDataScaleById(Integer id);

    ChannelDataScale getChannelShareScale(String date, String date1, String cnid);

    ChannelDataScale findExchannelDataScale(ChannelDataScale channelDateScale);
}
