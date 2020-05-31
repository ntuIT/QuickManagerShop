package com.qman.web.module.user.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.masterdata.entity.RolePo;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.entity.UserRolePo;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;
import com.qman.web.orm.service.UserRoleService;
import com.qman.web.orm.service.UserService;
import com.qman.web.utility.MasterDataReadyEvent;
import com.qman.web.utility.PasswordUtils;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {java.lang.Exception.class, java.lang.RuntimeException.class})
public class AddUserBiz implements ApplicationListener<MasterDataReadyEvent> {

    private static final String ADMIN_USERNAME = "admin1";

    private static final String ADMIN_PASSWORD = "admin123";

    private static final Logger LOGGER = LoggerFactory.getLogger(AddUserBiz.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    public boolean doAddUser(UserPo user) {
        List<UserRolePo> userRoleList = new ArrayList<UserRolePo>();
        user.setPassword(PasswordUtils.makePassword(user.getPassword()));
        try {
            UserPo persistedUser = userService.save(user);
            for (long roleId : user.getRoleIdList()) {
                userRoleList.add(new UserRolePo(persistedUser.getId(), roleId));
            }
            userRoleService.saveAll(userRoleList);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void makeAdminUser(MasterDataStorage mdStorage) {
        LOGGER.debug("makeAdminUser() : CHECKING");
        if (userService.getByUserName(ADMIN_USERNAME) != null) { LOGGER.debug("makeAdminUser() : FINISH - admin1 existed, do nothing"); return; }
        LOGGER.debug("makeAdminUser() : START");
        List<RolePo> found = mdStorage.getRoleList().stream().filter(item -> item.getCode().equals("ADM")).collect(Collectors.toList());
        UserPo admin = new UserPo(ADMIN_USERNAME, null, PasswordUtils.makePassword(ADMIN_PASSWORD), UserStatus.ACTIVATED, "Quick", "Manager",
            "Administator", Gender.OTHER, null, new Date(), "N/A", new Date(), "N/A", "Quick Manager System", null, null);
        if (found.isEmpty()) { LOGGER.debug("makeAdminUser() : FAILED, Role list is Empty"); return; }
        try {
            UserPo persistedUser = userService.save(admin);
            UserRolePo adminUserRole = new UserRolePo(persistedUser.getId(), found.get(0).getId());
            UserRolePo persistedUserRole = userRoleService.save(adminUserRole);
            LOGGER.debug(persistedUserRole.getId().toString());
            LOGGER.debug("makeAdminUser() : SUCCESS");
        } catch (Exception e) {
            LOGGER.debug("makeAdminUser() : FAILED");
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationEvent(MasterDataReadyEvent event) {
        MasterDataStorage source = (MasterDataStorage) event.getSource();
        makeAdminUser(source);
    }
}
