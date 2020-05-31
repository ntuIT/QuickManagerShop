package com.qman.web.masterdata.glossary;

import java.util.Map;

import com.qman.web.constant.AuthRoute;
import com.qman.web.orm.PersistentEnum;
import com.qman.web.orm.PersistentEnums;

public enum SideBarRouteEnum implements PersistentEnum<String> {
    DASHBOARD(AuthRoute.DashBoard.ROOT, "Bảng tin", "nc-icon nc-paper"), /* ROLE("/role", "Vị trí - vai trò", "nc-icon nc-badge"), */
    USER(AuthRoute.User.LIST, "Danh sách người dùng", "nc-icon nc-badge"),
    PRODUCT_TYPE(AuthRoute.ProductType.LIST, "Phân loại đồ ăn & uống", "nc-icon nc-trophy"),
    PRODUCT(AuthRoute.Product.LIST, "Đồ ăn & đồ uống", "nc-icon nc-trophy"),
    CATEGORY(AuthRoute.Category.LIST, "Menu(Thực đơn)", "nc-icon nc-layout-11"),
    TABLE(AuthRoute.Table.LIST, "Danh sách bàn ăn", "nc-icon nc-tile-56"), 
    DISCOUNT(AuthRoute.Discount.LIST, "Khuyến mãi", "nc-icon nc-money-coins"),
    LOGOUT(AuthRoute.LOGOUT, "Đăng xuất", "nc-icon nc-button-power");

    private static final Map<String, SideBarRouteEnum> INDEX = PersistentEnums.index(SideBarRouteEnum.class);

    private final String path;

    private final String name;

    private final String icon;

    private SideBarRouteEnum(String path, String name, String icon) {
        this.path = path;
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String getValue() { return this.path; }

    @Override
    public String getDisplayName() { return this.name; }

    public String getIcon() { return this.icon; }

    @Override
    public Map<String, SideBarRouteEnum> getAll() { return INDEX; }

    public static SideBarRouteEnum parse(String value) {
        return value == null ? null : INDEX.get(value);
    }
}
