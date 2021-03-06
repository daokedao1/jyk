package com.xoa.dao.chatList;

import com.xoa.model.chatList.ChatList;
import com.xoa.model.chatList.ChatListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int countByExample(ChatListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int deleteByExample(ChatListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int insert(ChatList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int insertSelective(ChatList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    List<ChatList> getChatlist(ChatList chatList);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    List<ChatList> selectByExample(ChatListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    ChatList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ChatList record, @Param("example") ChatListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") ChatList record, @Param("example") ChatListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ChatList record, @Param("example") ChatListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ChatList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ChatList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chat_list
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ChatList record);
}