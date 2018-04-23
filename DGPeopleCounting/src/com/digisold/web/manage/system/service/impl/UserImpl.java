package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.UserListModel;
import com.digisold.web.manage.system.service.UserIfc;
import com.digisold.web.manage.system.vo.UserListVo;
import com.digisold.web.mybatis.dao.SystemUserMapper;
import com.digisold.web.mybatis.dao.UserStoreMapper;
import com.digisold.web.mybatis.entity.SystemUser;
import com.digisold.web.mybatis.entity.UserStore;

@Service("userService")
public class UserImpl implements UserIfc {

	@Autowired
	SystemUserMapper sysUserMapper;

	@Autowired
	UserStoreMapper userStoreMapper;

	public void addUser(SystemUser user) {
		sysUserMapper.insertSelective(user);
	}

	public void editUser(SystemUser user) {
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

	public SystemUser getUserById(String userId) {
		return sysUserMapper.selectByPrimaryKey(userId);
	}

	public void deleteUser(String userId) {
		sysUserMapper.deleteByPrimaryKey(userId);
	}

	public SystemUser getUserByUsername(String username) {
		return sysUserMapper.getUserByUsername(username);
	}

	public SystemUser login(String username, String password) {
		return sysUserMapper.userLogin(username, password);
	}

	public long checkUsername(SystemUser user) {
		return sysUserMapper.checkUsername(user);
	}

	public long countUserList() {
		return sysUserMapper.countUserList();
	}

	public List<UserListVo> userList(UserListModel listModel) {
		return sysUserMapper.userList(listModel);
	}

	public int resetPassage(String userId, String password) {
		return sysUserMapper.resetPassage(userId, password);
	}

	public List<UserStore> userStoreList(String userId) {
		return userStoreMapper.userStoreList(userId);
	}

	public void saveUserStore(String userId, String[] storeItem) {
		if (storeItem == null) {
			storeItem = new String[0];
		}
		List<UserStore> userStoreList = userStoreMapper.userStoreList(userId);
		// 删除不存在的
		for (UserStore us : userStoreList) {
			String sid = us.getStoreId();
			boolean flag = false;
			for (int i = 0; i < storeItem.length; i++) {
				if (sid.equals(storeItem[i])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				userStoreMapper.deleteByPrimaryKey(us.getId());
			}
		}
		// 是否已经存在
		for (int i = 0; i < storeItem.length; i++) {
			String sid = storeItem[i];
			boolean flag = false;
			for (UserStore us : userStoreList) {
				if (sid.equals(us.getStoreId())) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				UserStore us = new UserStore();
				{
					us.setUserId(userId);
					us.setStoreId(storeItem[i]);
				}
				userStoreMapper.insertSelective(us);
			}
		}
	}

}
