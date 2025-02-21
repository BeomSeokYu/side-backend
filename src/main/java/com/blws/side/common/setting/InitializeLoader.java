package com.blws.side.common.setting;

import com.blws.side.menu.entity.Menu;
import com.blws.side.menu.enumerate.MenuType;
import com.blws.side.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitializeLoader implements CommandLineRunner {

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public void run(String... args) {
        createInitMenu();
    }

    private void createInitMenu() {
        Menu settingMenu = new Menu(null, "설정", "Setting", null, MenuType.CATEGORY.getName(), null, 3L, "mdi-cog-outline", "Y", null, null);
        Menu finalSettingMenu = settingMenu;
        settingMenu = menuRepository.findByMenuCode(finalSettingMenu.getMenuCode()).orElseGet(() -> menuRepository.save(finalSettingMenu));

        List<Menu> menus = List.of(
                new Menu(null, "Home", "Home", null, MenuType.PAGE.getName(), "/home", 1L, "mdi-home-circle", "Y", "DefaultLayout", null),
                new Menu(null, "table", "table", null, MenuType.PAGE.getName(), "/sample/table", 1L, "mdi-cog-outline", "Y", "DefaultLayout", null),
                new Menu(null, "메뉴관리", "menu", settingMenu.getId(), MenuType.PAGE.getName(), "/setting/menu", 2L, "mdi-cog-outline", "Y", "DefaultLayout", null),
                new Menu(null, "권한관리", "auth", settingMenu.getId(), MenuType.PAGE.getName(), "/setting/auth", 3L, "mdi-cog-outline", "Y", "DefaultLayout", null),
                new Menu(null, "코드관리", "code", settingMenu.getId(), MenuType.PAGE.getName(), "/setting/code", 4L, "mdi-cog-outline", "Y", "DefaultLayout", null)
        );
        menuRepository.saveAll(menus.stream()
                .filter(menu -> menuRepository.findByMenuCode(menu.getMenuCode()).isEmpty())
                .toList());
    }
}
