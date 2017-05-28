package cn.advu.workflow.web.service.zwsc;


import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.domain.database.ChannelUser;

/**
 *
 */
public interface ZChannelUserService {



    public Page getChannelUserPages(String cnid, Page page, String userType, Integer userId);

    public Page getChannelUserListPages(String cnid, String userName, String nickName, Page page, String userType, Integer userId);

    void addChannelUser(ChannelUser channelUser);

    void updateChannelUser(ChannelUser channelUser);

    void deleteChannelUser(Integer id);

    ChannelUser getChannelUserById(Integer id);

    ChannelUser getChannelUserByNameAndPass(String userName, String password);

}
