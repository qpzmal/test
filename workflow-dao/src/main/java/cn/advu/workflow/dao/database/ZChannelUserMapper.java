package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.ChannelUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZChannelUserMapper extends ISqlMapper {

    /**
     * 获取中文书城分成比例
     *
     * @return
     */
    public List<ChannelUser> getChannelUser(@Param("cnid") String cnid, @Param("start") int start,
                                            @Param("pageSize") int pageSize, @Param("userType") String userType);

    public Integer getChannelUserCount(@Param("cnid") String cnid, @Param("userType") String userType);

    /**
     * 添加渠道分成比例
     */
    public void addChannelUser(@Param("channelUser") ChannelUser channelUser);

    ChannelUser getChannelUserById(Integer id);

    void deleteChannelUserById(@Param("id") Integer id);

    void updateChannelUser(ChannelUser channelUser);

    ChannelUser getChannelUserByNameAndPass(@Param("userName") String userName, @Param("password") String password);

    List<ChannelUser> getChannelUsers(@Param("cnid") String cnid, @Param("userName") String userName, @Param("nickName") String nickName, @Param("start") int start, @Param("pageSize") int pageSize, @Param("userType") String userType);

    Integer getChannelUsersCount(@Param("cnid") String cnid, @Param("userName") String userName, @Param("nickName") String nickName, @Param("userType") String userType);
}
