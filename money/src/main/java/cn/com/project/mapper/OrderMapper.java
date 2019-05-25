package cn.com.project.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.project.domain.Order;

@Repository
public interface OrderMapper {
    void deleteByPrimaryKey(Integer id);

    void insert(Order record);

    void insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectByName(String xm);

    List<Order> select(Order record);

    List<Order> selectTj();

    void updateByPrimaryKeySelective(Order record);

    void updateByPrimaryKey(Order record);
}