package cn.com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.project.domain.Gonggao;
import cn.com.project.domain.Lxr;
import cn.com.project.domain.Message;
import cn.com.project.domain.Order;
import cn.com.project.domain.Qtgs;
import cn.com.project.mapper.GonggaoMapper;
import cn.com.project.mapper.LxrMapper;
import cn.com.project.mapper.MessageMapper;
import cn.com.project.mapper.OrderMapper;
import cn.com.project.mapper.QtgsMapper;
import cn.com.project.service.CommService;

@Service
public class CommServiceImpl implements CommService {

    @Autowired
    private GonggaoMapper gonggaoMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private LxrMapper lxrMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private QtgsMapper qtgsMapper;

    @Override
    public void deleteByGg(Integer id) {
        gonggaoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Gonggao record) {
        gonggaoMapper.insertSelective(record);
    }

    @Override
    public Gonggao selectByGg(Integer id) {
        return gonggaoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Gonggao> select(Gonggao record) {
        return gonggaoMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Gonggao record) {
        gonggaoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByXx(Integer id) {
        messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Message record) {
        messageMapper.insertSelective(record);
    }

    @Override
    public Message selectByXx(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Message> select(Message record) {
        return messageMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Message record) {
        messageMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public void deleteByLx(Integer id) {
        lxrMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Lxr record) {
        lxrMapper.insertSelective(record);
    }

    @Override
    public Lxr selectByLx(Integer id) {
        return lxrMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Lxr> select(Lxr record) {
        return lxrMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Lxr record) {
        lxrMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByDd(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Order record) {
        orderMapper.insertSelective(record);
    }

    @Override
    public Order selectByDd(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> select(Order record) {
        return orderMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Order record) {
        orderMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public List<Order> selectDdTj() {
        return orderMapper.selectTj();
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        qtgsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertSelective(Qtgs record) {
        qtgsMapper.insertSelective(record);
    }

    @Override
    public Qtgs selectByPrimaryKey(Integer id) {
        return qtgsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Qtgs> select(Qtgs record) {
        return qtgsMapper.select(record);
    }

    @Override
    public void updateByPrimaryKeySelective(Qtgs record) {
        qtgsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Order> selectByName(String xm){
        return orderMapper.selectByName(xm);
    }

}
