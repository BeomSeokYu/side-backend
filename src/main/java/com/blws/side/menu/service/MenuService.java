package com.blws.side.menu.service;

import com.blws.side.common.enumerate.BooleanType;
import com.blws.side.common.exception.CustomException;

import com.blws.side.menu.entity.Menu;
import com.blws.side.menu.enumerate.MenuType;
import com.blws.side.menu.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Menu> getMenuAll() {
        List<Menu> rootMenus = menuRepository.findByParentIdAndIsUsedOrderBySortOrder(null, BooleanType.YES.getAlias())
                .orElseThrow(() -> new CustomException("Menu not found", HttpStatus.BAD_REQUEST));
        return rootMenus.stream()
                .map(this::buildMenuTree)
                .collect(Collectors.toList());
    }

    private Menu buildMenuTree(Menu menu) {
        List<Menu> children = menuRepository.findByParentIdAndIsUsedOrderBySortOrder(menu.getId(), BooleanType.YES.getAlias())
                .orElseThrow(() -> new CustomException("Menu not found", HttpStatus.BAD_REQUEST));
        if (!children.isEmpty()) {
            children = children.stream()
                    .map(this::buildMenuTree)
                    .collect(Collectors.toList());
            menu.setChildren(children);
        }
        return menu;
    }

    public List<Menu> getRouteMenuList() {
        return menuRepository.findByMenuTypeAndIsUsedOrderBySortOrder(MenuType.PAGE.getName(), BooleanType.YES.getAlias())
                .orElseThrow(() -> new CustomException("Menu not found", HttpStatus.BAD_REQUEST));
    }

    public List<Menu> getMenuList() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new CustomException("Menu not found", HttpStatus.BAD_REQUEST));
    }

    public Menu createMenu(Menu Menu) {
        return menuRepository.save(Menu);
    }

    @Transactional
    public Menu updateMenu(Long id, Menu Menu) {
        Menu target = menuRepository.findById(id)
                .orElseThrow(() -> new CustomException("Menu not found", HttpStatus.BAD_REQUEST));
        return menuRepository.save(target.update(Menu));
    }

    @Transactional
    public Menu deleteMenu(Long id) {
        Menu Menu = menuRepository.findById(id)
                .orElseThrow(() -> new CustomException("Menu not found", HttpStatus.BAD_REQUEST));
        menuRepository.deleteById(id);
        return Menu;
    }

}
