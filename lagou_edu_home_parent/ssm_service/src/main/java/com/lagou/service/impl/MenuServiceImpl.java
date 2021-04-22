package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> findAllMenu(MenuVo menuVo) {

        PageHelper.startPage(menuVo.getCurrentPage(), menuVo.getPageSize());
        List<Menu> menuList = menuMapper.findAllMenu();

        return new PageInfo<Menu>(menuList);
    }

    @Override
    public Menu findMenuById(int id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        return menuMapper.findSubMenuListByPid(pid);
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        if( menu.getParentId() == -1){
            menu.setLevel(0); //父级菜单为 0
        }else{
            menu.setLevel(1); //子菜单为 1
        }

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        menuMapper.updateMenu(menu);
    }

    @Override
    public List<Menu> findAllMenu2() {
        return menuMapper.findAllMenu();
    }

}
