package cn.com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.project.domain.User;
import cn.com.project.mapper.UserMapper;
import cn.com.project.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void insert(User record) {
		userMapper.insert(record);
	}
	public void deleteByPrimaryKey(Integer id) {
		userMapper.deleteByPrimaryKey(id);
		
	}
	public User selectByPrimaryKey(Integer id) {
		User u = userMapper.selectByPrimaryKey(id);
		return u;
	}
	public List<User> selectUserList(User u) {
		List<User> selectUserList = userMapper.selectUserList(u);
		return selectUserList;
	}
	public void updateByPrimaryKey(User record) {
        userMapper.updateByPrimaryKeySelective(record);
	}
	public void agree(User u) {
		userMapper.agree(u);
	}
	public void updatePassword(User record) {
		userMapper.updatePassword(record);
	}

}
