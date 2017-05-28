package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.dao.database.SysUserMapper;
import cn.advu.workflow.dao.database.ZChannelUserMapper;
import cn.advu.workflow.domain.database.ChannelUser;
import cn.advu.workflow.web.service.zwsc.ZChannelUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *分成比例
 */
@Service
public class ZChannelUserServiceImpl implements ZChannelUserService {

    @Autowired
    private ZChannelUserMapper zChannelUserMapper;
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public Page getChannelUserPages(String cnid, Page page, String userType, Integer userId) {
        //查看当前用户是否为商务用户，获得分配渠道
        String channel = userMapper.selectBusinessChannelByUserId(userId);
        if(StringUtils.isNotBlank(channel)){
            if(StringUtils.isNotBlank(cnid)){
                cnid = cnid +","+channel;
            }else{
                cnid = channel;
            }
        }
        Integer userCount = 0;//分页总条数
        List<ChannelUser> newList = new ArrayList<ChannelUser>();
        List<ChannelUser> channelUsers = new ArrayList<ChannelUser>();
        if(StringUtils.isNotBlank(cnid)){
            String[] split = cnid.split(",");
            for(String cid : split){
                List<ChannelUser> channelUser = zChannelUserMapper.getChannelUser(cid, page.getStart(), page.getPageSize(), userType);
                Integer count = zChannelUserMapper.getChannelUserCount(cnid, userType);
                userCount += count;
                channelUsers.addAll(channelUser);
            }
            //去除重复
            for(ChannelUser u : channelUsers ){
                boolean flage = true;
                for(ChannelUser n : newList){
                    if(n.getId() == u.getId()){
                        flage = false;
                    }
                }
                if(flage){
                    newList.add(u);
                }
            }

        }else{
            newList = zChannelUserMapper.getChannelUser(cnid, page.getStart(), page.getPageSize(), userType);
            userCount = zChannelUserMapper.getChannelUserCount(cnid, userType);
        }

        page.setData(newList);
        page.setTotal(userCount);
        return page;
    }

    @Override
    public Page getChannelUserListPages(String cnid, String userName, String nickName, Page page, String userType, Integer userId) {
        //查看当前用户是否为商务用户，获得分配渠道
        String channel = userMapper.selectBusinessChannelByUserId(userId);
        if(StringUtils.isNotBlank(channel)){
            if(StringUtils.isNotBlank(cnid)){
                cnid = cnid +","+channel;
            }else{
                cnid = channel;
            }
        }
        Integer userCount = 0;//分页总条数
        List<ChannelUser> newList = new ArrayList<ChannelUser>();
        List<ChannelUser> channelUsers = new ArrayList<ChannelUser>();
        if(StringUtils.isNotBlank(cnid)){
            String[] split = cnid.split(",");
            for(String cid : split){
                List<ChannelUser> channelUser = zChannelUserMapper.getChannelUsers(cid,userName,nickName, page.getStart(), page.getPageSize(), userType);
                Integer count = zChannelUserMapper.getChannelUsersCount(cnid,userName,nickName,userType);
                userCount += count;
                channelUsers.addAll(channelUser);
            }
            //去除重复
            for(ChannelUser u : channelUsers ){
                boolean flage = true;
                for(ChannelUser n : newList){
                    if(n.getId() == u.getId()){
                        flage = false;
                    }
                }
                if(flage){
                    newList.add(u);
                }
            }

        }else{
            newList = zChannelUserMapper.getChannelUsers(cnid,userName,nickName, page.getStart(), page.getPageSize(), userType);
            userCount = zChannelUserMapper.getChannelUsersCount(cnid,userName,nickName, userType);
        }

        page.setData(newList);
        page.setTotal(userCount);
        return page;
    }

    @Override
    public void addChannelUser(ChannelUser channelUser) {
        zChannelUserMapper.addChannelUser(channelUser);
    }

    @Override
    public void updateChannelUser(ChannelUser channelUser) {
        zChannelUserMapper.updateChannelUser(channelUser);
    }

    @Override
    public void deleteChannelUser(Integer id) {
        zChannelUserMapper.deleteChannelUserById(id);
    }

    @Override
    public ChannelUser getChannelUserById(Integer id) {
        return zChannelUserMapper.getChannelUserById(id);
    }

    @Override
    public ChannelUser getChannelUserByNameAndPass(String userName, String password) {
        return zChannelUserMapper.getChannelUserByNameAndPass(userName,password);
    }

}
