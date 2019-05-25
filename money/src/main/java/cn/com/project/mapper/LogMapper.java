package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Log;

@Repository
public interface LogMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Log record);

    void insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    List<Log> select(Log record);

    void updateByPrimaryKeySelective(Log record);

    void updateByPrimaryKey(Log record);
}