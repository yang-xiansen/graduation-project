package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Lxr;

@Repository
public interface LxrMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Lxr record);

    void insertSelective(Lxr record);

    Lxr selectByPrimaryKey(Integer id);

    List<Lxr> select(Lxr record);

    void updateByPrimaryKeySelective(Lxr record);

    void updateByPrimaryKey(Lxr record);
}