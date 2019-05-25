package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Message;

@Repository
public interface MessageMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Message record);

    void insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> select(Message record);

    void updateByPrimaryKeySelective(Message record);

    void updateByPrimaryKey(Message record);
}