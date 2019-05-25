package cn.com.project.service;

import java.util.List;

import cn.com.project.domain.Gonggao;
import cn.com.project.domain.Lxr;
import cn.com.project.domain.Message;
import cn.com.project.domain.Order;
import cn.com.project.domain.Qtgs;

public interface CommService {


    List<Order> selectByName(String xm);

    void deleteByGg(Integer id);

    void insertSelective(Gonggao record);

    Gonggao selectByGg(Integer id);

    List<Gonggao> select(Gonggao record);

    void updateByPrimaryKeySelective(Gonggao record);

    void deleteByXx(Integer id);

    void insertSelective(Message record);

    Message selectByXx(Integer id);

    List<Message> select(Message record);

    void updateByPrimaryKeySelective(Message record);


    void deleteByLx(Integer id);

    void insertSelective(Lxr record);

    Lxr selectByLx(Integer id);

    List<Lxr> select(Lxr record);

    void updateByPrimaryKeySelective(Lxr record);

    void deleteByDd(Integer id);

    void insertSelective(Order record);

    Order selectByDd(Integer id);

    List<Order> select(Order record);

    void updateByPrimaryKeySelective(Order record);


    List<Order> selectDdTj();

    void deleteByPrimaryKey(Integer id);

    void insertSelective(Qtgs record);

    Qtgs selectByPrimaryKey(Integer id);

    List<Qtgs> select(Qtgs record);

    void updateByPrimaryKeySelective(Qtgs record);

}
