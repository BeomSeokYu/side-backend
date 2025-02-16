package com.blws.side.menu.repository;

import com.blws.side.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<List<Menu>> findByParentIdAndIsUsedOrderBySortOrder(Long parentId, String isUsed);

    Optional<List<Menu>> findByMenuTypeAndIsUsedOrderBySortOrder(String menuType, String isUsed);

    Optional<Menu> findByMenuCode(String menuCode);

}
