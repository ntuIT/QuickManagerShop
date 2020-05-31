package com.qman.web.masterdata;

import java.util.HashMap;
import java.util.Map;

import com.qman.web.constant.AuthRoute;

public class PermissionTable {

    /**
     * Singleton definition
     */
    private static PermissionTable instance = null;

    private PermissionTable() {
        initData();
    }

    public static PermissionTable getInstance() {
        if (instance == null) instance = new PermissionTable();
        return instance;
    }

    // Admin|Manager|Cashier|Bartender|Waiter
    // Code: ADM|QL|TN|PC|BB
    private Map<String, String[]> configMap = new HashMap<String, String[]>();

    private static String kAdmin = "ADM";

    private static String kManager = "QL";

    private static String kCashier = "TN";

    private static String kBartender = "PC";

    private static String kWaiter = "BB";

    public static String[] allRole = new String[]{kAdmin, kManager, kCashier, kBartender, kWaiter};

    public static String[] staffRole = new String[]{kCashier, kBartender, kWaiter};

    public static String[] managerRole = new String[]{kAdmin, kManager};

    private void initData() {
        configMap.put(AuthRoute.LOGOUT, allRole);
        configMap.put(AuthRoute.DashBoard.ROOT, allRole);
        // -----------------------------------------------------------------------------------------
        configMap.put(AuthRoute.User.ADD, new String[]{kAdmin});
        configMap.put(AuthRoute.User.CHANGE_PASS, allRole);
        configMap.put(AuthRoute.User.MY_PROFILE, allRole);
        configMap.put(AuthRoute.User.DETAIL, managerRole);
        configMap.put(AuthRoute.User.LIST, managerRole);
        // -----------------------------------------------------------------------------------------
        configMap.put(AuthRoute.ProductType.ROOT, managerRole);
        configMap.put(AuthRoute.ProductType.LIST, new String[]{kAdmin, kManager, kBartender});
        configMap.put(AuthRoute.ProductType.ADD, managerRole);
        configMap.put(AuthRoute.ProductType.DELETE, managerRole);
        configMap.put(AuthRoute.ProductType.EDIT, managerRole);
        configMap.put(AuthRoute.ProductType.DETAIL, managerRole);
        // -----------------------------------------------------------------------------------------
        configMap.put(AuthRoute.Category.ROOT, managerRole);
        configMap.put(AuthRoute.Category.LIST, new String[]{kAdmin, kManager, kBartender});
        configMap.put(AuthRoute.Category.ADD, managerRole);
        configMap.put(AuthRoute.Category.DELETE, managerRole);
        configMap.put(AuthRoute.Category.EDIT, managerRole);
        // -----------------------------------------------------------------------------------------
        configMap.put(AuthRoute.Table.LIST, allRole);
        configMap.put(AuthRoute.Table.DETAIL, allRole);
    }

    public Map<String, String[]> getConfigMap() { return configMap; }
}
