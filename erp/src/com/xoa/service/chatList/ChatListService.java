package com.xoa.service.chatList;

import com.xoa.model.chatList.ChatList;

import java.util.List;

/**
 * Created by zhuxinyuan on 2018/9/17.
 */
public interface ChatListService {

    //获取会话信息
    List<ChatList> getChatlist(ChatList chatList);
    //根据id删除
    Boolean delChatlistById(Integer id);
    //修改会话信息
    Boolean updateChatlist(ChatList chatList);
    //插入
    Boolean insert(ChatList chatList);
    //返回总数
    int count(ChatList chatList);
    //查询详情
    ChatList selectByPrimaryKey(Integer id);

}
