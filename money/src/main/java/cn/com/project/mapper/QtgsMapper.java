package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Qtgs;

@Repository
public interface QtgsMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Qtgs record);

    void insertSelective(Qtgs record);

    Qtgs selectByPrimaryKey(Integer id);

    List<Qtgs> select(Qtgs record);

    void updateByPrimaryKeySelective(Qtgs record);

    void updateByPrimaryKey(Qtgs record);
}