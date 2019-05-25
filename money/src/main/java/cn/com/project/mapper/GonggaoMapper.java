package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Gonggao;

@Repository
public interface GonggaoMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Gonggao record);

    void insertSelective(Gonggao record);

    Gonggao selectByPrimaryKey(Integer id);

    List<Gonggao> select(Gonggao record);

    void updateByPrimaryKeySelective(Gonggao record);

    void updateByPrimaryKey(Gonggao record);
}