package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.ChannelDataScale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZChannelDataScaleMapper extends ISqlMapper {

    /**
     * 获取中文书城分成比例
     *
     * @return
     */
    public List<ChannelDataScale> getChannelDataScales(@Param("cnid") String cnid, @Param("start") int start,
                                                       @Param("pageSize") int pageSize, @Param("scaleType") String scaleType,
                                                       @Param("channel") String channel);

    public Integer getChannelDataScalesCount(@Param("cnid") String cnid, @Param("scaleType") String scaleType,
                                             @Param("channel") String channel);

    /**
     * 添加渠道分成比例
     */
    public void addChannelDataScale(@Param("channelDataScale") ChannelDataScale channelDataScale);

    ChannelDataScale getChannelDataScalesById(@Param("id") Integer id);

    void deleteChannelDataById(Integer id);

    void updateChannelScale(ChannelDataScale channelDateScale);

    ChannelDataScale getChannelShareScale(@Param("date") String date, @Param("date1") String date1, @Param("cnid") String cnid);

    ChannelDataScale findExchannelDataScale(ChannelDataScale channelDateScale);
}
