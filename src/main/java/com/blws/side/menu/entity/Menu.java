package com.blws.side.menu.entity;

import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Table(name = "TB_MENU")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "memu_name")
    private String memuName;

    @Column(name = "memu_code", unique = true, nullable = false)
    private String menuCode;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "memu_type")
    private String menuType;

    @Column(name = "url")
    private String url;

    @Column(name = "sort_order")
    private Long sortOrder;

    @Column(name = "icon_class")
    private String iconClass;

    @Column(name = "is_used")
    private String isUsed;

    @Column(name = "layout_type")
    private String layoutType;

    @Setter
    @Transient
    private List<Menu> children;

    public Menu update(Menu menu) {
        this.id = menu.getId();
        this.memuName = menu.getMemuName();
        this.menuCode = menu.getMenuCode();
        this.parentId = menu.getParentId();
        this.menuType = menu.getMenuType();
        this.url = menu.getUrl();
        this.sortOrder = menu.getSortOrder();
        this.iconClass = menu.getIconClass();
        this.isUsed = menu.getIsUsed();
        this.layoutType = menu.getLayoutType();
        return this;
    }
}
