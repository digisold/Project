package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.mybatis.entity.SystemMenu;

public interface SystemMenuMapper {
	List<SystemMenu> menuList();
}