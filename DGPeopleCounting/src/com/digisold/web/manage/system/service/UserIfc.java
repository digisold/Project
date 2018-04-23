package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.manage.system.model.UserListModel;
import com.digisold.web.manage.system.vo.UserListVo;
import com.digisold.web.mybatis.entity.SystemUser;
import com.digisold.web.mybatis.entity.UserStore;

public interface UserIfc {

	public void addUser(SystemUser user);

	public void editUser(SystemUser user);

	public void deleteUser(String userId);

	public SystemUser getUserById(String userId);

	public SystemUser getUserByUsername(String username);

	public SystemUser login(String username, String password);

	public long checkUsername(SystemUser user);

	public long countUserList();

	public List<UserListVo> userList(UserListModel listModel);

	public int resetPassage(String userId, String password);

	public List<UserStore> userStoreList(String userId);

	public void saveUserStore(String userId, String[] storeItem);

}
