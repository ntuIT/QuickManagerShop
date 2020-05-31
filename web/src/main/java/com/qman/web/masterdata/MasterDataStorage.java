package com.qman.web.masterdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.qman.web.ApplicationContextHolder;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.masterdata.entity.CategoryPo;
import com.qman.web.masterdata.entity.RolePo;
import com.qman.web.masterdata.glossary.SideBarRouteEnum;
import com.qman.web.masterdata.model.UserGender;
import com.qman.web.masterdata.service.RoleService;
import com.qman.web.module.base.SideBarItem;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.utility.MasterDataReadyEvent;

@Component
public class MasterDataStorage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterDataStorage.class);

    public static MasterDataStorage bean() {
        return ApplicationContextHolder.getContext().getBean(MasterDataStorage.class);
    }

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private RoleService roleService;

    private List<CategoryPo> categoryList = new ArrayList<CategoryPo>();

    public List<CategoryPo> getCategoryList() { return categoryList; }

    private List<RolePo> roleList = new ArrayList<RolePo>();

    public List<RolePo> getRoleList() { return roleList; }

    public List<UserGender> getGenderList() {
        List<UserGender> list = new ArrayList<UserGender>();
        for (Gender item : Gender.values()) list.add(new UserGender(item.getValue(), item.getDisplayViName()));
        return list;
    }

    @PostConstruct
    private void loadData() {
        LOGGER.debug("[START] get all Master Data.");
        LOGGER.debug("[Starting] get all Role.");
        roleList = roleService.getAll();
        LOGGER.debug("[FINISH] get all Master Data.");
        applicationEventPublisher.publishEvent(new MasterDataReadyEvent(this, "MasterDataReadyEvent"));
    }

    public List<SideBarItem> getSideBarInfo(String activePath, HttpSession session) {
        List<String> roleCodes = ((SessionData) session.getAttribute(SessionConstant.SESSION_INFO)).getRoleCodeList();
        List<SideBarItem> result = new ArrayList<SideBarItem>();
        for (SideBarRouteEnum re : SideBarRouteEnum.values()) {
            boolean isAllowable = validatePermission(re.getValue(), roleCodes);
            if (isAllowable) result.add(new SideBarItem(re.getValue().equals(activePath), re.getDisplayName(), re.getValue(), re.getIcon()));
        }
        return result;
    }

    private boolean validatePermission(String activePath, List<String> roleCodes) {
        if (activePath != null) {
            String[] config = PermissionTable.getInstance().getConfigMap().get(activePath);
            if (config == null) return false;
            List<String> listConfigRole = Arrays.asList(config);
            Boolean allow = listConfigRole.containsAll(roleCodes);
            return allow;
        }
        return false;
    }
}
