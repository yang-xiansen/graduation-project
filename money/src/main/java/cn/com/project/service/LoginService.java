package cn.com.project.service;

import java.util.List;

import cn.com.project.domain.Log;
import cn.com.project.domain.User;

public interface LoginService {
   public User login(String account,String password) throws Exception;

    void deleteByLg(Integer id);

    void insertSelective(Log record);

    List<Log> select(Log record);

}
