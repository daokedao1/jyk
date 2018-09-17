package com.xoa.service.chatList.impl;

import com.xoa.dao.chatDetail.chatDetailMapper;
import com.xoa.dao.chatList.ChatListMapper;
import com.xoa.model.chatDetail.chatDetail;
import com.xoa.model.chatList.ChatList;
import com.xoa.service.chatList.ChatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhuxinyuan on 2018/9/17.
 */
@Service
public class ChatListServiceImpl  implements ChatListService{

    @Autowired
    private ChatListMapper chatListMapper;
    @Autowired
    private chatDetailMapper chatDetailMapper;

    /**
     * 创建作者:   zhuxinyuan
     * 创建日期:   2018.9.17
     * 方法介绍:  查询会话信息
     * @param chatList
     * @return
     */
    @Override
    public List<ChatList> getChatlist(ChatList chatList) {
        if(chatList.page != null && chatList.pageSize != null){
            chatList.beginRow = (chatList.page-1)*chatList.pageSize;
        }
        List<ChatList> list = chatListMapper.getChatlist(chatList);

        for(ChatList chat:list){
            if(chat.getId() != null){
                List<chatDetail> chatDetaillist = chatDetailMapper.selectBySessionId(chat.getId());
            }

        }
        return list;
    }

    @Override
    public Boolean delChatlistById(Integer id) {
        return null;
    }

    @Override
    public Boolean updateChatlist(ChatList chatList) {
        return null;
    }

    @Override
    public Boolean insert(ChatList chatList) {
        return null;
    }

    @Override
    public int count(ChatList chatList) {
        return 0;
    }

    @Override
    public ChatList selectByPrimaryKey(Integer id) {
        return null;
    }
}
